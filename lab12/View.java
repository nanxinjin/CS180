

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.*;

import java.util.Iterator;

public class View extends JPanel{
    
    Model model;
    private Image mapImage;
    private Image volunteerImage;
    private Image requesterImage;
    
    
    final static int DIAMETER = 16; // diameter of circle at each location
    final static float dash1[] = { 10.0f };
    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
    Graphics2D g2;
    float scaleWidth;  //  Width Parameter for resizing the image
    float scaleHeight; //  Height Parameter for resizing the image
    
    public View(Model model) {
        
        
        this.model = model;
        
        mapImage = loadImage("CampusCropped-Faded.jpg");
        volunteerImage = loadImage("volunteer.jpg");
        requesterImage = loadImage("request.jpg");
        
        
        // The following code displays a blank JPanel with SafeWalkView in the title bar    
        JFrame frame = new JFrame("SafeWalkView");
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Adding the canvas (this) to the main panel at the CENTER...
        mainPanel.add(this, BorderLayout.CENTER);
        
        // Adding the main panel to the frame...
        frame.add(mainPanel);
        JScrollPane pane = new JScrollPane(this);
        frame.add(pane);
        frame.getContentPane().add(pane);
        frame.setSize(500,600);
        // Sets visible and away we go...
        frame.setVisible(true);
        
    }
    
    
    
    /**
     * Called on the Event Dispatch Thread (EDT) in response to a call to
     * repaint(). Accesses the model to decide what to paint. Since the model is
     * also being accessed and updated on a different thread (by the Controller
     * when messages arrive), must get the lock before accessing the model.
     */
    public void paintComponent(Graphics gr) {
        g2 = (Graphics2D) gr; //Stores the graphic as a 2D graphic image
        
        /** 
         *  Insert your code by following the instructions in TODO:
         */  
        
        
        // TODO Calculates the 'scaleWidth' and 'scaleHeight' parameters as width of the Panel
        //divided by the mapImage Width or Height.
        // To implement this use the getWidth() and getHeight() methods defined in the JPanel class 
        // and the type-casted (float)mapImage.getWidth(null) and (float)mapImage.getHeight(null) values 
	  // respectively for this section of the code. Note that these parameters are for resizing the 
	  // image and the coordinates as per the panel dimensions. 
        
        
        
        // Draw the map first...
        //TODO Draws the image using DrawImage() method
        //Syntax for drawImage method:  g2.drawImage(Image img, int x, int y, int width, int height, null);
        
        
        drawLocation();  //  Calls the drawLocation method
        drawIntransit(); //  Calls the drawIntransit method
        
    }
    
    
    /** 
     * This method places indicators on each marked location and displays the volunteers 
     * and the requesters present at a particular location. The method first Iterates through 
     * each location HashSet<String> of locations stored in the model and converts the string
     * coordinates into integers. These values are used to draw two concentric circles at location.
     * 
     * Next the method displays a list of volunteers and requesters at the location. 
     * 
     */  
    
    private void drawLocation() {
        
        /** 
         *  Insert your code by following the instructions in TODO:
         */  
        
        
        //Refer Javadocs for using Iterator
        int x = 0; // Variable for X coordinate of the location 
        int y = 0; // Variable for Y coordinate of the location
        Iterator it = (model.getCoordinates()).iterator(); 
        
        while (it.hasNext()) {
            String Coordinate = (String) it.next(); //Iterates through each location
            String[] CoordSplit = Coordinate.split(" ");
            x = Integer.parseInt(CoordSplit[0]); //Fetches x coordinate
            y = Integer.parseInt(CoordSplit[1]); //Fetches y coordinate
            
            // Draw overlapping circle for the building location...
            g2.setColor(Color.BLACK); //Sets color to Black for outer circle
            
            //TODO use this command to fill the first circle g2.fillOval(Math.round(x * scaleWidth), Math.round(y * scaleHeight), DIAMETER, DIAMETER);
            
            g2.setColor(Color.YELLOW); //Sets color to Yellow for inner circle
            
            //TODO use this command to fill the second circle g2.fillOval(Math.round(x * scaleWidth), Math.round(y * scaleHeight), DIAMETER, DIAMETER);
            //Adjust the dimensions of the Yellow circle to make the circles appear like concentric circles
            
            g2.setColor(Color.BLACK); //Sets the color back to black
            
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            // Sets font to "Arial" with type= Font.PLAIN and size = 12
            
            
            int dy = g2.getFontMetrics().getHeight(); // Calculates line width dy
            
            y+=dy;   // Adds dy to the y coordinate to move to the next line in the image
            
            // This prints the volunteers and the requests in the next line 
            Iterator<String> it2 = (model.getRequester2Location()).keySet()
                .iterator();
            while (it2.hasNext()) {
                String key = it2.next(); //Iterates through each key
                //TODO gets location of each Requester (key) and prints the requesters at each location 
                //using drawName method  
                
                y+=dy;   // Adds dy to the y coordinate to move to the next line in the image
            }
            
            Iterator<String> it3 = (model.getVolunteer2Location()).keySet()
                .iterator();
            while (it3.hasNext()) {
                String key = it3.next(); //Iterates through each key
                //TODO gets location of each Volunteer (key) and prints the volunteers at each location 
                //using drawName method  
                
                y+=dy;   // Adds dy to the y coordinate to move to the next line in the image
            }
        }
    }
    
    
    
