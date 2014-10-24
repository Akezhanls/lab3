public class Student {
   private int id;
   private String firstName; 
   private String lastName;   
   private int year;  
   private String faculty;

   public Student() {}
   public Student(String fname, String lname, int year, String faculty) {
      this.firstName = fname;
      this.lastName = lname;
      this.year = year;
      this.faculty=faculty;
   }
   public int getId() {
      return id;
   }
   public String getFirstName() {
      return firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public int getyear() {
      return year;
   }
   public String getFaculty() {
	      return faculty;
   }
   
   public void updateStudent(String fname, String lname, int year, String faculty ) {
      this.firstName = fname;
      this.lastName = lname;
      this.year = year;
      this.faculty=faculty;
   }
}