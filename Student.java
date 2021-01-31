// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

import java.util.ArrayList;

// Class Student implement the Comparable interface 
public class Student implements Comparable<Student> {
  private String name;
  private String id;
  public ArrayList<CreditCourse> courses;


  /**
     Checks to see if a student has taken a course before. 
     If so, checks whether that course is currently active or inactive and prints a corresponding message
     @param courseCode 
     @return true if the student has the CreditCourse (Has taken or is already enrolled in the course), false if otherwise
   */
  public boolean tookCourse(String courseCode) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equalsIgnoreCase(courseCode)) {
        if (courses.get(i).getActive()) {
          System.out.println("Student is already enrolled in this course");
        }
        if (courses.get(i).getActive() == false) {
          System.out.println("Student has already taken this course");
        }
        return true;
      }
    }
    return false;
  }


  /**
     Finds the ActiveCourse Object in Student's arraylist of CreditCourses and removes it
     @param activeCourse
   */
  public void removeCreditCourse(ActiveCourse activeCourse) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equalsIgnoreCase(activeCourse.getCode())) {
        courses.remove(courses.get(i));
      }
    }
  }


  /**
     Finds the ActiveCourse in Student's arraylist of CreditCourses. 
     Sets the final grade and sets the course inactive
     @param activeCourse ActiveCourse object to be removed 
     @param finalGrade The final grade to be set
   */  
  public void setFinalGrade(ActiveCourse activeCourse, double finalGrade) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equalsIgnoreCase(activeCourse.getCode())) {
        courses.get(i).grade = finalGrade;
        courses.get(i).setInactive();
      }
    }
  }


  public Student(String name, String id) {
    this.name = name;
    this.id = id;
    courses = new ArrayList<CreditCourse>();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getGrade(String courseCode) {
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getCode().equals(courseCode)) {
        return courses.get(i).grade;
      }

    }
    return 0;
  }


	/**
    Adds a credit course to list of courses for this student. 
    Creates a CreditCourse object and sets the course active.
    Add the credit course to the Student's arraylist of CreditCourses
		@param courseName
		@param courseCode
		@param descr
		@param format
    @param sem
		@param grade   
	 */
  public void addCourse(String courseName, String courseCode, String descr, String format, String sem, double grade) {
    CreditCourse creditCourse = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
    creditCourse.setActive();
    courses.add(creditCourse);
  }
  

	/**
    Prints a student transcript. 	
    Prints all completed (i.e. non active) courses for this student (course code, course name, semester, letter grade) 
	 */
  public void printTranscript() {
    for (int i = 0; i < courses.size(); i++) {
      if (!courses.get(i).getActive()) {
        System.out.println(courses.get(i).displayGrade());
      }
    }
  }


	/**
    Prints all active courses this student is enrolled in 	
	 */ 
  public void printActiveCourses() {

    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getActive() == true) {
        System.out.println(courses.get(i).getDescription());

      }
    }
  }
  

	/**
		Drops a course (given by courseCode). 
    Finds the credit course in courses arraylist above and removes it if it is an active course
		@param courseCode
	 */
  public void removeActiveCourse(String courseCode) {

    for (int i = 0; i < courses.size() - 1; i++) {
      if (courses.get(i).getCode().equals(courseCode)) {
        if (courses.get(i).getActive()) {
          courses.remove(i);

        }
      }
    }
  }

  public String toString() {
    return "Student ID: " + id + " Name: " + name;
  }


	/**
    Overrides equals method inherited from superclass Object. 
		@param score
		@return If student ids are equal (of "this" student and "other" student) then return true. 
    Otherwise return false
	 */
  public boolean equals(Object other) {
    Student otherStudent = (Student) other;
    if (this.id.equals(otherStudent.id)) {
      return true;
    }
    return false;
  }


	/**
    Overrides the compareTo method in interface Comparable
		@param other
	 */
  @Override
  public int compareTo(Student other) {
    return getName().compareTo(other.getName());
  }
}
