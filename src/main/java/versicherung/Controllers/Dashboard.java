package versicherung.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import versicherung.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Button leftMenu_btnKunden;

    @FXML
    private Button leftMenu_btnMitarbeitern;

    @FXML
    private Button leftMenu_btnVersicherung;

    @FXML
    private Button leftMenu_btnAbmelden;

    @FXML
    private Button leftMenu_btnUberuns;

    /* Scene StackPane */
    @FXML
    private StackPane sceneStackPane;

    /* Scene Header */
    @FXML
    private Label sceneUrl;

    @FXML
    private Label sceneName;

    /* Scene Contents */
    @FXML
    private VBox sceneKunden;

    @FXML
    private VBox sceneMitarbeitern;

    @FXML
    private VBox sceneVersicherung;

    @FXML
    private VBox sceneUberuns;

    /* Scene - Kunden */
    @FXML
    private HBox sceneKunden_buttons;

    @FXML
    private VBox sceneKunden_neueKunden;

    @FXML
    private StackPane sceneKunden_stackpane;

    @FXML
    private Button sceneKunden_button_auflisten;

    @FXML
    private Button sceneKunden_button_neueKunden;

    /* Override Methods */

    @FXML
    private void handleLeftMenuButtonClicks(ActionEvent event)
    {
        clearSceneHeader();
        hideAllSceneItems();
        if (event.getSource() == leftMenu_btnKunden)
        {
            sceneUrl.setText("/versicherung/kunden");
            sceneName.setText("Kunden");
            sceneKunden.setVisible(true);

            hideAllSceneKundenItems();
            sceneKunden_buttons.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnMitarbeitern)
        {
            sceneUrl.setText("/versicherung/mitarbeitern");
            sceneName.setText("Mitarbeitern");
            sceneMitarbeitern.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnVersicherung)
        {
            sceneUrl.setText("/versicherung/versicherung");
            sceneName.setText("Versicherung");
            sceneVersicherung.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnUberuns)
        {
            sceneUrl.setText("/versicherung/uberuns");
            sceneName.setText("Über uns");
            sceneUberuns.setVisible(true);
        }
        else if(event.getSource() == leftMenu_btnAbmelden)
        {
            try{
                Main main = new Main();
                main.loadLogin();
                main.getAccount().abMelden();
            }catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void handleSceneKundenButtonClicks(ActionEvent event)
    {
        hideAllSceneKundenItems();
        if (event.getSource() == sceneKunden_button_neueKunden){
            sceneKunden_neueKunden.setVisible(true);
        }else if(event.getSource() == sceneKunden_button_auflisten){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearSceneHeader();
        hideAllSceneItems();
    }

    /* Methods */
    public void clearSceneHeader()
    {
        sceneUrl.setText("");
        sceneName.setText("");
    }

    public void hideAllSceneItems()
    {
        sceneStackPane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
    }

    public void hideAllSceneKundenItems(){
        sceneKunden_buttons.setVisible(false);
        sceneKunden_neueKunden.setVisible(false);
    }
}