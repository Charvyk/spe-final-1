package esd.academia.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="batch")
public class Batch {
	
	@Id
	@Column(name="batch_id")
	private String batch_id;
	
	@Column(name="degree", nullable = false)
	private String degree;
	
	@Column(name="year", nullable = true)
	private int year;	
	
	@Column(name="batch_code")
	private String batch_code;
	
	@OneToMany(mappedBy = "s_batch_code")
	private Set<Student> batchStudents = new HashSet<Student>();
	
	@PrePersist
	public void generateBatchCode() {
		this.batch_id = this.degree + Integer.toString(this.year);		
	}

	public Batch() {
		super();
	}	

	public Batch(String batch_id) {
		super();
		this.batch_id = batch_id;
	}

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getBatch_code() {
		return batch_code;
	}

	public void setBatch_code(String batch_code) {
		this.batch_code = batch_code;
	}

	@Override
	public String toString() {
		return "Batch [batch_id=" + batch_id + ", degree=" + degree + ", year=" + year + ", batch_code=" + batch_code
				+ ", batchStudents=" + batchStudents + "]";
	}

}
