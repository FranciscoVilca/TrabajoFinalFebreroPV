package ar.edu.unju.edm.trabajofinalfebreropv.model;


import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ViajeDTO {
	private Long id;            
    private String tipo;      
    private Integer costo;       
    private String nombreApellidoConductor; 

}
