package esd.academia.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Entity
@Table(name="specialization")
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spec_id")
	private long spec_id;
	
	@Column(name="code", unique = true, nullable = false)
	private String code;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="credits_req", nullable = false)
	private int credits_req;
	
	@OneToMany(mappedBy = "specId")
	@JsonIgnore
	private Set<Course> associatedCourses = new HashSet<>();

	public Specialization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Specialization(long spec_id) {
		super();
		this.spec_id = spec_id;
	}

	public long getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(long spec_id) {
		this.spec_id = spec_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits_req() {
		return credits_req;
	}

	public void setCredits_req(int credits_req) {
		this.credits_req = credits_req;
	}

	public Set<Course> getAssociatedCourses() {
		return associatedCourses;
	}

	public void setAssociatedCourses(Set<Course> associatedCourses) {
		this.associatedCourses = associatedCourses;
	}

}
