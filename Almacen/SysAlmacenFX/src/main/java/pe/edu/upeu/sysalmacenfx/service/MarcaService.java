package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.model.Categoria;
import pe.edu.upeu.sysalmacenfx.model.Marca;
import pe.edu.upeu.sysalmacenfx.repository.MarcaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaService {
    @Autowired
    MarcaRepository service;
    //C
    public Marca save(Marca to) {
        return service.save(to);
    }
    //R
    public List<Marca> listIt() {
        return service.findAll();
    }
    //U
    public Marca update(Marca to, Long id) {
        try {
            Marca toExistent = service.findById(id).get();
            if (toExistent != null) {
                toExistent.setNombre(to.getNombre());
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
    public Marca search(Long id) {
        return service.findById(id).get();
    }

    public List<ComboBoxOption> listaCategoriaCombobox(){
        List<ComboBoxOption> listar=new ArrayList<>();
        for (Marca marca : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(marca.getIdMarca()),
                    marca.getNombre()));
        }
        return listar;
    }
}
