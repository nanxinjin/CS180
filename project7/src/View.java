import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
//<Liqhawa Jubane, ljubane@purdue.edu>
//<Nanxin Jin , jinn@purdue.edu>

import javax.swing.*;

import java.util.Iterator;

public class View extends JPanel{

   Model model;

   private Image volImage;
   private Image requesterImage;
   private Image mpi;

   final static int DIAMETER = 16; 
   final static float dash1[] = { 10.0f };
   final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
   Graphics2D g2;
   float scaleWidth;  
   float scaleHeight;

   public View(Model model) {
  	
       this.model = model;
       volImage = loadImage("volunteer.jpg");
       requesterImage = loadImage("request.jpg");
       mpi = loadImage("CampusCropped-Faded.jpg");
       JFrame frame = new JFrame("SafeWalkView");
       JPanel mainPanel = new JPanel(new BorderLayout());

       mainPanel.add(this, BorderLayout.CENTER);
       frame.add(mainPanel);
       JScrollPane pane = new JScrollPane(this);
       frame.add(pane);
       frame.getContentPane().add(pane);
       frame.setSize(500,600);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void paintComponent(Graphics gr) {

   	synchronized(model.lock){
   		g2 = (Graphics2D) gr; 
   		scaleHeight = getHeight()/(float)(mpi.getHeight(null));
   		scaleWidth = getWidth()/(float)(mpi.getWidth(null));
   		g2.drawImage(mpi, 0, 0, getWidth(), getHeight(), null);

       drawLocation(); 
       drawIntransit(); 
   	}
   }
   

   private void drawLocation() {

       int xcordy = 0; int ycordyy = 0; 
       Iterator<Location> it = (model.getLocations()).iterator();

       while (it.hasNext()) {
    	   
               Location place = it.next();
               String name = place.getName();

               double[] position = place.getXY();
               int[] p = new int[2];
               p[0] = (int)position[0];
               p[1] = (int)position[1];
              
           String[] pString = new String[2];
           pString[0] = Integer.toString(p[0]);
           pString[1] = Integer.toString(p[1]);
           ycordyy = (int) position[1];
           xcordy = (int) position[0];

           g2.setColor(Color.BLACK); 
           g2.fillOval(Math.round(xcordy * scaleWidth), Math.round(ycordyy * scaleHeight), DIAMETER, DIAMETER);
           g2.setColor(Color.YELLOW); 
           g2.fillOval(Math.round(xcordy * scaleWidth)+2, Math.round(ycordyy * scaleHeight)+2, DIAMETER-4, DIAMETER-4);
           g2.setColor(Color.BLACK); 
           g2.setFont(new Font("Arial", Font.PLAIN, 12));



           int dy = g2.getFontMetrics().getHeight() * 2;

           ycordyy+=dy;  
           Iterator<Request> it2 = (model.getRequests()).iterator();
           while (it2.hasNext()) {
               Request request = (Request)it2.next();
               String reqName = request.getName();

               Location requested = request.getStart();
              
               double[] reqPosition = requested.getXY();
               int[] p2 = new int[2];
               p2[0] = (int)reqPosition[0];
               p2[1] = (int)reqPosition[1];
              
               String[] reqPos = new String[2];
               reqPos[0] = Integer.toString(p2[0]);
               
	            reqPos[1] = Integer.toString(p2[1]);

               if(reqPos[0].equals(pString[0]) && reqPos[1].equals(pString[1])){
         
                       drawName(requesterImage, reqName, xcordy, ycordyy);
                       ycordyy+=dy;   
               }
               }


           Iterator<Volunteer>it3 = (model.getVolunteers()).iterator();
           while (it3.hasNext()) {
        	   
               Volunteer volunteer = (Volunteer)it3.next();
               String volName = volunteer.getName();
               Location volLoc = volunteer.getCurrentLocation();

               if(volLoc != null){

               double[] volPosition = volLoc.getXY();
               int[] vP = new int[2];
               vP[0] = (int)volPosition[0];
               vP[1] = (int)volPosition[1];
               String[] volPos = new String[2];
               volPos[0] = Integer.toString(vP[0]);
               volPos[1] = Integer.toString(vP[1]);

               if(volPos[0].equals(pString[0])&& volPos[1].equals(pString[1])){
                       drawName(volImage, volName, xcordy, ycordyy);

                       ycordyy+=dy;
               	}     
              }
           }
       	}
   }



   private void drawName(Image image, String name, int x, int y) {
       int gap = g2.getFontMetrics().getHeight();

       g2.drawImage(image, Math.round(x * scaleWidth) - gap, Math.round(y * scaleHeight), 12, 12, null);
       g2.drawString(name, Math.round(x * scaleWidth), Math.round(y * scaleHeight) + gap - 3);
   }

  private void drawIntransit() {

       Iterator<Volunteer>it = (model.getVolunteers()).iterator();



       while (it.hasNext()) {

           Volunteer vMover = (Volunteer)it.next();
           Location locMover = vMover.getCurrentLocation();

           if(locMover == null){


           String volunteerName = vMover.getName();

           double[] xy = vMover.getCurrentPosition();
           int x = (int) Math.round(xy[0]);
           int y = (int) Math.round(xy[1]);


           g2.setFont(new Font("Arial", Font.PLAIN, 16));
           int lineW = g2.getFontMetrics().getHeight();
           g2.setColor(Color.BLACK);
           g2.drawString(volunteerName,Math.round(x * scaleWidth) + 21, Math.round(y * scaleHeight) + lineW - 5);
           
           
           double[] coord3 = vMover.getCurrentPosition();
           String [] locCoords = new String[2];
           locCoords[0] = Double.toString(coord3[0]);
           locCoords[1] = Double.toString(coord3[1]);

           int x1 = (int)Double.parseDouble(locCoords[0]);
           int y1 = (int)Double.parseDouble(locCoords[1]);
          
           double [] dest = vMover.getDestination();
           String [] destCoords = new String[2];
           destCoords[0] = Double.toString(dest[0]);
           destCoords[1] = Double.toString(dest[1]);
           int x2 = (int)Double.parseDouble(destCoords[0]);
	        int y2 = (int)Double.parseDouble(destCoords[1]);
          
           g2.setStroke(dashed); 
           g2.drawLine((Math.round(x1 * scaleWidth) + DIAMETER / 2), (Math.round(y1 * scaleHeight) + DIAMETER / 2), (Math.round(x2 * scaleWidth) + DIAMETER / 2), (Math.round(y2 * scaleHeight) + DIAMETER / 2));
           g2.setColor(Color.GREEN);
           g2.fillOval(Math.round(x * scaleWidth),Math.round(y * scaleHeight), DIAMETER, DIAMETER);
           	}
       	}
  }


   private Image loadImage(String name) {
       URL url = getClass().getResource(name); 
       if (url == null)
           throw new RuntimeException("Could not find " + name);
       return new ImageIcon(url).getImage();
   }

}