/**
 * @author jinn
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingWorker;

public class Controller extends SwingWorker<Object, Object> implements Observer {
    Model m;
    View view;
    
    Connector connect = new Connector("pc.cs.purdue.edu", 1337, "connect k989227", this);
    Connector connect2 = new Connector("pc.cs.purdue.edu", 1337, "connect k989227", this);

    Controller(Model model, View view, String host, int port) {
	this.m = model;
	this.view = view;

	new Connector(host, port, "connect monitor", this);
	execute();
    }

    protected Object doInBackground() throws Exception {
	while (true) {
	    Thread.sleep(100); 
	    view.repaint(); 
	}
    }

    public void update(Observable arg1, Object arg2) {
	String line = (String) arg2;
	String[] fields = line.split(" ");
	String message = fields[0];

	synchronized (m.lock) {
	    
	    if (message.equals("location"))
		handleLocation(fields);
	    else if (message.equals("volunteer"))
		handleVolunteer(fields);
	    else if (message.equals("request"))
		handleRequest(fields);
	    else if (message.equals("moving"))
		handleMoving(fields);
	    else if (message.equals("walking"))
		handleWalking(fields);
	    else if (message.equals("delete"))
		handleDelete(fields);
	    else if (message.equals("reset"))
		handleReset(fields);
	    else 
		ignoring(fields);
	}
    }

    public Request nextRequest(Volunteer volunteer, Model model) {
        
    	
    	 Request [] box = new Request[model.getRequests().size()];
    	 box = model.getRequests().toArray(box);
    	 HashMap<Integer, Request> hashReq = new HashMap <Integer, Request>();
    	 int amount = 0;
    	 
        for(int j = 0; j < box.length;j++){        	
        	
        	hashReq.put(box[j].getValue(), box[j]);
        	
        	if(box[j].getValue() > amount){
        		amount = box[j].getValue();
        	}
        	
        }
         
        Request best = hashReq.get(amount);
       
        return best;
        
      
  }
    
    public void handleVolunteer(String[] fields) {
	String volunteerString = fields[1];
	String locationString = fields[2];
	int score = Integer.parseInt(fields[3]);
	
    Log.i("VOLUNTEER: volunteer=%s location=%s score=%d", volunteerString, locationString, score);
	
	Volunteer volunteer = m.getVolunteerByName(volunteerString);
	Location location = m.getLocationByName(locationString);
	if (location == null) {
	    Log.w("Haven't seen volunteer location %s yet", locationString);
	    return;
	}

	if (volunteer != null)
	m.removeVolunteer(volunteer);
	new Volunteer(m, volunteerString, score, location);
	

    if (volunteerString.equals("kim1004")) {
    
        Request req = nextRequest(m.getVolunteerByName("kim1004"), m);
       
        boolean flag = false;
        if (req == null) {
        	flag = true;
        }
        
        if(req.getStart().getName() == m.getVolunteerByName("kim1004").getCurrentLocation().getName()){
              
        	System.out.println("GO WALK");
        	
        	connect.writeLine("walk " + req.getName());
         	
        } 
        else {
        	System.out.println("GO MOVE");
        	Location getLoc = req.getStart();
        	String ll = getLoc.getName();   	
        	connect2.writeLine("move " + ll);
        	      	
        }
     }
		
	}
	   
    public void handleMoving(String[] fields) {
	String volunteerString = fields[1];
	String startString = fields[2];
	String destinationString = fields[3];
	int transitTime = Integer.parseInt(fields[4]);

	Log.i("MOVING: volunteer=%s start=%s destination=%s transitTime=%d", volunteerString,
		startString, destinationString, transitTime);

	Volunteer volunteer = m.getVolunteerByName(volunteerString);
	if (volunteer == null) {
	    Log.w("Haven't seen moving volunteer %s yet", volunteerString);
	    return;
	}
	
	Location start = m.getLocationByName(startString);
	if (start == null) {
	    Log.w("Haven't see start location %s yet", startString);
	    return;
	}
	
	Location destination = m.getLocationByName(destinationString);
	if (destination == null) {
	    Log.w("Haven't seen destination location %s yet", destinationString);
	    return;
	}

	if (!start.getName().equals(startString))
	    Log.w("MOVING violation: %s not at %s but rather at %s", volunteerString, startString,
		    start.getName());

	volunteer.startMoving(destination, transitTime);
    }

    public void handleWalking(String[] fields) {
	String volunteerName = fields[1];
	String requesterName = fields[2];
	String startString = fields[3];
	String destinationString = fields[4];
	int transitTime = Integer.parseInt(fields[5]);

	Log.i("WALKING: volunteer=%s requester=%s start=%s destination=%s transitTime=%d",
		volunteerName, requesterName, startString, destinationString, transitTime);

	Volunteer volunteer = m.getVolunteerByName(volunteerName);
	if (volunteer == null) {
	    Log.w("Haven't seen walking volunteer %s yet", volunteerName);
	    return;
	}
	Request request = m.getRequestByName(requesterName);
	if (request == null) {
	    Log.w("Haven't seen requester %s yet", requesterName);
	    return;
	}
	
	Location start = m.getLocationByName(startString);
	if (start == null) {
	    Log.w("Haven't seen start location %s yet", startString);
	    return;
	}
	
	Location destination = m.getLocationByName(destinationString);
	if (destination == null) {
	    Log.w("Haven't seen destination location %s yet", destinationString);
	    return;
	}

	if (!start.getName().equals(startString))
	    Log.w("WALKING violation: %s not at %s but rather at %s", volunteerName, startString,
		    start.getName());

	volunteer.startWalking(request, transitTime);
    }

    public void handleRequest(String[] fields) {
	String requesterName = fields[1];
	String startString = fields[2];
	String destinationString = fields[3];
	int value = Integer.parseInt(fields[4]);

	Request request = m.getRequestByName(requesterName);
	if (request != null)
	    m.removeRequest(request);
	
	Location start = m.getLocationByName(startString);
	Location destination = m.getLocationByName(destinationString);

	if (start == null) {
	    Log.w("Haven't see the request start location %s yet", startString);
	    return;
	}

	if (destination == null) {
	    Log.w("Haven't see the request destination location %s yet", destinationString);
	    return;
	}

	new Request(m, requesterName, start, destination, value);
    }

    private void handleDelete(String[] fields) {
	Volunteer volunteer = m.getVolunteerByName(fields[1]);
	if (volunteer == null) {
	    Log.w("Haven't see deleted volunteer %s yet", fields[1]);
	    return;
	}
	m.removeVolunteer(volunteer);
    }

    private void handleLocation(String[] fields) {
	String building = fields[1];
	double x = Double.parseDouble(fields[2]);
	double y = Double.parseDouble(fields[3]);
	
	if (m.getLocationByName(building) == null) 
	    new Location(m, building, x, y);
    }

    private void handleReset(String[] fields) {
	HashSet<Request> requestsClone = (HashSet<Request>) m.getRequests().clone();
	for (Request request : requestsClone)
	    m.removeRequest(request);
	HashSet<Volunteer> volunteersClone = (HashSet<Volunteer>) m.getVolunteers().clone();
	for (Volunteer volunteer : volunteersClone)
	    m.removeVolunteer(volunteer);
    }

    private void ignoring(String[] fields) {
	Log.w("IGNORING: %s", fields[0]);
    }
}