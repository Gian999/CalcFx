package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.model.UnidadMedida;
import pe.edu.upeu.sysalmacenfx.repository.UnidadMedidaRepository;
import pe.edu.upeu.sysalmacenfx.repository.UnidadMedidaRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class UnidadMedidaService {
    @Autowired
    UnidadMedidaRepository service;
    //C
    public UnidadMedida save(UnidadMedida to) {
        return service.save(to);
    }
    //R
    public List<UnidadMedida> listIt() {
        return service.findAll();
    }
    //U
    public UnidadMedida update(UnidadMedida to, Long id) {
        try {
            UnidadMedida toExistent = service.findById(id).get();
            if (toExistent != null) {
                toExistent.setNombreMedida(to.getNombreMedida());
            }else{}
            return service.save(toExistent);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //D
    public void delete(Long id) {
        service.deleteById(id);
    }
    public UnidadMedida search(Long id) {
        return service.findById(id).get();
    }
    public List<ComboBoxOption> listaCategoriaCombobox(){
        List<ComboBoxOption> listar=new ArrayList<>();
        for (UnidadMedida unidadMed : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(unidadMed.getIdUnidad()),
                    unidadMed.getNombreMedida()));
        }
        return listar;
    }
}
