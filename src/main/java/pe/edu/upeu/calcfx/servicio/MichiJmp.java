package pe.edu.upeu.calcfx.servicio;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.modelo.MichiM;
import pe.edu.upeu.calcfx.respository.MichiRepo;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class MichiJmp implements MichiJ{
    private final MichiRepo repo;
   //ist<MichiM> ja= new ArrayList<>();
    @Override
    public void guardarResultados(MichiM to) {
        repo.save(to);
    }

    @Override
    public List<MichiM> obtenerResultados() {
        return repo.findAll();
    }

    @Override
    public void actualizarResultados(MichiM to, int index) {
        to.setId(index);
        repo.save(to);
    }

    @Override
    public void eliminarResultados(int index) {
        repo.deleteById(index);
    }

}