// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course

public class ActiveCourse extends Course {
   private ArrayList<Student> students;
   private String semester;
   private int lectureStart;
   private int lectureDuration;
   private String lectureDay;
   
   /**
    * A method to set the Schedule variables for an ActiveCourse object
    * @param lectureStart
    * @param lectureDuration
    * @param lectureDay
    */
   public void setScheduleVariable(int lectureStart, int lectureDuration, String lectureDay) {
      this.lectureStart = lectureStart;
      this.lectureDuration = lectureDuration;
      this.lectureDay = lectureDay;
   }

   /**
    * returns the lecture start time
    */
   public int getLectureStart() {
      return this.lectureStart;
   }

   /**
    * returns the lecture duration
    */
   public int getLectureDuration() {
      return this.lectureDuration;
   }

   /**
    * returns the lecture day
    */
  public String getLectureDay() {
      return this.lectureDay;
   }

 


 

 	/**
      A method to check whether a student is enrolled in the ActiveCourse or not.
      @param student
      @return true if student is enrolled in the ActiveCrouse and false if otherwise
	 */  
   public boolean studentEnrolledInActiveCourse(String studentId) {
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getId().equals(studentId)) {
            return true;
         }
      }
      return false;

   }

	/**
      Adds student to the ActiveCourse's students arraylist 
      @param student
	 */
   public void addStudent(Student student) {
      students.add(student);
   }

	/**
      Removes student from the ActiveCourse's students arraylist 
      @param student
	 */
   public void removeStudent(Student student) {
      students.remove(student);
   }


   /**
      A constructor method with appropriate parameters using the super class constructor to initialize inherited variables
      @param name
      @param code
      @param descr
      @param semester
      @param students
    */
   public ActiveCourse(String name, String code, String descr, String fmt, String semester,
         ArrayList<Student> students) {
      super(name, code, descr, fmt);
      this.semester = semester;
      this.students = new ArrayList<Student>(students);
      this.lectureDay = "";
      this.lectureDuration = 0;
      this.lectureStart = 0;
   }

   /**
		returns semester
	 */
   public String getSemester() {
      return semester;
   }
  
   
	/**
		Prints the students in this course (name and student id)
	 */
   public void printClassList() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println(students.get(i).toString());
      }
   }

  
   /**
		Prints the grade of each student in this course (along with name and student id)
	 */
      public void printGrades() {
      for (int i = 0; i < students.size(); i++) {
         System.out.println(students.get(i).getId() + " " + students.get(i).getName() + " " + getGrade(students.get(i).getId()));
      }

   }


   /**
		Returns the numeric grade in this course for this student. 
      If student not found in course, returns 0 
      @param studentId
      @return numeric grade of student 
	 */
   public double getGrade(String studentId) {
      for (int i = 0; i < students.size(); i++) {
         if (students.get(i).getId().equals(studentId)) {
            return students.get(i).getGrade(getCode());
         }
      }
      return 0;
   }


   /**
      Returns a String containing the course information
      as well as the semester and the number of students enrolled in the course 
	 */    
   public String getDescription() {
      return super.getDescription() + " " + semester + " Enrolment: " + students.size();
   }


   /**
      Sort the students in the course by name using the Collections.sort() method
      with appropriate arguments and use of private Comparator class below   
	 */    
   public void sortByName() {
      Collections.sort(students, new NameComparator());
   }


   /**
      class NameComparator implement the Comparator interface and compares two Student objects based on student name 
	 */    
   private class NameComparator implements Comparator<Student> {
      public int compare(Student s1, Student s2) {
         return s1.getName().compareToIgnoreCase(s2.getName());
      }
   }


   /**
      Sort the students in the course by student id using the Collections.sort() method
      with appropriate arguments and use of private Comparator class below   
	 */    
   public void sortById() {
      Collections.sort(students, new IdComparator());
   }


   /**
      class IDComparator implement the Comparator interface and compares two Student objects based on student id
	 */   
   private class IdComparator implements Comparator<Student> {
      public int compare(Student s1, Student s2) {
         return s1.getId().compareToIgnoreCase(s2.getId());
      }
   }
}
