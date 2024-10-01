package pe.edu.upeu.calcfx.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.calcfx.modelo.MichiM;

@Repository
public interface MichiRepo extends JpaRepository<MichiM, Integer> {
}
