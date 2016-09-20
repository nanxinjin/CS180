import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;


public class Lab14 {

 public static void printSentences() {
	 Scanner sc = new Scanner(System.in);

	 Queue<String> q = new ArrayDeque<String>();  

     while(sc.hasNextLine()) {
    	 
    	 String store [] = sc.nextLine().split(" ");
    	 boolean flag = false;
    	 
    	 for(int i = 0; i < store.length; i ++) {
    		 q.add(store[i]);
    		 
    		 if (store[i].contains("!")|| store[i].contains("?")||store[i].contains(".")) {    			 
    			 flag = true;
    			 
    			 while (q.size() > 0) {
    				 
    				 System.out.print(q.remove() + " ");
    			 }
    		 }	
    	 }
    	 if (flag == true) {
    		 
    		 System.out.println();
    	 }
     }
 	}
     
 public static String findBestMedalist(File fileName) throws FileNotFoundException {

  Map<String, Integer> medalistPoints = new HashMap<String, Integer>();
  
  Scanner fileScan = new Scanner(fileName);
  
  while(fileScan.hasNextLine()) {
  
	  String[] list = fileScan.nextLine().split(" ");
  
	  if(list.length < 2) {
	   
		  continue;
	  }
  
	  for(int i = 2 ; i <=6 ; i++) {
		  if(medalistPoints.containsKey(list[i])) {		  
			  int a = medalistPoints.get(list[i])+ 2;
			  medalistPoints.put(list[i], a);
			  
		  }
		  else{
			  medalistPoints.put(list[i],2);
			  
		  }
	  }
	  for(int i = 8; i <=12; i++) {
		  if(medalistPoints.containsKey(list[i])) {		  
			  int b = medalistPoints.get(list[i])+ 1;
			  medalistPoints.put(list[i], b);
			  
		  }
		  else {
			  medalistPoints.put(list[i],1);
			  
		  }
    
	  }
  }
        
  Entry<String, Integer> point = null;
  for(Entry<String, Integer>entry : medalistPoints.entrySet()) {
	  
	  if(point == null||entry.getValue() > point.getValue()) {
		  point = entry;
  
	  }
  
  }
  String bestMedalist = point.getKey();
  return bestMedalist;
  
 }

 
 public static void main(String[] args) throws FileNotFoundException {
 
  File tweets = new File("lab14test");
  String winner = findBestMedalist(tweets);
  System.out.println("Winner is: " + winner);
        
  printSentences();
 }
}


