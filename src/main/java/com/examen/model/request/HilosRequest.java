package com.examen.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class HilosRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long[] employee_id;
    private String start_date;
    private String end_date;

}
