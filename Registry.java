// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;


public class Registry {
	private TreeMap<String, Student> students = new TreeMap<String, Student>();
    private TreeMap<String, ActiveCourse> courses = new TreeMap<String, ActiveCourse>(String.CASE_INSENSITIVE_ORDER);

	public Registry() throws FileNotFoundException, NoSuchElementException{
	
		// Reads from the file students.txt and ensure for correct overall file name and format
		File inputFile = new File("students.txt");
		Scanner scanner = new Scanner(inputFile);
	
		while(scanner.hasNextLine()){
			String Name = scanner.next();
			String Id = scanner.next();

			Student s = new Student(Name,Id);
			students.put(Id,s);
		}
		scanner.close() ;


	ArrayList<Student> list = new ArrayList<Student>();


	 // CPS209
     String courseName = "Computer Science II";
     String courseCode = "CPS209";
     String descr = "Learn how to write complex programs!";
     String format = "3Lec 2Lab";
	 //courses.add(new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

     // CPS511
     courseName = "Computer Graphics";
     courseCode = "CPS511";
     descr = "Learn how to write cool graphics programs";
     format = "3Lec";
	 //courses.add(new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

     // CPS643
     courseName = "Virtual Reality";
     courseCode = "CPS643";
     descr = "Learn how to write extremely cool virtual reality programs";
     format = "3Lec 2Lab";
	 //courses.add(new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

     // CPS706
     courseName = "Computer Networks";
     courseCode = "CPS706";
     descr = "Learn about Computer Networking";
     format = "3Lec 1Lab";
	 //courses.add(new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

     // CPS616
     courseName = "Algorithms";
     courseCode = "CPS616";
     descr = "Learn about Algorithms";
     format = "3Lec 1Lab";
	 //courses.add(new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	 courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));

	}
	

	public TreeMap<String,ActiveCourse> getCourses(){
		return this.courses;

	}
	/**
		Adds new student to the registry (students TreeMap above) and ensure that student is not already in registry.
		@param name Name of the student to be registered
		@param id Id of the student to be registered
		@return true if student was not already registerd and is now registered
	 */
	public boolean addNewStudent(String name, String id) {
		Student s7 = new Student(name, id);
		if (students.containsKey(id)){
			return false;
		}
		students.put(id,s7);
		return true;

	}


	/**
		Removes a student from registry 
		@param id Id of the student to be removed
		@return true if student was found in registry and is now removed, false if otherwise
	 */
	public boolean removeStudent(String studentId) {
		if (students.containsKey(studentId)){
			students.remove(studentId);
			return true;
		}
		return false;
		
	}


	/**
		Print all registered students 
	 */
	public void printAllStudents() {
		for(String treeKey: students.keySet()){
			System.out.println(students.get(treeKey));
		}
		

	}


	/**
		Adds a student to a active course given by courseCode. Checks to see if 
		student has already completed or is already enrolled in course. If neither are the case,
		student is added to the active course and
		the active course is added to student list of credit courses with initial grade of 0 
		@param studentId Id of the student
		@param courseCode Code of the active course the student is to be added to
	 */	
	public void addCourse(String studentId, String courseCode) {
		if (students.containsKey(studentId)){
			Student student = students.get(studentId);
			if(students.get(studentId).tookCourse(courseCode)){}
			else{
				if(courses.containsKey(courseCode)){
					ActiveCourse c = courses.get(courseCode);
					c.addStudent(student);
					student.addCourse(c.getName(), c.getCode(), c.getDescription(), c.getFormat(), c.getSemester(),
					0.0);

				}
			}
		}	
	}


	/**
		Given a studentId and a course code, student is dropped from the active course. 
		Checks to see if student in the list of students for this course. 
		If so, student is removed from the active course 
		and the credit course is removed from the student's list of credit courses
		@param studentId Id of the student
		@param courseCode Code of the active course the student is to be dropped from
	 */	
	public void dropCourse(String studentId, String courseCode) {
		if(courses.containsKey(courseCode)){
			ActiveCourse activeCourse = courses.get(courseCode);
			if(activeCourse.studentEnrolledInActiveCourse(studentId)){
				if(students.containsKey(studentId)){
					Student student = students.get(studentId);
					activeCourse.removeStudent(student);
					student.removeActiveCourse(courseCode);
				}
			}
		}
	}


	/**
		Print all the active courses 
	 */
	public void printActiveCourses() {
		for(String treeKey: courses.keySet()){
			System.out.println(courses.get(treeKey).getDescription() + "\n" );
		}
	}


	/**
		Print the list of students in an active course
	 */
	public void printClassList(String courseCode) {
		if(courses.containsKey(courseCode)){
			courses.get(courseCode).printClassList();
		}
	}


	/**
		Given a course code, sorts the course's class list by student name
		@param courseCode
	 */
	public void sortCourseByName(String courseCode) {
		if(courses.containsKey(courseCode)){
			courses.get(courseCode).sortByName();
		}
	}

	/**
		Given a course code, sorts the course's class list  by student Id
		@param courseCode
	 */
	public void sortCourseById(String courseCode) {
		if(courses.containsKey(courseCode)){
			courses.get(courseCode).sortById();
		}
	}


	/**
		Given a course code, prints the the student's information and grades in that course 
		@param courseCode
	 */
	public void printGrades(String courseCode) {
		if(courses.containsKey(courseCode)){
			courses.get(courseCode).printGrades();
		}
	}


	/**
		Given a student Id, prints all the active courses of student
		@param studentId
	 */
	public void printStudentCourses(String studentId) {
		if(students.containsKey(studentId)){
			students.get(studentId).printActiveCourses();
		}		
	}


	/**
		Given a student Id, prints all the completed courses and grade of student
		@param studentId
	 */
	public void printStudentTranscript(String studentId) {
		if(students.containsKey(studentId)){
			students.get(studentId).printTranscript();
		}			
	}

	
	/**
		Given a course code, student id and numeric grade, sets the final grade of the student in specified credit course.
		Credit course is then set inactive
		@param studentId Id of the student
		@param courseCode Code of the active course the student's mark is being finalized in
		@param grade Final grade of student
	 */	
	public void setFinalGrade(String courseCode, String studentId, double grade) {
		if(courses.containsKey(courseCode)){
			ActiveCourse c = courses.get(courseCode);
			if(c.studentEnrolledInActiveCourse(studentId) == true){
				if(students.containsKey(studentId)){
					Student s = students.get(studentId);
					s.setFinalGrade(c, grade);
				}
			}
		}
	}
		
}
