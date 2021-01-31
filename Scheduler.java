// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

import java.util.TreeMap;
import java.util.Arrays; 


public class Scheduler 
{

	TreeMap<String,ActiveCourse> courses;
	
	/**
	 *  A constructor initializing courses of scheduler to the given parameter
	 * @param courses
	 */
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  this.courses = courses;
	}
	
	/**
	 * Sets the following parameter for an Active Course object.
	 * The method checks for custom exceptions to ensure if there are valid inputs for the parameters and 
	 * throws an error if otherwise (errors are caught in StudentRegistrySimulator).
	 * @param courseCode
	 * @param day
	 * @param startTime
	 * @param duration
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		if(unknownCourse(courseCode)){
			throw new unKnownCourseException("Unknown course: " + courseCode);
		}

		if(invalidDay(day)){
			throw new invalidDayException("Invalid Lecture Day");
		}

		if(invalidTime(startTime, duration)){
			throw new invalidTimeException("Invalid Lecture Start Time");
		}

		if(invalidDuration(duration)){
			throw new invalidDurationException("Invalid Lecture Duration");
		}

		if(lectureTimeCollision(day,startTime,duration)){
			throw new lectureTimeCollisionException("Lecture Time Collision");
		}
		ActiveCourse activeCourse = courses.get(courseCode);
		activeCourse.setScheduleVariable(startTime, duration, day);
	}

	/**
	 * A method to clear the scheduled times of a given active course 
	 * @param courseCode
	 */
	public void clearSchedule(String courseCode)
	{
		if(courses.containsKey(courseCode)){
			ActiveCourse ac = courses.get(courseCode);
			ac.setScheduleVariable(0, 0, "");

		}
	}
	 	
	/**
	 * A method to print schedule
	 */
	public void printSchedule()
	{
		String[][] scheduleTable = new String[9][5];

		for (String[] row : scheduleTable){ 
			Arrays.fill(row, "      "); 
		}
		
		// Schedules all the active courses that are to be scheduled (duration > 0) in the 2d array schedule table
		// Converts lecture days to column indices 
		// Converts lecture start times + end times to row indices (using duration to calculate the end times)
		for (ActiveCourse ac : courses.values()){
			if(ac.getLectureDuration() > 0){
				int column = 0;
				int startRow = ((ac.getLectureStart() - 800)/100);
				int endRow = startRow + (ac.getLectureDuration());
				if(ac.getLectureDay().equalsIgnoreCase("Mon")){
					column = 0;
				}
				else if(ac.getLectureDay().equalsIgnoreCase("Tue")){
					column = 1;
				}
				else if(ac.getLectureDay().equalsIgnoreCase("Wed")){
					column = 2;
				}
				else if(ac.getLectureDay().equalsIgnoreCase("Thu")){
					column = 3;
				}
				else{
					column = 4;
				}
				for (int row = startRow; row < endRow; row++){
					scheduleTable[row][column] = ac.getCode();

				}
			}
		}

		// Header for the days of schedule table
		System.out.println("\t" + " mon  " + "\t" + " tue  " + "\t" +  " wed  " + "\t" + " thu  " + "\t" + " fri  ");

		// Prints the schedule table
		for(int row = 0; row < scheduleTable.length ; row++){
			int time = ((row + 8) * 100);
			if(time < 1000){
				System.out.print("0"+ time);
			}
			else{System.out.print(time);}
			for(int col = 0; col < scheduleTable[row].length; col++){
				System.out.print("\t" + scheduleTable[row][col]);
			}
			System.out.println("");
			}		
			
	}
	
	/**
      A method to check whether a courseCode is an active course.
      @param courseCode
      @return true if the courseCode is not an active course (an unknown course)
	*/  
	private boolean unknownCourse(String courseCode){
		if(courses.containsKey(courseCode)){
			return false;
		}
		return true;
	}

	/**
      A method to check whether a day is an invalid lecture day.
      @param day
      @return true if the day is an invalid lecture day
	*/  	
	private boolean invalidDay(String day){
		if(day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Tue") || day.equalsIgnoreCase("Wed") || day.equalsIgnoreCase("Thur") || day.equalsIgnoreCase("Fri")){
			return false;
		}
		return true;
	}  

	/**
      A method to check the start time and end time (end time being calculated in respect to the given duration) is invalid.
	  @param startTime
	  @param duration
      @return true if the startTime and duration result in invalid time
	*/  	
	private boolean invalidTime(int startTime, int duration){
		if (startTime >= 800 && (startTime + duration * 100) < 1800){
			return false;
		}
		return true;

	}

 	/**
      A method to check whether the lecture duration is invalid
      @param student
      @return true if the lecture duration is invalid
	 */  	
	private boolean invalidDuration(int duration){
		if (duration == 1 || duration == 2 || duration == 3){
			return false;
		}
		return true;
	}

 	/**
      A method to check whether there is a lecture time collision between two lectures when scheduling a lecture
      @param student
      @return true if there is a lecture time collision
	 */  	
	private boolean lectureTimeCollision(String day, int startTime, int duration)
	{	
		for (ActiveCourse ac : courses.values()){
			if(day.equalsIgnoreCase(ac.getLectureDay())){
				if(((startTime + (duration * 100)) > ac.getLectureStart()) && (startTime < (ac.getLectureStart() + (ac.getLectureDuration() * 100)))){
					return true;
				}		
			}
		}
		return false;
	}
	
 	// A custom exception for an unknown course 
	public class unKnownCourseException extends RuntimeException{
		public unKnownCourseException(){}
		public unKnownCourseException(String message){
			super(message);
		}
	}

 	// A custom exception for an invalid day
	public class invalidDayException extends RuntimeException{
		public invalidDayException(){}
		public invalidDayException(String message){
			super(message);
		}
	}


 	// A custom exception for an invalid time	
	public class invalidTimeException extends RuntimeException{
		public invalidTimeException(){}
		public invalidTimeException(String message){
			super(message);
		}
	}

 	// A custom exception for an invalid duration
	public class invalidDurationException extends RuntimeException{
		public invalidDurationException(){}
		public invalidDurationException(String message){
			super(message);
		}
	}

 	// A custom exception for a lecture collision
	public class lectureTimeCollisionException extends RuntimeException{
		public lectureTimeCollisionException(){}
		public lectureTimeCollisionException(String message){
			super(message);
		}
	}
}
