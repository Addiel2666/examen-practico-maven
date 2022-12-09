package com.examen.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contrato4Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total_worked_hours;
    private Boolean success;
}
