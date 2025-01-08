package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.employee.entity.Employee;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j

public class RestClientController {
	private RestClient restClient;

	@Value("${my.property}")
	private String restClientURL;

	@PostConstruct
	public void init() {

		restClient = RestClient.builder().baseUrl(restClientURL).build();
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		log.info("addEmployee is called in controller");
		return restClient.post().uri("/add").contentType(MediaType.APPLICATION_JSON).body(employee).retrieve()
				.body(Employee.class);

	}

	@GetMapping("/fetchList")
	public List<Employee> getEmpList() {
		return restClient.get().uri("/fetchList").retrieve().body(new ParameterizedTypeReference<List<Employee>>() {
		});

	}

	@GetMapping("/fetchById/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return restClient.get().uri("/fetchById/{id}", id).retrieve().body(Employee.class);

	}

	@DeleteMapping("/deleteById/{id}")
	public String deleteEmployee(@PathVariable int id) {
		restClient.delete().uri("/deleteById/{id}", id).retrieve().toBodilessEntity();
		return "Employee removed : " + id;
	}

	@PutMapping("/updateById/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
		return restClient.put().uri("/updateById/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.body(updatedEmployee).retrieve().body(Employee.class);

	}
}
