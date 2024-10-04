package pe.edu.upeu.sysalmacenfx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "upeu_perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil", nullable = false)
    private Long idPerfil;


    //@Size es de Jakarta Validation https://central.sonatype.com/artifact/jakarta.validation/jakarta.validation-api
    @Size(max = 40)
    @Column(length = 20)
    private String nombre;
    @Size(max = 6)
    @Column(length = 6)
    private String codigo;
}