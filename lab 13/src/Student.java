public class Student extends AcademicPerson {

	// Instance variables
	private int[] grades; // grade for the corresponding course
	private static final int MAX_COURSES = 30; // maximum number of courses

	// Constructor
	public Student(String name, String address) {
		super(name, address);
		courses = new String[MAX_COURSES];
		grades = new int[MAX_COURSES];
	}

	// It adds a course and corresponding grade to the lists.
	// Student can take the same course couple of times. If a course that
	// already exists in the list is given as the input of the method you need
	// to compare the input grade with the one that is saved in the Grades list,
	// the higher grade needs to be saved in the Grades list.
	public void addCourseGrade(String course, int grade) {
		boolean exists = false;
		for(int i = 0; i< numCourses; i++){
	         if(courses[i].equals(course)){
	        	 if(grades[i] < grade) {
	        		 grades[i] = grade;
	        		 exists = true;
	        	 } 
	         }
	         
	        }
		if(exists == false){
			courses[numCourses] = course;
		
			grades[numCourses] = grade;
			numCourses ++;
		}
	}

	// This method prints the student's average grade for all the courses.
	// This method throws IllegalDivisionByZero exception, when there is no
	// courses in the list.
	public void getAverageGrade() throws IllegalDivisionByZero {
		int sum = 0;
		for (int i = 0; i < numCourses; i++) {
			
			sum += grades[i];
			
		}
		int average;
		
		if(numCourses != 0) {
			average = sum/numCourses;
		}else{throw new IllegalDivisionByZero("There is no courses!");}
		System.out.println(average);
	}

	// It prints all the courses with the corresponding grades in each line.
	@Override
	public void printCourses() {
		for(int i = 0; i < numCourses; i++){
			  
			   System.out.println(courses[i]);
			  
			}
	}

	public int[] getGrades() {
		return grades;
	}

	public void setGrades(int[] grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Student: " + super.toString();
	}
}