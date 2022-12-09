package com.examen.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Contrato3Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<EmployeesModel> employees;
    private Boolean success;

}
