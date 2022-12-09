package com.examen.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Table(name = "pruebas.EMPLOYEE_WORKED_HOURS")
public class EmployeeWorkedHours implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "WORKED_HOURS")
    private Long workedHours;
    @Column(name = "WORKED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workedDate;
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Employees employeeId;
}
