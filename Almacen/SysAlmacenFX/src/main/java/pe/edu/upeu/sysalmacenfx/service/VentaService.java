package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.model.CompCarrito;
import pe.edu.upeu.sysalmacenfx.model.Venta;
import pe.edu.upeu.sysalmacenfx.repository.CompCarritoRepository;
import pe.edu.upeu.sysalmacenfx.repository.VentaRepository;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    VentaRepository repo;

    public Venta save(Venta to) {
        return repo.save(to);
    }

    public List<Venta> list() {
        return repo.findAll();
    }

    public Venta update(Venta to, Long id) {
        try {
            Venta toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setSerie(to.getSerie());
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

    public Venta searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
