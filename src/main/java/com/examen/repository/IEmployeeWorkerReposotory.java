package com.examen.repository;

import com.examen.model.EmployeeWorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IEmployeeWorkerReposotory extends JpaRepository<EmployeeWorkedHours, Long> {
    @Query(value = "SELECT * FROM PRUEBAS.EMPLOYEE_WORKED_HOURS WHERE ID =:idEmp AND WORKED_DATE BETWEEN :ini AND :fin", nativeQuery = true)
    EmployeeWorkedHours findByIdAndDates(@Param("idEmp") Long employee_id, @Param("ini") Date inicio, @Param("fin") Date fin);
}
