package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.MichiM;

import java.util.List;

public interface MichiJ {
    public void guardarResultados(MichiM to);//C
    public List<MichiM> obtenerResultados();//R
    public void actualizarResultados(MichiM to, int index);//U
    public void eliminarResultados(int index);//D

}