    private void drawName(Image image, String name, int x, int y) {
        int gap = g2.getFontMetrics().getHeight(); 
        
        g2.drawImage(image, Math.round(x * scaleWidth) - gap, Math.round(y * scaleHeight), 12, 12, null);
        g2.drawString(name, Math.round(x * scaleWidth), Math.round(y * scaleHeight) + gap - 3);
    }
    /** Method drawIntransit
      *  This method draws movers from start location to end location and draws
      *  a moving green dot to depict the motion. Command for motion is given in 
      *  Controller.java
      */ 
    private void drawIntransit() {
        /** 
         *  Insert your code in TODO by following the instructions:
         */  
        Iterator it = (model.getMovers()).iterator();
        while (it.hasNext()) {   
            
            String mover = (String) it.next();
            
            // Uses the getVolunteerPosition() method in model to get location of mover.
            //You may uncomment the following code for implementing this:
            //double[] xy = model.getVolunteerPosition(mover);
            //int x = (int) Math.round(xy[0]);
            //int y = (int) Math.round(xy[1]);
            
            
            // Draw mover name and anyone being walked
            //TODO Sets font to "Arial" with type= Font.PLAIN and size = 16 (As done above)
            //TODO Calculates line width ('gap' variable) using methods g2.getFontMetrics().getHeight()
            
            //TODO sets the color to Color.Black using: g2.setColor(Color.BLACK);
            //TODO Stores the mover name in a string variable for displaying
            //TODO Draws the mover name at the obtained position using drawString method 
            
            
            // Drawing the dashed line
            String[] fields = mover.split(" ");
            //TODO Fetches 'name' from String array and stores in a String variable
            
            //TODO Obtains start building of mover using HashMap<String,String> getVolunteer2Location() method
            //TODO Obtains location coordinates of the Building using getLocation2Coordinate() method in model
            //TODO Splits the location String obtained into coordinates using parseInt() and store in two different variables 
            
            //TODO Stores destination Building name into a String variable
            //TODO gets coordinates of the building using getLocation2Coordinate() method
            //TODO Splits the obtained String to save the coordinates in two int variables
            
            g2.setStroke(dashed); //sets drawing style to shaded line
            
            //TODO: Draws a line using GraphicsObject.drawLine() method 
            //Syntax: drawLine(int x1, int y1, int x2, int y2)
            //for coordinate use the scaled and rounded coordinates with offset given in the example 
            //For example for x coordinate of starting point use (Math.round(xStart * scaleWidth) + DIAMETER / 2)
            
            
            // Draw filled circle at mover location on the dashed line
            //TODO Sets a moving green dot at the scaled coordinate
            // Use setColor() and fillOval() method for drawing the moving green dot. 
        }
    }
    
    
    /** Method loadImage
      * Loads images from resources, allowing use in .jar files.
      * 
      * @param name
      *            file name where the icon is located.
      * @return the Image found in the file.
      */
    private Image loadImage(String name) {
        URL url = getClass().getResource(name); //Gets the image URL
        if (url == null)
            throw new RuntimeException("Could not find " + name);
        return new ImageIcon(url).getImage(); //Loads the image and returns the Image
    }
    
}
