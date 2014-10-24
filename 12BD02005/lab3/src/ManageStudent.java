import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageStudent {
   private static SessionFactory factory; 
   public ManageStudent(){
	   try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
   }
   
   public static void main(String[] args) {
   }
   
   /* Method to CREATE an Student in the database */
   public Integer addStudent(String fname, String lname, int year, String faculty){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer StudentID = null;
      try{
         tx = session.beginTransaction();
         Student Student = new Student(fname, lname, year, faculty);
         StudentID = (Integer) session.save(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return StudentID;
   }
   public String listStudents( ){
      Session session = factory.openSession();
      Transaction tx = null;
      String res="";
      try{
         tx = session.beginTransaction();
         List Students = session.createQuery("FROM Student").list(); 
         for (Iterator iterator = 
                           Students.iterator(); iterator.hasNext();){
            Student Student = (Student) iterator.next(); 
            res+=Student.getId()+"&"+Student.getFirstName()+"&"+Student.getLastName()+"&"+Student.getFaculty()+"&"+Student.getyear()+"ELA";
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return res;
   }
   public void updateStudent(Integer StudentID,  String fname, String lname, String faculty, int year ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Student Student = (Student)session.get(Student.class, StudentID); 
         Student.updateStudent(fname,lname,year,faculty);
		 session.update(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   public void deleteStudent(Integer StudentID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Student Student = (Student)session.get(Student.class, StudentID); 
         session.delete(Student); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}