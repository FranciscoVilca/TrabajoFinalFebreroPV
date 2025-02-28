package ar.edu.unju.edm.trabajofinalfebreropv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unju.edm.trabajofinalfebreropv.model.Conductor;

import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje;
import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje.Tipo;
import ar.edu.unju.edm.trabajofinalfebreropv.model.ViajeDTO;
import ar.edu.unju.edm.trabajofinalfebreropv.service.IConductorService;
import ar.edu.unju.edm.trabajofinalfebreropv.service.IViajeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViajeController {
  @Autowired
  IViajeService viajeService;
  @Autowired
  IConductorService conductorService;

  @GetMapping("/nuevoViaje")
  public ModelAndView getNuevoViajePage() {
    ModelAndView modelView = new ModelAndView("formularioViaje");
    modelView.addObject("viaje", new Viaje());
    modelView.addObject("tipos", List.of("Corta", "Media", "Larga"));
    modelView.addObject("conductores", conductorService.listarConductoresActivos());
    return modelView;
  }

  @PostMapping("/viajes/precio")
  public String postVerificarViaje(@RequestParam("conductorId") Long conductorId,@RequestParam("tipo") String tipo, @ModelAttribute Viaje viaje, RedirectAttributes redirectAttributes) {
    try {

      viaje.setConductor(conductorService.buscarConductor(conductorId));
      viaje.setTipo(Tipo.valueOf(tipo));
      
      Integer precio = 0;
      if (viaje.getTipo().toString() == "Corta") {
        precio = 7000;
      } else {
        if (viaje.getTipo().toString() == "Media") {
          precio = 10000;
        } else {
          precio = 20000;
        }
      }
      if (viaje.getConductor().getTipoAutomovil().toString() == "Luxe") {
        precio = precio + (precio * 10 / 100);
      } else {
        if (viaje.getConductor().getTipoAutomovil().toString() == "Premium") {
          precio = precio + (precio * 20 / 100);
        }
      }
  
      viaje.setCosto(precio);
      viaje.getConductor().setEstado(false);
      viajeService.guardarViaje(viaje);

      
      return "redirect:/viajes";
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());

      return "redirect:/nuevoViaje";
      
    }
  }

  @GetMapping("/viajes")
  public ModelAndView getListaViajes() {
    ModelAndView modelView = new ModelAndView("listadoViajes");
    List<ViajeDTO> viajesDTO = viajeService.listarViajesActivosDTO();
    modelView.addObject("viajes", viajesDTO); 
    return modelView;
  }
  
  @GetMapping("/viajes/eliminar/{id}")
  public String eliminarViaje(@ModelAttribute Viaje viaje) {
	viaje =  viajeService.buscarViaje(viaje.getId());
	viaje.getConductor().setEstado(true);
    viajeService.eliminarViaje(viaje.getId());
    return "redirect:/viajes";
  }
}
