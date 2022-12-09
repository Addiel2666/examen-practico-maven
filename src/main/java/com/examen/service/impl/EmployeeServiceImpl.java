package com.examen.service.impl;

import com.examen.model.EmployeeWorkedHours;
import com.examen.model.Employees;
import com.examen.model.Genders;
import com.examen.model.Jbs;
import com.examen.model.request.ContratoRequest;
import com.examen.model.request.EmpleadoRequest;
import com.examen.model.request.HilosRequest;
import com.examen.model.request.WorkerPaymentRequest;
import com.examen.model.response.*;
import com.examen.repository.IEmployeeWorkerReposotory;
import com.examen.service.IEmployeeService;
import com.examen.repository.IEmployeeRepository;
import com.examen.repository.IGenderRepository;
import com.examen.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Autowired
    IJobRepository iJobRepository;

    @Autowired
    IGenderRepository iGenderRepository;

    @Autowired
    IEmployeeWorkerReposotory iEmployeeWorkerReposotory;

    @Override
    public ResponseEntity<Contrato1Response> guardaEmpleado(EmpleadoRequest request) {
        Contrato1Response response = new Contrato1Response();
        Employees employeesInsert = new Employees();
        try {
            Employees employees = iEmployeeRepository.getSearchForNameLastName(request.getName(),request.getLast_name());
            if(employees != null){
                response.setId(Long.parseLong("0"));
                response.setSuccess(false);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            boolean mayorEdad = metodoAnios(request.getBirthdate());
            if (mayorEdad == true) {
                Optional<Jbs> jbs = iJobRepository.buscaId(request.getJob_id());
                Optional<Genders> genders = iGenderRepository.buscaId(request.getGender_id());
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthdate());

            employeesInsert.setBirthdate(date);
                employeesInsert.setName(request.getName());
                employeesInsert.setLastName(request.getLast_name());
                employeesInsert.setJobId(jbs.get());
                employeesInsert.setGenderId(genders.get());
                employeesInsert = iEmployeeRepository.save(employeesInsert);
                iEmployeeRepository.flush();
                response.setId(employeesInsert.getId());
                response.setSuccess(true);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.setId(Long.parseLong("0"));
                response.setSuccess(false);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }catch (Exception e){
            response.setId(Long.parseLong("0"));
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Contrato2Response> listaPorPuesto(ContratoRequest request) {
       Contrato2Response response = new Contrato2Response();
       try {
           Optional<Jbs> jbs = iJobRepository.buscaId(request.getJob_id());
           List<Employees> lista = iEmployeeRepository.findByPuesto(jbs.get());
           if(lista != null) {
               response.setEmployees(lista);
               return ResponseEntity.status(HttpStatus.OK).body(response);
           }else{
               response.setEmployees(null);
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
           }
       }catch (Exception e){
           response.setEmployees(null);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }

    @Override
    public ResponseEntity<Contrato3Response> multihilo(HilosRequest request) {
        Contrato3Response response = new Contrato3Response();
        List<EmployeesModel> listaEmployees = new ArrayList<>();
        try {
            if(request.getEmployee_id().length > 0) {
                for (Long recorrido : request.getEmployee_id()) {
                    Employees employees = iEmployeeRepository.getSearchById(recorrido);
                    EmployeesModel employeesModel = new EmployeesModel();
                    employeesModel.setBirthdate(this.dateToString(employees.getBirthdate()));
                    employeesModel.setName(employees.getName());
                    employeesModel.setGender_id(employees.getGenderId().getId());
                    employeesModel.setJob_id(employees.getJobId().getId());
                    employeesModel.setLast_name(employees.getLastName());
                    listaEmployees.add(employeesModel);
                }
                response.setEmployees(listaEmployees);
                response.setSuccess(true);
            }else{
                response.setSuccess(false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Contrato4Response> horasTrabajadas(WorkerPaymentRequest request) {
        Contrato4Response response=new Contrato4Response();
        try{
            Date inicio = this.StringToDate(request.getStart_date());
            Date fin = this.StringToDate(request.getEnd_date());
            EmployeeWorkedHours employeeWorkedHours = iEmployeeWorkerReposotory.findByIdAndDates(request.getEmployee_id(), inicio, fin);
            if(employeeWorkedHours !=null){
                response.setTotal_worked_hours(employeeWorkedHours.getWorkedHours());
                response.setSuccess(true);
            }else{
                response.setTotal_worked_hours(Long.parseLong("0"));
                response.setSuccess(false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
       }catch (Exception e){
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
    }

    @Override
    public ResponseEntity<Contrato5Response> horasPagadas(WorkerPaymentRequest request) {
        Contrato5Response response = new Contrato5Response();
        try {
            Employees employees = iEmployeeRepository.getSearchById(request.getEmployee_id());
            if(employees != null){
                response.setPayment(employees.getJobId().getSalary());
                response.setSuccess(true);
            }else{
                response.setSuccess(false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private boolean metodoAnios(String birthdate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        LocalDate start = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(fin, start);
        if(period.getYears() >= 18){
            return true;
        }
        return false;
    }

    private String dateToString(Date fecha){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(fecha);
        return strDate;
    }

    private Date StringToDate(String fecha) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        return date;
    }
}
