/**
     * This method is called by the searchBook() 
     * It checks if the book being searched for is same as 
     * the book object being referred. 
     * @parameter byAttributes determine which fields to check; 0 is number, 1 is name;
     * @parameter newBook a book object that used for search
     * If yes, it returns a true. Else false 
     */
    // public boolean equals(Book newBook, int byAttributes)

class Book {
    private int number;
    private String name;
    private String category;
    private boolean availability;
    
    public int getNumber() {
       return this.number;
    }
    
    public String getName() {
       return this.name;
    }
       
    public String getCategory() {
       return this.category;
    }
    
    public boolean availability(){
      
       return this.availability;
    }
    
    public Book(int number, String name, String category) {
        this.number = number;
        this.name = name;
        this.category = category;

    }
    
    public boolean equals(Book newBook, int byAttributes){
        
        if(byAttributes == 0) {
          if(this.number == newBook.number){
              return true;
          }else{return false;}
            
        }
        
        if(byAttributes == 1) {
          if(this.name.equals(newBook.name)) {
              return true;
          }
          else{return false;}
        
        }
        else {return false;}
        
    }
}
