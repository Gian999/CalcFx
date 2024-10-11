package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.model.Producto;
import pe.edu.upeu.sysalmacenfx.repository.ProductoRepository;


import java.util.List;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository service;
    //C
    public Producto save(Producto to) {
        return service.save(to);
    }
    //R
    public List<Producto> listIt() {
        return service.findAll();
    }
    //U
    public Producto update(Producto to, Long id) {
        try {
            Producto toExistent = service.findById(id).get();
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
    public Producto search(Long id) {
        return service.findById(id).get();
    }
}
