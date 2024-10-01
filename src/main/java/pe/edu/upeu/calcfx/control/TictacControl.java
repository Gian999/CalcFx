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
    @Autowired
    MichiJ serviceI;
    @FXML
    TextField txt1,txt2;


    Button[][] tablero;

    @FXML
    Button btn00, btn01, btn02,btn10, btn11, btn12, btn20,btn21, btn22, btnIniciar, btnAnular;
    @FXML
    TableView tabla;

    @FXML
    TableColumn<MichiM, String> colpartido;
    @FXML
    TableColumn<MichiM, String> colplayer1;
    @FXML
    TableColumn<MichiM, String> colplayer2;
    @FXML
    TableColumn<MichiM, String> colwinner;
    @FXML
    TableColumn<MichiM, String> colscore;
    @FXML
    TableColumn<MichiM, String> colstatus;
    private ObservableList<MichiM> MichiMList;
    private String currentPlayer = "X";
    boolean turno=true;
    boolean ganadorGuardado = false; // Añade esto a la clase
    int numPartida = 0;
    int Nscore;
    int estadoN;
    private boolean partidaEnCurso = false;


    @FXML
    public void initialize() {
        System.out.println("holas hh");
        checkTo();
        tablero=new Button[][]{
                {btn00, btn01,btn02},
                {btn10, btn11, btn12},
                {btn20,btn21, btn22}
        };
    }


    @FXML
    void accionButon(ActionEvent e){
        Button b=(Button)e.getSource();
        b.setText(turno?"X":"O");
        turno=!turno;
        // Verifica combinaciones para "X"
        if (checkWinner("X")) {
            System.out.println("Gana X");
            checkTo();
            procesadorDeGanador();
        }
        // Verifica combinaciones para "O"
        else if (checkWinner("O")) {
            System.out.println("Gana O");
            checkTo();
            procesadorDeGanador();
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

        if (!btn00.getText().isEmpty() && !btn01.getText().isEmpty() && !btn02.getText().isEmpty() &&
                !btn10.getText().isEmpty() && !btn11.getText().isEmpty() && !btn12.getText().isEmpty() &&
                !btn20.getText().isEmpty() && !btn21.getText().isEmpty() && !btn22.getText().isEmpty()){
            return true;
        }
        return false;
    }
    @FXML
    public void procesadorDeGanador(){
        MichiM gg = new MichiM();
        String jugador1 = txt1.getText();
        String jugador2 = txt2.getText();
        gg.setPlayer1(jugador1);
        gg.setPlayer2(jugador2);

        numPartida++;  // Incrementar número de partida
        gg.setNpartido(numPartida);  // Asignar número de partida al objeto


        if (isBoardFull()){
            gg.setWinner("None");  // Empate
            Nscore = 0;  // Puntaje para empate
            gg.setScore(Nscore);
            estadoN = 1;

            gg.setEstado("Empate");



        }else {
            if (checkWinner("X")) {
                String winner = txt1.getText();  // Ganador es jugador 1
                gg.setWinner(winner);
                Nscore = 1;
                gg.setScore(Nscore);

                gg.setEstado("Terminado");

            } else if (checkWinner("O")) {
                String winner = txt2.getText();  // Ganador es jugador 2
                gg.setWinner(winner);
                Nscore = 1;

                gg.setEstado("Terminado");

                gg.setScore(Nscore);
            }
        }

        ganadorGuardado = true;  // Marca como guardado

        // Guarda el resultado y actualiza la tabla
        serviceI.guardarResultados(gg);
        listaOper();


    }
    boolean isBoardFull() {
        return !btn00.getText().isEmpty() && !btn01.getText().isEmpty() && !btn02.getText().isEmpty() &&
                !btn10.getText().isEmpty() && !btn11.getText().isEmpty() && !btn12.getText().isEmpty() &&
                !btn20.getText().isEmpty() && !btn21.getText().isEmpty() && !btn22.getText().isEmpty();
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
            // Incrementa el contador de partidas
            // Limpiar el tablero
            cleanGame();
            partidaEnCurso = true;
            turno=true;
        }
    }
    public void cleanGame(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j].setText("");  // Limpia los botones
            }
        }
    }


    @FXML
    public void anular(){

//        @FXML
//        TableColumn<MichiM, String> colpartido;
//        @FXML
//        TableColumn<MichiM, String> colplayer1;
//        @FXML
//        TableColumn<MichiM, String> colplayer2;
//        @FXML
//        TableColumn<MichiM, String> colwinner;
//        @FXML
//        TableColumn<MichiM, String> colscore;
//        @FXML
//        TableColumn<MichiM, String> colstatus;

        MichiM gg = new MichiM();
        if (partidaEnCurso) { // Aquí validamos si hay una partida en curso
            // Guardar los datos en la tabla como 'Anulado'
            numPartida++;
            gg.setNpartido(numPartida);
            String jugador1 = txt1.getText();
            String jugador2 = txt2.getText();
            gg.setPlayer1(jugador1);
            gg.setPlayer2(jugador2);
            gg.setEstado("Anulado");
            gg.setWinner("None");
            gg.setScore(0);
            serviceI.guardarResultados(gg);
            checkTo();
            listaOper();



            // También podemos detener la partida en curso
            partidaEnCurso = false; // Marcamos que la partida ha sido anulada
        }
    }

    public void checkTo(){
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
        List<MichiM> lista = serviceI.obtenerResultados();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colpartido.setCellValueFactory(new PropertyValueFactory<MichiM, String>("Npartido"));
        colplayer1.setCellValueFactory(new PropertyValueFactory<MichiM, String>("player1"));
        colplayer2.setCellValueFactory(new PropertyValueFactory<MichiM, String>("player2"));
        if (checkWinner("X")){
            colwinner.setCellValueFactory(new PropertyValueFactory<MichiM, String>("winner"));
        } else if (checkWinner("O")){
            colwinner.setCellValueFactory(new PropertyValueFactory<MichiM, String>("winner"));
        }
        if (checkWinner("X")){
            colscore.setCellValueFactory(new PropertyValueFactory<MichiM, String>("score"));
        } else if (checkWinner("O")){
            colscore.setCellValueFactory(new PropertyValueFactory<MichiM, String>("score"));
        }
        if(isBoardFull()){
            colstatus.setCellValueFactory(new  PropertyValueFactory<MichiM, String>("estado"));
        }else if(checkWinner("X")) {
            colstatus.setCellValueFactory(new  PropertyValueFactory<MichiM, String>("estado"));
        } else if (checkWinner("O")) {
            colstatus.setCellValueFactory(new  PropertyValueFactory<MichiM, String>("estado"));
        }



        MichiMList = FXCollections.observableArrayList(lista);
        tabla.setItems(MichiMList);

//        List<MichiM> lista= serviceI.obtenerResultados();
//        for (MichiM to:lista) {
//            System.out.println(to.toString());
//        }
//
//        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//        // Vincular columnas con propiedades de CalcTO
//        colplayer1.setCellValueFactory(new PropertyValueFactory<MichiM,
//                String>("player1"));
//
//        colplayer2.setCellValueFactory(new PropertyValueFactory<CalcTO,
//                String>("player2"));
//
//        colwinner.setCellValueFactory(new PropertyValueFactory<CalcTO,
//                String>("winner"));









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
    }


}