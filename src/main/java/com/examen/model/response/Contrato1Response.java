

/**
* Banco Santander (Mexico) S.A., Institucion de Banca Multiple, Grupo Financiero Santander Mexico
* Todos los derechos reservados
* Contrato1Response.java
* Control de versiones:
* Version  Date/Hour               By                  				Company     Description
* -------  -------------------     --------------------------------    --------    -----------------------------------------------------------------
* 1.0      9 dic. 2022 00:26:23	        [Addiel]                           VASS		Creacion de Contrato1Response.java
*/
	 
package com.examen.model.response;


import lombok.Data;

import java.io.Serializable;

/**
* Descripcion:
*
* @author Addiel
*
*/
@Data
public class Contrato1Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Boolean success;

}

	