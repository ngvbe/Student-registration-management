// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class StudentRegistrySimulator {
	public static void main(String[] args) throws FileNotFoundException, NoSuchElementException {

		try{
			Registry registry = new Registry();
			Scheduler scheduler = new Scheduler(registry.getCourses());
			


		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals(""))
				continue;

			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();

			if (command == null || command.equals(""))
				continue;

			else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST")) {
				registry.printAllStudents();
			} else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
				return;

				
			// Registers a new student in registry (gets student name and id string)
			// Ensures that name is all alphabetic characters and id string is all numeric characters
			// Handles null name/id input 
			else if (command.equalsIgnoreCase("REG")) {
				try {
					String studentName = commandLine.next();
					String studentId = commandLine.next();
					if (isStringOnlyAlphabet(studentName) == false) {
						System.out.println("Invalid Characters in name " + studentName);
					}
					else if (isNumeric(studentId) == false) {
						System.out.println("Invalid Characters in Id " + studentId);
					} 
					else if (studentId.length() != 5){
						System.out.println("Id needs to be 5 digits");
					}
					else if ((registry.addNewStudent(studentName, studentId)) == false) {
						System.out.println("Id " + studentId + " is already registered");
					} 
					else {
						registry.addNewStudent(studentName, studentId);
					}
				} catch (Exception e) {
					System.out.println("Missing all necessary parameter(s)");
				}


			// Deletes a student from registry (gets student id and numeric)
			// Ensures that name is all alphabetic characters and id string is all numeric characters
			// Ensures that student id is only 5 digits
			// Handles null name/id input
			} else if (command.equalsIgnoreCase("DEL")) {
				try {
					String studentId = commandLine.next();
					if (isNumeric(studentId) == false) {
						System.out.println("Invalid Characters in ID " + studentId);
					} 
					else if (studentId.length() != 5){
						System.out.println("Id needs to be 5 digits");
					}
					else {
						registry.removeStudent(studentId);
					}
				} catch (Exception e) {
					System.out.println("Missing student Id");
				}
			}


			// Adds a student to an active course (gets student id and course code string)
			// Makes sure that student id is only numeric and 5 digits
			// Handles null name/course code input
			else if (command.equalsIgnoreCase("ADDC")) {
				try{
				String studentId = commandLine.next();
				String courseCode = commandLine.next();
				if (isNumeric(studentId) == false) {
					System.out.println("Invalid Characters in ID " + studentId);
				} 
				else if (studentId.length() != 5){
					System.out.println("Id needs to be 5 digits");
				}				
				registry.addCourse(studentId, courseCode);
				} catch(Exception e){
					System.out.println("Missing the necessary parameter(s)");

				}
				

			// Drops a student from an active course (gets student id and course code string)
			// Makes sure that student id is only numeric and 5 digits	
			// Handles null name/course code input
			} else if (command.equalsIgnoreCase("DROPC")) {
				try{		
				String studentId = commandLine.next();
				String courseCode = commandLine.next();
				if (isNumeric(studentId) == false) {
					System.out.println("Invalid Characters in ID " + studentId);
				} 
				else if (studentId.length() != 5){
					System.out.println("Id needs to be 5 digits");
				}		
				registry.dropCourse(studentId, courseCode);
				} catch(Exception e){
					System.out.println("Missing the necessary parameter(s)");
				}


			// Prints all active courses
			} else if (command.equalsIgnoreCase("PAC")) {
				registry.printActiveCourses();


			//Prints class list (i.e. students) for this course (gets course code string)
			//Handles null course code input
			} else if (command.equalsIgnoreCase("PCL")) {
				try{
				String courseCode = commandLine.next();
				registry.printClassList(courseCode);
				} catch(Exception e){
					System.out.println("Missing the course code");
				}
		

			// Prints name, id and grade of all students in active course (gets course code string)
			// Handles null course code input
			} else if (command.equalsIgnoreCase("PGR")) {
				try{
				String courseCode = commandLine.next();
				registry.printGrades(courseCode);
				} catch(Exception e){
					System.out.println("Missing course code");
				}


			// Prints all active courses of students (gets student id string)	
			// Makes sure that student id is only numeric and 5 digits	
			// Handles null student Id input
			} else if (command.equalsIgnoreCase("PSC")) {
				try{
				String studentId = commandLine.next();
				if (isNumeric(studentId) == false) {
					System.out.println("Invalid Characters in ID " + studentId);
				} 
				else if (studentId.length() != 5){
					System.out.println("Id needs to be 5 digits");
				}
				else{
				registry.printStudentCourses(studentId);
				}
				} catch(Exception e){
					System.out.println("Missing student Id");
			}

			
			// Prints student transcript (gets student Id string)
			// Makes sure that student id is only numeric and 5 digits	
			// Handles null student Id input
			} else if (command.equalsIgnoreCase("PST")) {
				String studentId = commandLine.next();
				try{
				if (isNumeric(studentId) == false) {
					System.out.println("Invalid Characters in ID " + studentId);
				} 
				else if (studentId.length() != 5){
					System.out.println("Id needs to be 5 digits");
				}		
				else{			
				registry.printStudentTranscript(studentId);
				}
				} catch(Exception e){
					System.out.println("Missing student Id");
				} 
	

			// Sets final grade of student (gets course code, student Id and numeric grade)
			// Makes sure that student id is only numeric and 5 digits
			// Makes sure grade is of type double
			// Handles null inputs
			} else if (command.equalsIgnoreCase("SFG")) {
				try{
				String courseCode = commandLine.next();
				String studentId = commandLine.next();
				
				if (isNumeric(studentId) == false) {
					System.out.println("Invalid Characters in ID " + studentId);
				} 
				else if (studentId.length() != 5){
					System.out.println("Id needs to be 5 digits");
				}
				else if (commandLine.hasNextDouble() == false){
					System.out.println("Grade needs to be a number");
				}
				else{
				Double grade = commandLine.nextDouble();
				registry.setFinalGrade(courseCode, studentId, grade);
				}
				} catch(Exception e){
					System.out.println("Missing the necessary parameter(s)");
				}
				
	
			// Sorts list of students in course by name (i.e. alphabetically) (gets course code)
			// Handles null course code input
			} else if (command.equalsIgnoreCase("SCN")) {
				try{
				String courseCode = commandLine.next();
				registry.sortCourseByName(courseCode);
				} catch(Exception e){
					System.out.println("Missing course code");
				}


			// Sort list of students in course by student id (gets course code)
			// Handles null course code input
			} else if (command.equalsIgnoreCase("SCI")) {
				try{
				String courseCode = commandLine.next();
				registry.sortCourseById(courseCode);
				} catch(Exception e){
					System.out.println("Missing course code");
				}
			} 

			// Schedules an active course for the given day, start time and duration
			// Handles incorrect course code, day, start time, duration inputs
			// Handles lecture time collisions
			else if(command.equalsIgnoreCase("SCH")){
				try{
				String courseCode = commandLine.next();
				String day = commandLine.next();
				int start = commandLine.nextInt();
				int duration = commandLine.nextInt();
				scheduler.setDayAndTime(courseCode, day, start, duration);	

				}catch(Scheduler.unKnownCourseException e){
					System.out.println(e.getMessage());
				}
				catch(Scheduler.invalidDayException e){
					System.out.println(e.getMessage());
				}
				catch(Scheduler.invalidTimeException e){
					System.out.println(e.getMessage());
				}
				catch(Scheduler.invalidDurationException e){
					System.out.println(e.getMessage());	
				}
				catch(Scheduler.lectureTimeCollisionException e){
					System.out.println(e.getMessage());
				}
				catch(NoSuchElementException e){
					System.out.println("Missing the necessary parameter(s)");
				}
			}

			// Clear the scheduled times of the given course code
			else if(command.equalsIgnoreCase("CSCH")){
				String courseCode = commandLine.next();
				scheduler.clearSchedule(courseCode);
			}

			// Prints schedule
			else if (command.equalsIgnoreCase("PSCH")){
				scheduler.printSchedule();
			}
			else {
				System.out.println("Command not recognized");
			}
			System.out.print("\n>");
		}
	}

	// Catches the exception for file reading of students.txt if file was not found
	catch(FileNotFoundException e){
		System.out.println("students.txt File Not Found");
		System.exit(0);
	}

	// Catches the exception for file reading of students.txt if file had bad format
	catch(NoSuchElementException e){
		System.out.println("Bad File Format students.txt");
		System.exit(0);
	}
	}


	/**
	   @param str
	   @return true if string is only alphabetic characters, false if otherwise
	 */
	private static boolean isStringOnlyAlphabet(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}


	/**
	   @param str
	   @return true if string str contains only numeric characters, false if otherwise
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
