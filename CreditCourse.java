// Name: Sholeh Sepehri-Boroujeni
// Student ID: 500975452

public class CreditCourse extends Course {
	private String semester;
	public double grade;
	public boolean active;

   /**
      A constructor method with appropriate parameters using the super class constructor to initialize inherited variables
      @param name
      @param code
      @param descr
      @param semester
      @param students
    */	
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade) {
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
	}

	/**
		Default constructor
	 */
	public CreditCourse() {
	}

	/**
		@return true if the CreditCourse is active and false if otherwise
	 */
	public boolean getActive() {
		return active;
	}

	/**
		Sets active to true
	 */
	public void setActive() {
		active = true;
	}

	/**
		Sets active to false
	 */
	public void setInactive() {
		active = false;
	}

	/**
		Prints information about this course plus which semester and the grade achieved
	 */	
	public String displayGrade() {
		return getInfo() + " " + semester + " Grade " + convertNumericGrade(grade);
	}

}
