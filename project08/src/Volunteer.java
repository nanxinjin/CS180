/**
 * @author jinn
 * @author ljubane
 */

public class Volunteer {
    private Model m;  
    private Location loc;     
    
    private int add;
    private String people;
    private String req;
    private long timeReq;
    private long timeBegin;
    
    private double[] begin;
    private double[] end;

    public Volunteer(Model model, String name, int score, Location location) {
        this.m = model;
        this.people = name;
        this.add = score;
        this.loc = location;
        
        location.addVolunteer(this);
        model.addVolunteer(this);
    }
    
    public Location getCurrentLocation() {
        return loc;
    }
    
    public double[] getCurrentPosition() {
        double xLength = end[0] - begin[0];
        double yLength = end[1] - begin[1];
        
        double[] position = new double[2];
        
        long time = System.currentTimeMillis() - timeBegin;
        if (time >= timeReq) {
            position[0] = end[0];
            position[1] = end[1];
        } else {
            position[0] = xLength * time / timeReq + begin[0];
            position[1] = yLength * time / timeReq + begin[1];
        }
        return position;
    }
    
    public double[] getDestination() {
        return end;
    }

    public String getName() {
        return people;
    }

    public String getRequester() {
        return req;
    }

    public int getScore() {
        return add;
    }

    public double[] getStart() {
        return begin;
    }
    
    public void startMoving(Location destination, long timeRequired) {
        startMovement(loc.getXY(), destination.getXY(), timeRequired, null);
 
    }

    public void startWalking(Request request, long timeRequired) {
        m.removeRequest(request);
        startMovement(request.getStart().getXY(), request.getDestination().getXY(), timeRequired, request.getName());
    }

    private void startMovement(double[] start, double[] destination, long timeRequired,
                               String requester) {
        if (loc != null)
            loc.removeVolunteer(this);
        loc = null;
        
        this.begin = start;
        this.end = destination;
        this.timeReq = timeRequired;
        this.req = requester;
        
        timeBegin = System.currentTimeMillis();
    }
}
