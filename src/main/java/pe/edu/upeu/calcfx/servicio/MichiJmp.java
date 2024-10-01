package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.modelo.MichiM;

import java.util.ArrayList;
import java.util.List;
@Service
public class MichiJmp implements MichiJ{
    List<MichiM> ja= new ArrayList<>();
    @Override
    public void guardarResultados(MichiM to) {
        ja.add(to);
    }

    @Override
    public List<MichiM> obtenerResultados() {
        return ja;
    }

    @Override
    public void actualizarResultados(MichiM to, int index) {
        ja.set(index,to);
    }

    @Override
    public void eliminarResultados(int index) {
        ja.remove(index);
    }

    @Override
    public MichiM obtenerUltimoResultado() {
        if (ja.isEmpty()) {
            return null;  // Si no hay resultados aún, devuelve null
        }
        return ja.get(ja.size() - 1);  // Devuelve el último resultado de la lista
    }
}