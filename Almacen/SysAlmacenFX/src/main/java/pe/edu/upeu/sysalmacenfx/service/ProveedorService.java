package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.model.Proveedor;
import pe.edu.upeu.sysalmacenfx.repository.ProveedorRepository;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository repo;

    public Proveedor save(Proveedor to) {
        return repo.save(to);
    }

    public List<Proveedor> list() {
        return repo.findAll();
    }

    public Proveedor update(Proveedor to, Long id) {
        try {
            Proveedor toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setDniRuc(to.getDniRuc());
                return repo.save(toe);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Proveedor searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
