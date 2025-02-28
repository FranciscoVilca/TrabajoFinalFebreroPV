package ar.edu.unju.edm.trabajofinalfebreropv.service;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje;
import ar.edu.unju.edm.trabajofinalfebreropv.model.ViajeDTO;

@Mapper(componentModel = "spring")
public interface ViajeDtoMapper {
	 // Método para mapear la lista de viajes
    @Mapping(target = "nombreApellidoConductor", ignore = true)
    List<ViajeDTO> viajesToViajeDTOs(List<Viaje> viajes);

    // Método para el mapeo de nombre y apellido después de realizar el mapeo
    @AfterMapping
    default void setNombreApellidoConductor(@MappingTarget ViajeDTO viajeDTO, Viaje viaje) {
        if (viaje.getConductor() != null) {
            viajeDTO.setNombreApellidoConductor(viaje.getConductor().getNombre() + " " + viaje.getConductor().getApellido());
        } else {
            viajeDTO.setNombreApellidoConductor(""); 
        }
    }
}
