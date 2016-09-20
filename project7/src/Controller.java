import java.awt.Component;
import java.util.*;

import javax.swing.*;


public class Controller extends SwingWorker<Object, Object> implements Observer{
	Model model;
	View view;
	
	Controller(Model model, View view) {
		
		Connector c = new Connector("pc.cs.purdue.edu", 1337, "connect monitor", this);
		this.model = model;
		this.view = view;
		
		execute();		
	}
	
	protected Object doInBackground() throws Exception {
		while(true) {
			
			Thread.sleep(100);
			 view.repaint();
		}
	}
	
	public void update(Observable o, Object arg) {
		synchronized(model.lock) {
			
			String mes = (String) arg;
			String [] M = mes.split(" ");
		if(M[0].equals("location")) {
			locationHandler(M);
		}
		else if(M[0].equals("request")) {
			requestHandler(M);
		}
		else if(M[0].equals("walking")) {
			walkingHandler(M);
		}
		else if(M[0].equals("moving")) {
			movingHandler(M);
		}
		else if(M[0].equals("volunteer")) {
			volunteerHandler(M);
		}
		else if(M[0].equals("delete")) {
			deteleHandler(M);
		}
		
		}	
	}
	
	public void locationHandler(String [] M) {
		String locName = M[1];
		Double a = Double.parseDouble(M[2]);
		Double b = Double.parseDouble(M[3]);
		
		if(model.getLocationByName(locName) == null) {
			Location location = new Location(model, locName, a, b);
			model.addLocation(location);
			
			
		}
	}
	
	public void requestHandler(String [] M){
			
		String rH = M[1];
		Location from = model.getLocationByName(M[2]);
		Location to = model.getLocationByName(M[3]);
		int score = Integer.parseInt(M[4]);
			
		if(model.getRequestByName(M[1]) == null) {
			Request newone = new Request(model,rH,from,to,score);
			model.addRequest(newone);
		}
		else{
			from.getRequests().remove(model.getRequestByName(rH));
		}
		
	}
		
		
		
		public void walkingHandler(String [] M) {
			String volName = M[1]; 
			String reqName = M[2];
			String fromWhere = M[3];
			String toWhere = M[4];
			
			Location fW = model.getLocationByName(fromWhere); 
			Location tW = model.getLocationByName(toWhere);
			Request reqW = model.getRequestByName(reqName);
			Volunteer volW = model.getVolunteerByName(volName);
			
			long timeW = Long.parseLong(M[5]);
			model.getVolunteerByName(volName).startWalking(reqW, timeW);
			
			fW.removeRequest(reqW);
			fW.removeVolunteer(volW);
			tW.addRequest(reqW);
			tW.addVolunteer(volW);
			
		}
	
		
		public void movingHandler(String[] M){
		
			if(model.getVolunteerByName(M[1]).getRequester() == null){
			
			Location from = model.getLocationByName(M[2]);
			Location to = model.getLocationByName(M[3]);
			
			from.removeVolunteer(model.getVolunteerByName(M[1]));
			to.removeVolunteer(model.getVolunteerByName(M[1]));
			
			}
		
		}
		public void volunteerHandler(String [] M) {
			String vH = M[1];
			Location current = model.getLocationByName(M[2]);
			int score = Integer.parseInt(M[3]);
			Volunteer volN = new Volunteer(model, vH, score, current);
			
			if(model.getVolunteerByName(vH) == null) {
				model.addVolunteer(volN);
				current.addVolunteer(volN);
				
			}
			
		}
		
	
	
	

		public void deteleHandler(String [] M){
	
			model.removeVolunteer(model.getVolunteerByName(M[1]));
	
		}
	
	
	
}