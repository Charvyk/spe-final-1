package esd.academia.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private long student_id;
	
	@Column(name="roll")
	private String rollnumber;
	
	@Column(name="fname", nullable=false)
	private String firstname;
	
	@Column(name="lname")
	private String lastname;
	
	@Column(name="email",unique = true, nullable=false)
	private String email;
	
	@Column(name="photo")
	private String photourl;
	
	@Column(name="cgpa", nullable=false)
	private double cgpa = 0.0;
	
	@Column(name="credits", nullable=false)
	private int totalcredits;
	
	@Column(name="gradyear")
	private int graduationYear;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="s_batch_code")
	private Batch s_batch_code;
	
	@ManyToMany
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> enrolledCourses;

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public int getTotalcredits() {
		return totalcredits;
	}

	public void setTotalcredits(int totalcredits) {
		this.totalcredits = totalcredits;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public Set<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void setEnrolledCourses(Set<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public Batch getS_batch_code() {
		return s_batch_code;
	}

	public void setS_batch_code(Batch s_batch_code) {
		this.s_batch_code = s_batch_code;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", rollnumber=" + rollnumber + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", photourl=" + photourl + ", cgpa=" + cgpa
				+ ", totalcredits=" + totalcredits + ", graduationYear=" + graduationYear + ", enrolledCourses="
				+ enrolledCourses + "]";
	}

	public Student() {
		super();
	}
	
	@PostPersist
	public void generateRollnumber() {
		this.rollnumber = this.s_batch_code.getBatch_id() + String.format("%03d", this.student_id);
	}	

}
