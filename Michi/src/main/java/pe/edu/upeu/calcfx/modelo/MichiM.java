package pe.edu.upeu.calcfx.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "michi")
public class MichiM{
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            int id;
    String player1;
    String player2;
    String winner;
    int Npartido;
    int score;
    String estado;

}