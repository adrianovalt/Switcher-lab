package SwControler;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class MainControl implements Initializable {

    private String buttonPress = "";
    private String itemSetPress = "";

    @FXML
    private Button computerButton;
    @FXML
    private Button printerButton;
    @FXML
    private Button phoneButton;
    @FXML
    private Button switchButton;
    @FXML
    private Button hubButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button playButton;
    @FXML
    private BorderPane borderPane;
    //@FXML 
    private Pane mainPane;

    @FXML
    protected void btAbrirMenuAction(ActionEvent e) {
        System.out.println("Teste");
        Main.alternaTela("prop");
    }

    @FXML
    protected void editButtonAction(ActionEvent event) {
        setButtonState("edit");
        System.out.println("Butoon press? " + buttonPress);
        computerButton.setDisable(false);
        printerButton.setDisable(false);
        phoneButton.setDisable(false);
        switchButton.setDisable(false);
        hubButton.setDisable(false);
        lineButton.setDisable(false);
    }

    @FXML
    protected void deleteButtonAction(ActionEvent event) {
        buttonPress = "delete";
        computerButton.setDisable(true);
        printerButton.setDisable(true);
        phoneButton.setDisable(true);
        switchButton.setDisable(true);
        hubButton.setDisable(true);
        lineButton.setDisable(true);
    }

    @FXML
    protected void saveButtonAction(ActionEvent event) {
        buttonPress = "save";
        //rightVBox.set
    }

    @FXML
    protected void reportButtonAction(ActionEvent event) {
        buttonPress = "report";
        //rightVBox.set
    }

    @FXML
    protected void playButtonAction(ActionEvent event) {
        buttonPress = "play";
        computerButton.setDisable(true);
        printerButton.setDisable(true);
        phoneButton.setDisable(true);
        switchButton.setDisable(true);
        hubButton.setDisable(true);
        lineButton.setDisable(true);
    }

    @FXML
    protected void computerButtonAction(ActionEvent event) {
        itemSetPress = "appIcons/computer-72x72.PNG";
        //rightVBox.set
    }

    @FXML
    protected void printerButtonAction(ActionEvent event) {
        itemSetPress = "appIcons/printer-72x72.PNG";
        //rightVBox.set
    }

    @FXML
    protected void phoneButtonAction(ActionEvent event) {
        itemSetPress = "appIcons/phone-72x72.PNG";
        //rightVBox.set
    }

    @FXML
    protected void switchButtonAction(ActionEvent event) {
        itemSetPress = "appIcons/switch-72x72.PNG";
        //rightVBox.set
    }

    @FXML
    protected void hubButtonAction(ActionEvent event) {
        itemSetPress = "appIcons/hub-72x72.PNG";
        //rightVBox.set
    }

    @FXML
    protected void lineButtonAction(ActionEvent event) {
        buttonPress = "line";
        //rightVBox.set
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane = new Pane();
        final String cssDefault = "-fx-border-color: gray;-fx-border-width: 2;";
        mainPane.setStyle(cssDefault);
        borderPane.setCenter(mainPane);

        System.out.println("Butoon press? " + buttonPress);

        System.out.println("Butoon press? " + buttonPress);
        mainPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent me) {
                if (buttonPress.equals("edit")) {
                    Nodo unidade = new Nodo();
                    try {
                        final ImageView obj = unidade.createDragImage(me.getX(), me.getY(), itemSetPress);
                        mainPane.getChildren().add(obj);
                        unidade.manipulaNodo(obj, buttonPress);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Selecione na barra de equipamentos um item antes de clicar");
                    }
                }
            }
        });

        Image computerIconImage = new Image("appIcons/computer-64x64.png");
        computerButton.setGraphic(new ImageView(computerIconImage));
        computerButton.setTooltip(new Tooltip("Computador:" + "\n" + "Inserir Computador na area de edição"));
        Image printerIconImage = new Image("appIcons/printer-64x64.png");
        printerButton.setGraphic(new ImageView(printerIconImage));
        printerButton.setTooltip(new Tooltip("Impressora:" + "\n" + "Inserir Impressora na area de edição"));
        Image phoneIconImage = new Image("appIcons/phone-64x64.png");
        phoneButton.setGraphic(new ImageView(phoneIconImage));
        phoneButton.setTooltip(new Tooltip("IP-Phone:" + "\n" + "Inserir IP-Phone na area de edição"));
        Image switchIconImage = new Image("appIcons/switch-64x64.png");
        switchButton.setGraphic(new ImageView(switchIconImage));
        switchButton.setTooltip(new Tooltip("Switch:" + "\n" + "Inserir Switch na area de edição"));
        Image hubIconImage = new Image("appIcons/hub-64x64.png");
        hubButton.setGraphic(new ImageView(hubIconImage));
        hubButton.setTooltip(new Tooltip("Hub:" + "\n" + "Inserir Hub na area de edição"));
        Image lineIconImage = new Image("appIcons/line-64x64.png");
        lineButton.setGraphic(new ImageView(lineIconImage));
        lineButton.setTooltip(new Tooltip("Conectar:" + "\n" + "Conecte um nodo ao outro"));
        Image editButtonImage = new Image("appIcons/edit-64x64.png");
        editButton.setGraphic(new ImageView(editButtonImage));
        editButton.setTooltip(new Tooltip("Editor:" + "\n" + "Selecione para habilitar o modo de edição e adicionar nodos ao projeto"));
        Image deleteButtonImage = new Image("appIcons/delete-64x64.png");
        deleteButton.setGraphic(new ImageView(deleteButtonImage));
        deleteButton.setTooltip(new Tooltip("Excluir:" + "\n" + "Selecione para excluir nodos ou conexões"));
        Image saveButtonImage = new Image("appIcons/save-64x64.png");
        saveButton.setGraphic(new ImageView(saveButtonImage));
        saveButton.setTooltip(new Tooltip("Salvar:" + "\n" + "Salvar projeto"));
        Image reportButtonImage = new Image("appIcons/check-64x64.png");
        reportButton.setGraphic(new ImageView(reportButtonImage));
        reportButton.setTooltip(new Tooltip("Relatório:" + "\n" + "Gerar relatório"));
        Image playButtonImage = new Image("appIcons/start-64x64.png");
        playButton.setGraphic(new ImageView(playButtonImage));
        playButton.setTooltip(new Tooltip("Executar:" + "\n" + "Selecione para habilitar o modo de execução"));
    }

    public void setButtonState(String state) {
        this.buttonPress = state;
    }
}
