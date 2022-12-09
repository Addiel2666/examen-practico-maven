package com.examen.service;

import com.examen.model.request.ContratoRequest;
import com.examen.model.request.EmpleadoRequest;
import com.examen.model.request.HilosRequest;
import com.examen.model.request.WorkerPaymentRequest;
import com.examen.model.response.*;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {


    ResponseEntity<Contrato1Response> guardaEmpleado(EmpleadoRequest request);

    ResponseEntity<Contrato2Response> listaPorPuesto(ContratoRequest request);

    ResponseEntity<Contrato3Response> multihilo(HilosRequest request);

    ResponseEntity<Contrato4Response> horasTrabajadas(WorkerPaymentRequest request);

    ResponseEntity<Contrato5Response> horasPagadas(WorkerPaymentRequest request);
}
