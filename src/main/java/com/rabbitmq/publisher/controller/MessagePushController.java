package com.rabbitmq.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.publisher.model.Employee;
import com.rabbitmq.publisher.sender.RabbitMqSender;

@RestController
@RequestMapping(value = "/send-rabbitmq/")
public class MessagePushController {
	@Autowired
	RabbitMqSender rabbitMQSender;
	
	@GetMapping(value = "/producer")
	public String sendMessage(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		System.out.println("Emp Id:"+emp.getEmpId());
		rabbitMQSender.send(emp);
		return emp.getEmpName();
	}
	
	@RequestMapping(value="/getVal")
	public String getValue() {
		return"Bholanath";
	}

}
