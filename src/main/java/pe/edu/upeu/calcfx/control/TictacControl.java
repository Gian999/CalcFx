package pe.edu.upeu.calcfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.servicio.MichiJ;
import pe.edu.upeu.calcfx.modelo.MichiM;

import java.util.List;

@Component
public class TictacControl extends MichiM{
    private int contadorPartidos = 0;

    @Autowired
    MichiJ serviceI;
    @FXML
    TextField txt1,txt2;


    Button[][] tablero;

    @FXML
    Button btn00, btn01, btn02,btn10, btn11, btn12, btn20,btn21, btn22, btnIniciar, btnAnular;
    @FXML
    TableView tabla;

//    @FXML
//    TableColumn<CalcTO, String> colpartido;
    @FXML
    TableColumn<MichiM, String> colplayer1;
    @FXML
    TableColumn<CalcTO, String> colplayer2;
    @FXML
    TableColumn<CalcTO, String> colwinner;
//    @FXML
//    TableColumn<CalcTO, String> colscore;
//    @FXML
//    TableColumn<CalcTO, String> colstatus;
    private ObservableList<MichiM> MichiMList;
    @FXML
    private TableColumn<MichiM, Integer> colpartido;

    @FXML
    private TableColumn<MichiM, Integer> colscore;

    @FXML
    private TableColumn<MichiM, String> colstatus;

    boolean turno=true;

    int winner;
    int score;

    @FXML
    public void initialize() {
        System.out.println("holas hh");
        anular();
        tablero=new Button[][]{
                {btn00, btn01,btn02},
                {btn10, btn11, btn12},
                {btn20,btn21, btn22}
        };
    }

    @FXML
    void accionButon(ActionEvent e){
        Button b = (Button) e.getSource();
        b.setText(turno ? "X" : "O");
        turno = !turno;

        // Verificar ganador
        String currentPlayer = turno ? "O" : "X"; // Jugador actual es el contrario del turno

        if (checkWinner(currentPlayer)) {
            System.out.println("Gana " + currentPlayer);

            // Procesar el ganador y guardar los resultados
            procesadorDeGanador(currentPlayer);

            // Reiniciar el tablero para un nuevo juego
            anular();
        }
    }
    boolean checkWinner(String player) {
        // Verifica filas
        if (btn00.getText().equals(player) && btn01.getText().equals(player) && btn02.getText().equals(player)) return true;
        if (btn10.getText().equals(player) && btn11.getText().equals(player) && btn12.getText().equals(player)) return true;
        if (btn20.getText().equals(player) && btn21.getText().equals(player) && btn22.getText().equals(player)) return true;

        // Verifica columnas
        if (btn00.getText().equals(player) && btn10.getText().equals(player) && btn20.getText().equals(player)) return true;
        if (btn01.getText().equals(player) && btn11.getText().equals(player) && btn21.getText().equals(player)) return true;
        if (btn02.getText().equals(player) && btn12.getText().equals(player) && btn22.getText().equals(player)) return true;

        // Verifica diagonales
        if (btn00.getText().equals(player) && btn11.getText().equals(player) && btn22.getText().equals(player)) return true;
        if (btn02.getText().equals(player) && btn11.getText().equals(player) && btn20.getText().equals(player)) return true;

        return false;
    }
    @FXML
    public void procesadorDeGanador(String currentPlayer){
        MichiM currentGame = serviceI.obtenerUltimoResultado(); // Ajusta esto a tu servicio o base de datos

        if (checkWinner("X")) {
            currentGame.setWinner(txt1.getText()); // Asigna el nombre del ganador (jugador 1)
            currentGame.setScore(1); // El ganador obtiene 1 punto
            currentGame.setStatus("Terminado"); // Actualiza el estado a 'Terminado'
            System.out.println("Gana X");
        } else if (checkWinner("O")) {
            currentGame.setWinner(txt2.getText()); // Asigna el nombre del ganador (jugador 2)
            currentGame.setScore(1); // El ganador obtiene 1 punto
            currentGame.setStatus("Terminado"); // Actualiza el estado a 'Terminado'
            System.out.println("Gana O");
        } else {
            // Si nadie gana, se marca el juego como anulado
            currentGame.setScore(0);
            currentGame.setStatus("Anulado");
        }

        // Guarda el resultado actualizado en la base de datos
        serviceI.actualizarResultados(currentGame);

        // Refresca la tabla
        listaOper();
    }
    @FXML
    void imprimir(){
        for (int i=0;i<tablero.length; i++ ){
            for (int j=0;j<tablero[0].length; j++ ){
                System.out.print(tablero[i][j].getText()+"\t");
            }
            System.out.println("");
        }
    }


