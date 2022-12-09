package com.examen.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkerPaymentRequest  implements Serializable {
    private Long employee_id;
    private String start_date;
    private String end_date;
}
