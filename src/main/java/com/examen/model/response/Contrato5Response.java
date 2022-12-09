package com.examen.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contrato5Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double payment;
    private Boolean success;
}
