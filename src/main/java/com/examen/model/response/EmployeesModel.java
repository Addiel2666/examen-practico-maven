package com.examen.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeesModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long gender_id;
    private Long job_id;
    private String name;
    private String last_name;
    private String birthdate;
}
