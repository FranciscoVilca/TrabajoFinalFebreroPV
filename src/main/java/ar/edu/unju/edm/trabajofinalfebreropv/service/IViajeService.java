package ar.edu.unju.edm.trabajofinalfebreropv.service;

import java.util.List;



import ar.edu.unju.edm.trabajofinalfebreropv.model.Viaje;
import ar.edu.unju.edm.trabajofinalfebreropv.model.ViajeDTO;

public interface IViajeService {
  public void guardarViaje(Viaje viaje);

  public Viaje buscarViaje(Long id);

  public void eliminarViaje(Long id);

  public Viaje modificarViaje(Viaje viaje);

  public List<Viaje> listarViajesActivos();
  public List<Viaje> listarViajes();
  public List<ViajeDTO> listarViajesActivosDTO(); //metodo para dto
}
