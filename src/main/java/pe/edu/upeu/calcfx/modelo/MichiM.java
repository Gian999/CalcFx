package pe.edu.upeu.calcfx.modelo;

import java.util.Arrays;

public class MichiM{
    String player1;
    String player2;
    String winner;
    int Npartido;
    int score;
    String estado;

    public int getNpartido() {
        return Npartido;
    }

    public void setNpartido(int npartido) {
        Npartido = npartido;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MichiM{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", winner='" + winner + '\'' +
                ", Npartido=" + Npartido +
                ", score=" + score +
                ", estado='" + estado + '\'' +
                '}';
    }
}