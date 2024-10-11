package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.model.Categoria;
import pe.edu.upeu.sysalmacenfx.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repo;
    //C
    public Categoria save(Categoria to) {
        return repo.save(to);
    }
    //R
    public List<Categoria> listIt() {
        return repo.findAll();
    }
    //U
    public Categoria update(Categoria to, Long id) {
        try {
            Categoria toExistent = repo.findById(id).get();
            if (toExistent != null) {
                toExistent.setNombre(to.getNombre());
            }else{}
            return repo.save(toExistent);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    //D
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Categoria search(Long id) {
        return repo.findById(id).get();
    }

    public List<ComboBoxOption> listaCombobox(){
        List<ComboBoxOption> listar=new ArrayList<>();
        for (Categoria cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCategoria()),
                    cate.getNombre()));
        }
        return listar;
    }
}
