package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jobs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	private Double salary;
	private String location;
	private String companyName;
	private LocalDate postedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recruiter_id")
    @JsonIgnoreProperties({"jobs"})
	private UserEntity recruiter;
}
