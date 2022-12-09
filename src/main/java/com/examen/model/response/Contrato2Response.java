package com.examen.model.response;

import com.examen.model.Employees;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Contrato2Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Employees> employees;

}
