package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name="EMP_DETAILS")
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	

}
