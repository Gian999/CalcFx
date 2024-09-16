package pe.edu.upeu.calcfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
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
    TableColumn<CalcTO, String> colpartido;
    @FXML
    TableColumn<MichiM, String> colplayer1;
    @FXML
    TableColumn<CalcTO, String> colplayer2;
    @FXML
    TableColumn<CalcTO, String> colwinner;
    @FXML
    TableColumn<CalcTO, String> colscore;
    @FXML
    TableColumn<CalcTO, String> colstatus;
    private ObservableList<MichiM> MichiMList;

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
        Button b=(Button)e.getSource();
        b.setText(turno?"X":"O");
        turno=!turno;
        // Verifica combinaciones para "X"
        if (checkWinner("X")) {
            System.out.println("Gana X");
            anular();
            procesadorDeGanador();
        }
        // Verifica combinaciones para "O"
        else if (checkWinner("O")) {
            System.out.println("Gana O");
            anular();
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

        return false;
    }
    @FXML
    public void procesadorDeGanador(){
        if (checkWinner("X")){
            String winner= txt1.getText();
            MichiM gg = new MichiM();
            gg.setWinner(winner);
            serviceI.guardarResultados(gg);
            listaOper();
        }else if(checkWinner("O")){
            String winner= txt2.getText();
            MichiM gg = new MichiM();
            gg.setWinner(winner);
            serviceI.guardarResultados(gg);
            listaOper();
        }
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
        if (txt1.getText().equals("")&&txt2.getText().equals("")){
            System.out.println("Colca algo antes de empezar");
        }else if(!txt1.getText().equals("")&&!txt2.getText().equals("")){
            activaDesacticaB(false);
            String jugador1= txt1.getText();
            String jugador2= txt2.getText();
            MichiM gg = new MichiM();
            gg.setPlayer1(jugador1);
            gg.setPlayer2(jugador2);
            serviceI.guardarResultados(gg);
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
        List<MichiM> lista= serviceI.obtenerResultados();
        for (MichiM to:lista) {
            System.out.println(to.toString());
        }

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Vincular columnas con propiedades de CalcTO
       colplayer1.setCellValueFactory(new PropertyValueFactory<MichiM,
               String>("player1"));

       //colplayer1.setCellFactory(TextFieldTableCell.<MichiM>forTableColumn());

       colplayer2.setCellValueFactory(new PropertyValueFactory<CalcTO,
               String>("player2"));

       colwinner.setCellValueFactory(new PropertyValueFactory<CalcTO,
               String>("winner"));
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
