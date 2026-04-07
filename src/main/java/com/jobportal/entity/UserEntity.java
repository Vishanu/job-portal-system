package com.jobportal.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobportal.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String email;

    @Enumerated(EnumType.STRING)
	private Role role;
	private Long phone;
	private LocalDate createdDate;
	
	@OneToMany(mappedBy = "recruiter")
    @JsonIgnore
	private List<JobEntity> jobs;

    public UserEntity(Integer id){
        this.id = id;
    }


}
