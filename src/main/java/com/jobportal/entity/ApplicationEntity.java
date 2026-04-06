package com.jobportal.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobportal.enums.ApplicationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applications")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="job_id")
	private JobEntity job;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	private String resumeUrl;
	private ApplicationStatus status;
	private LocalDate appliedDate;
}
