package ar.edu.unju.edm.trabajofinalfebreropv.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajofinalfebreropv.Repository.ConductorRepository;
import ar.edu.unju.edm.trabajofinalfebreropv.model.Conductor;
import ar.edu.unju.edm.trabajofinalfebreropv.service.IConductorService;

@Service
public class IConductorServiceImp implements IConductorService {
  @Autowired
  ConductorRepository conductorRepository;

  @Override
  public void guardarConductor(Conductor conductor) {
    conductorRepository.save(conductor);
  }

  @Override
  public List<Conductor> listarConductoresActivos() {
    List<Conductor> listaConductores = (List<Conductor>) conductorRepository.findAll();
    List<Conductor> listaConductoresActivos = new ArrayList<>();
    for (Conductor conductor : listaConductores) {
      if (conductor.getEstado() == true) {
        listaConductoresActivos.add(conductor);
      }
    }
    return listaConductoresActivos;
  }

  @Override
  public void eliminarConductor(Long id) {
    Conductor conductorEliminado = buscarConductor(id);
    conductorEliminado.setEstado(false);
    conductorRepository.save(conductorEliminado);
  }

  @Override
  public Conductor modificarConductor(Conductor conductor) {
    Conductor conductorAModificar = buscarConductor(conductor.getId());
    conductorAModificar = conductor;
    return conductorRepository.save(conductorAModificar);
  }

  @Override
  public Conductor buscarConductor(Long id) {
    return conductorRepository.findById(id).orElse(null);
  }

}