    @FXML
    public void iniciar(){
        if (txt1.getText().equals("") && txt2.getText().equals("")) {
            System.out.println("Coloca algo antes de empezar");
        } else if (!txt1.getText().equals("") && !txt2.getText().equals("")) {
            activaDesacticaB(false);
            String jugador1 = txt1.getText();
            String jugador2 = txt2.getText();

            MichiM nuevoJuego = new MichiM();
            nuevoJuego.setPlayer1(jugador1);
            nuevoJuego.setPlayer2(jugador2);
            nuevoJuego.setStatus("En curso"); // Marca el juego como "En curso"
            nuevoJuego.setScore(0); // Inicia con score 0
            nuevoJuego.setPartido(serviceI.obtenerNumeroDePartida() + 1); // Asigna un número de partida consecutivo

            // Guarda el nuevo juego en la base de datos
            serviceI.guardarResultados(nuevoJuego);

            // Refresca la tabla
            listaOper();
        }

    }

    @FXML
    public void anular(){
        activaDesacticaB(true);
    }

    public void activaDesacticaB(boolean indi){
        btn00.setDisable(indi);
        btn01.setDisable(indi);
        btn02.setDisable(indi);

        btn10.setDisable(indi);
        btn11.setDisable(indi);
        btn12.setDisable(indi);

        btn20.setDisable(indi);
        btn21.setDisable(indi);
        btn22.setDisable(indi);
    }

   public void  listaOper(){
//        List<MichiM> lista= serviceI.obtenerResultados();
//        for (MichiM to:lista) {
//            System.out.println(to.toString());
//        }
       // Refresca la lista desde el servicio
       List<MichiM> resultados = serviceI.obtenerResultados(); // O el método correcto de tu servicio

       // Limpia la tabla antes de agregar nuevos datos
       tabla.getItems().clear();

       // Agrega los resultados actualizados a la tabla
       tabla.getItems().addAll(resultados);
       // Limpia la tabla antes de agregar nuevos datos para evitar duplicados

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Vincular columnas con propiedades de CalcTO
       colplayer1.setCellValueFactory(new PropertyValueFactory<MichiM,
               String>("player1"));

       //colplayer1.setCellFactory(TextFieldTableCell.<MichiM>forTableColumn());

       colplayer2.setCellValueFactory(new PropertyValueFactory<CalcTO,
               String>("player2"));

       colwinner.setCellValueFactory(new PropertyValueFactory<CalcTO,
               String>("winner"));
       colpartido.setCellValueFactory(new PropertyValueFactory<MichiM, Integer>("partido"));
       colscore.setCellValueFactory(new PropertyValueFactory<MichiM, Integer>("score"));
       colstatus.setCellValueFactory(new PropertyValueFactory<MichiM, String>("status"));
        /*cVal2.setCellValueFactory(new PropertyValueFactory<CalcTO,
                String>("num2"));


        cVal2.setCellFactory(TextFieldTableCell.<CalcTO>forTableColumn());
..
        cOper.setCellValueFactory(new
                PropertyValueFactory<>("Operador"));
        cOper.setCellFactory(ComboBoxTableCell.<CalcTO,
                Character>forTableColumn('+', '-', '/', '*'));

        cResult.setCellValueFactory(new PropertyValueFactory<CalcTO,
                String>("Resultado"));

        cResult.setCellFactory(TextFieldTableCell.<CalcTO>forTableColumn());

        // Agregar botones de eliminar y modificar
        addActionButtonsToTable();
        MichiMList = FXCollections.observableArrayList(serviceI.obtenerResultados());
        // Asignar los datos al TableView
        AnchorPane.setLeftAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);

        cOper.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cResult.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cOpc.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

         */
       MichiMList = FXCollections.observableArrayList(serviceI.obtenerResultados());
        tabla.setItems(MichiMList);
    }


}
