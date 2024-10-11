package pe.edu.upeu.sysalmacenfx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
