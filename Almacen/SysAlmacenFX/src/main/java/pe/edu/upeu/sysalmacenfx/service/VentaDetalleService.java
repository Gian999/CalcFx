package pe.edu.upeu.sysalmacenfx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.model.VentaDetalle;
import pe.edu.upeu.sysalmacenfx.repository.VentaDetalleRepository;

import java.util.List;

@Service
public class VentaDetalleService {
    @Autowired
    VentaDetalleRepository repo;

    public VentaDetalle save(VentaDetalle to) {
        return repo.save(to);
    }

    public List<VentaDetalle> list() {
        return repo.findAll();
    }

    public VentaDetalle update(VentaDetalle to, Long id) {
        try {
            VentaDetalle toe = repo.findById(Math.toIntExact(id)).orElse(null);
            if (toe != null) {
                toe.setCantidad(to.getCantidad());
                return repo.save(toe);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(Math.toIntExact(id));
    }

    public VentaDetalle searchById(Long id) {
        return repo.findById(Math.toIntExact(id)).orElse(null);
    }
}
