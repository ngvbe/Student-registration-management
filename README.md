# Student-registration-management
A java program to implement different classes, objects and methods to solve a real life problem of managing the information of registered students.

HOW TO USE (DIFFERENT COMMANDS):
 - “L” : Lists all the students in the registry.
	> FORMAT: L

 - “Q” : Quits out of the program.
	> FORMAT: Q 

 - “REG” : Registers a student.
	> FORMAT: REG [student's name] [student's ID] 

 - “DEL” : Deletes a student from the registry. 
	> FORMAT: DEL [student's ID]

 - “ADDC” : Adds a student to an active course.
	> FORMAT: ADDC [student's ID] [course code] 

 - “DROPC” : Drops a student from an active course
	> FORMAT: DROPC [student's ID] [course code] 

 - “PAC” : prints all active course
	> FORMAT: PAC

 - “PCL” : prints class list for an active course
	> FORMAT: PCL [course code]

 - “PGR” : prints student id and grade for all students in an active course
	> FORMAT: PGR [course code]

 - “PSC” : prints all credit courses for a student
	> FORMAT: PSC [student's ID]

 - “SFG” : Set final grade of a student in a course
	> FORMAT: SFG [course code] [student's ID] [Grade in numerical representation]

 - “SCN” : sort list of students in a course by student name
	> FORMAT: SCN [course code]

 - “SCI” : sort list of students in a course by student id 
	> FORMAT: SCI [course code]

 - “SCH” : Schedules a course for a certain day, start time and duration. 
	> FORMAT: SCH [course code] [day] [start time]  [duration]
		- [day]: Mon, Tue, Wed, Thur, Fri
		- [start time]: 0800-1700
		- [duration]: 1-3

 - “CSCH” : Clears the schedule of the given course.
	> FORMAT: CSCH [course code ]

 - “PSCH” : Prints the entire schedule.
	> FORMAT: PSCH
