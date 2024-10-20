package pe.edu.upeu.sysalmacenfx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysalmacenfx.model.Emisor;

public interface EmisorRepository extends JpaRepository<Emisor, Long> {
}
