package versicherung.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

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
}