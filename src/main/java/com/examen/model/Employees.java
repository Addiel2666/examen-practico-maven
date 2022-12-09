package com.examen.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pruebas.EMPLOYEES")
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @JoinColumn(name = "GENDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Genders genderId;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jbs jobId;
    @OneToMany(mappedBy = "employeeId")
    private List<EmployeeWorkedHours> employeeWorkedHoursList;
}
