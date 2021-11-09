package edu.sdsu.cs160l.lab10.assignment;

import edu.sdsu.cs160l.lab10.assignment.institute.student.StudentLevel;
import edu.sdsu.cs160l.lab10.assignment.institute.student.StudentMajor;
import edu.sdsu.cs160l.lab10.exceptions.ClassFullException;
import edu.sdsu.cs160l.lab10.exceptions.NoSuchCourseException;
import edu.sdsu.cs160l.lab10.exceptions.StudentAlreadyEnrolledException;
import edu.sdsu.cs160l.lab10.assignment.institute.Registrar;
import edu.sdsu.cs160l.lab10.assignment.institute.student.Student;

/**
 * Create a report explaining in one or more lines (carries 2 points)
 * 1) checked vs unchecked exceptions.
 * 2) why using exceptions as conditions is a bad idea.
 * 3) what is try with resources
 * 4) the flow of try catch finally
 */
public class Lab10 {

  private static final Registrar registrar = new Registrar();

  public static void main(String[] args) {
    Student hmac = new Student(825000001L, "hmac", 3.8, StudentLevel.SENIOR, StudentMajor.COMPUTER_SCIENCE);
    Student john = new Student(825000002L, "john", 3.7, StudentLevel.FRESHMAN, StudentMajor.BIOLOGY);
    Student jane = new Student(825000003L, "jane", 3.6, StudentLevel.SOPHOMORE, StudentMajor.COMPUTER_ENGINEERING);
    Student nullStudent = null;
    executeTryCatch("CS210", hmac);
    // adding hmac again should give StudentAlreadyEnrolledException
    executeTryCatch("CS210", hmac);
    // adding null student should give NullPointerException
    executeTryCatch("CS210", nullStudent);
    executeTryCatch("CS210", john);
    // adding 3rd valid student to CS210 should give ClassFullException
    executeTryCatch("CS210", jane);

    // adding student to an invalid course should give NoSuchCourseException
    executeTryCatch("CS510", jane);
  }

  private static void executeTryCatch(String courseName, Student student) {
    try {
      registrar.enrollStudent(courseName, student);
    } catch (ClassFullException e) {
      System.err.println("Class Full Exception :: " + e.getMessage());
    } catch (NoSuchCourseException e) {
      System.err.println("No Such Course Exception :: " + e.getMessage());
    } catch (StudentAlreadyEnrolledException e) {
      System.err.println("Student already enrolled to the course :: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Generic exception :: " + e.getClass().getName() + " :: " + e.getMessage());
    } finally {
      System.out.println("Code ends here");
    }
  }
}