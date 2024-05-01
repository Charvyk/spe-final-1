package esd.academia.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private long course_id;
	
	@Column(name="code", unique = true, nullable=false)
	private String coursecode;
	
	@Column(name="name", nullable=false)
	private String coursename;
	
	@Column(name="description")
	private String description;
	
	@Column(name="credits", nullable=false)
	private int credits;
	
	@Column(name="capacity")
	private int capacity;	
	
	@ManyToOne
	@JoinColumn(name="fac_id", nullable=false)
//	@JsonIgnore
	private Faculty facultyId;
	
	@ManyToOne
	@JoinColumn(name="spec_id")
//	@JsonIgnore
	private Specialization specId;
	
	@ManyToMany(mappedBy = "enrolledCourses")
//	@JsonIgnore
	private Set<Student> enrolledStudents;
	
	@ManyToMany(cascade = CascadeType.MERGE )
    @JoinTable(
        name = "course_prerequisite",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "prereq_course_id")
    )
//	@JsonIgnore
	private Set<Course> prerequisites = new HashSet<>();

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Course() {
		super();
	}

	public Course(long course_id) {
		super();
		this.course_id = course_id;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", courseCode=" + coursecode + ", courseName=" + coursename
				+ ", description=" + description + ", credits=" + credits + ", capacity=" + capacity +  "]";
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public String getCourseCode() {
		return coursecode;
	}

	public void setCourseCode(String courseCode) {
		this.coursecode = courseCode;
	}

	public String getCourseName() {
		return coursename;
	}

	public void setCourseName(String courseName) {
		this.coursename = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Faculty getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Faculty facultyId) {
		this.facultyId = facultyId;
	}

	public Specialization getSpecId() {
		return specId;
	}

	public void setSpecId(Specialization specId) {
		this.specId = specId;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Set<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Set<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
	


}
