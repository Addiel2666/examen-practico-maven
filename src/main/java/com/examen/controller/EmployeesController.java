
	 
package com.examen.controller;

import com.examen.model.request.ContratoRequest;
import com.examen.model.request.EmpleadoRequest;
import com.examen.model.request.HilosRequest;
import com.examen.model.request.WorkerPaymentRequest;
import com.examen.model.response.*;
import com.examen.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Descripcion:
*
* @author Addiel
*
*/
@RestController
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	IEmployeeService employeeService;

	@PostMapping(value = "/inserta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contrato1Response> registraEmpleado(@RequestBody EmpleadoRequest request){
		return employeeService.guardaEmpleado(request);
	}

	@PostMapping(value = "/puesto", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contrato2Response> puestoEmpleado(@RequestBody ContratoRequest request){
		return employeeService.listaPorPuesto(request);
	}

	@PostMapping(value = "/multihilo", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contrato3Response> puestoEmpleado(@RequestBody HilosRequest request){
		return employeeService.multihilo(request);
	}

	@PostMapping(value = "/horas", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contrato4Response> puestoEmpleado(@RequestBody WorkerPaymentRequest request){
		return employeeService.horasTrabajadas(request);
	}

	@PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contrato5Response> pagoEmpleado(@RequestBody WorkerPaymentRequest request){
		return employeeService.horasPagadas(request);
	}
	
}

	