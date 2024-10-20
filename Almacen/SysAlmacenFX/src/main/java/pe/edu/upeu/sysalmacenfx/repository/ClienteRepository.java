package pe.edu.upeu.sysalmacenfx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.model.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    @Query(value = "SELECT * FROM upeu_cliente WHERE nombrers like :nombre", nativeQuery = true)
    List<Cliente> findByNombre(@Param(value = "nombre") String nombre);
    @Query(value = "SELECT c.* FROM upeu_cliente c WHERE c.nombrers like :filter", nativeQuery = true)
    List<Cliente> listAutoCompletCliente(@Param("filter") String filter);
}