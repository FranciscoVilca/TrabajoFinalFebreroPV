package ar.edu.unju.edm.trabajofinalfebreropv.service;

import java.util.List;

import ar.edu.unju.edm.trabajofinalfebreropv.model.Conductor;

public interface IConductorService {
  public void guardarConductor(Conductor conductor);

  public List<Conductor> listarConductoresActivos();

  public void eliminarConductor(Long id);

  public Conductor modificarConductor(Conductor conductor);

  public Conductor buscarConductor(Long id);
}
