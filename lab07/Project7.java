import javax.swing.JOptionPane;

public class Project7 {
  public static void main(String[] args) {
      
      JOptionPane.showMessageDialog(null, 
                                     "Welcome to CollegeFeeCalculator!",
                                     "CollegeFeeCalculator",
                                     JOptionPane.INFORMATION_MESSAGE);
  //TODO #1
      String result1;
      result1 = JOptionPane.showInputDialog(null,
                                             "Please enter your name,then press OK",
                                             "Name",                               
                                             JOptionPane.QUESTION_MESSAGE);
  //TODO #2    
      int result2;
      String enroll= null;  
      String[] enrollment = {"Part-time","Full-time"};
      result2 = JOptionPane.showOptionDialog(null,
                                            "Please select your enrollment",
                                            "Enrollment",
                                             JOptionPane.PLAIN_MESSAGE,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            enrollment,null);
      if (result2 == 0){
          enroll = "Part-time";
      }
      else if (result2 == 1){
          enroll = "Full-time";
      }
  //TODO #3    
      String result3;
      int credit;
      result3 = JOptionPane.showInputDialog(null,
                                             "Please enter the no. of credit hours,then press OK",
                                             "Credit Hours",                               
                                             JOptionPane.QUESTION_MESSAGE);
      
      credit = Integer.parseInt(result3);
      
  //TODO #4
      String result5=null;
      if(enroll.equals("Part-time") && credit >= 8 ||enroll.equals("Full-time") && credit < 8){
      JOptionPane.showMessageDialog(null, 
                                     "Please enter valid credit hours for the current enrollment",
                                     "Invalid no. of credits",
                                     JOptionPane.ERROR_MESSAGE);
      } 
      else{
      
  //TODO #5
    
    String[] b = "In-state Out-of-state International".split(" ");
    result5 = (String)JOptionPane.showInputDialog(null,
                                         "Please select the appropriate residency",
                                         "Residency",                               
                                         JOptionPane.QUESTION_MESSAGE,
                                          null,
                                          b,
                                          "In-state");
      }
    
   //TODO #6
    String result6;
    String campus;
    String[] c = "ON-Campus OFF-Campus".split(" ");
    result6 = (String)JOptionPane.showInputDialog(null,
                                         "Please select your housing",
                                         "Housing",                               
                                         JOptionPane.QUESTION_MESSAGE,
                                          null,
                                          c,
                                          "ON-Campus");
    
  
          campus = result6;
      String result7=null;
    if (campus.equals ("ON-Campus")){
    //TODO #7

    String[] d = "Earhart Hillenbrand Owen Windsor".split(" ");
    result7 = (String)JOptionPane.showInputDialog(null,
                                         "Please select the residence hall",
                                         "Residence-Hall",                               
                                         JOptionPane.QUESTION_MESSAGE,
                                          null,
                                          d,
                                          "Earhart");
    } else{
    
    //TODO #8
    int total;
    int tuition=0;
    int housing=0;
    
    if (enroll.equals ("Full-time")){
      if (result5.equals ("In-state")){
          tuition = 4996;
      }
      else if (result5.equals ("Out-of-state")) {
          tuition = 9401 + 4996;
      }
      else if (result5.equals ("International")){
          tuition = 1000 + 9401 + 4996;
      }
     }
    
    else if (enroll.equals ("Part-time")){
        if (result5.equals ("In-state")){
          tuition = 350 * credit;
      }
      else if (result5.equals ("Out-of-state")) {
          tuition = (600 + 350) * credit;
      }
      else if (result5.equals ("International")){
          tuition = (70 + 600 + 350) * credit;
      }
    }
    
    if (campus.equals ("ON-Campus")){
      if(result7.equals ("Earhart")){
          housing = 4745;
      }
      else if(result7.equals ("Hillenbrand")){
          housing = 5307;
      }
      else if(result7.equals ("Owen")){
          housing = 4130;
      }
      else if(result7.equals ("Windsor")){
          housing = 4150;
      }
    } 
    else if (campus.equals ("OFF-Campus")) {
        housing = 0;
    }
    
    total = tuition + housing;
    }
    
    //TODO #9
    String result9;
    result9 = "Name:";
    JOptionPane.showMessageDialog(null, 
                                     result9,
                                     "CollegeFeeCalculator",
                                     JOptionPane.INFORMATION_MESSAGE);
    
    //TODO #10
      int result10 = 0;
      String[] done = {"yes","no"};
      result10 = JOptionPane.showOptionDialog(null,
                                            "Would you like to perform another fee calculation?",
                                            "Are you done?",
                                             JOptionPane.PLAIN_MESSAGE,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            done,
                                             null);
    
  
  }


}