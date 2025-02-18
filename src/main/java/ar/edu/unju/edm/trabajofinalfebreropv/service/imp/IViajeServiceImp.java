package ar.edu.unju.edm.trabajofinalfebreropv.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajofinalfebreropv.Repository.ViajeRepository;
import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje;
import ar.edu.unju.edm.trabajofinalfebreropv.service.IViajeService;

@Service
public class IViajeServiceImp implements IViajeService {
  @Autowired
  ViajeRepository viajeRepository;

  @Override
  public void guardarViaje(Viaje viaje) {
    viaje.setEstado(true);
    viajeRepository.save(viaje);
  }

  @Override
  public Viaje buscarViaje(Long id) {
    return viajeRepository.findById(id).orElse(null);
  }

  @Override
  public void eliminarViaje(Long id) {
    Viaje viajeEliminado = buscarViaje(id);
    viajeEliminado.setEstado(false);
    viajeRepository.save(viajeEliminado);
  }

  @Override
  public Viaje modificarViaje(Viaje viaje) {
    Viaje viajeAModificar = buscarViaje(viaje.getId());
    viajeAModificar = viaje;
    return viajeRepository.save(viajeAModificar);
  }

  @Override
  public List<Viaje> listarViajesActivos() {
    List<Viaje> listaViajes = (List<Viaje>) viajeRepository.findAll();
    List<Viaje> listaViajesActivos = new ArrayList<Viaje>();
    for (Viaje v : listaViajes) {
      if (v.getEstado() == true) {
        listaViajesActivos.add(v);
      }
    }
    return listaViajesActivos;
  }

  @Override
  public List<Viaje> listarViajes() {
    return (List<Viaje>) viajeRepository.findAll();
  }

}
