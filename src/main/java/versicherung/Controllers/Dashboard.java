package versicherung.Controllers;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;
import versicherung.DatabaseKunden;
import versicherung.Main;
import versicherung.Models.Kunde;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private VBox sceneKunden_alleKunden;

    @FXML
    private VBox sceneKunden_neueKunden;

    @FXML
    private StackPane sceneKunden_stackpane;

    @FXML
    private Button sceneKunden_button_auflisten;

    @FXML
    private Button sceneKunden_button_neueKunden;

    /* Scene - Kunden (alle Kunden) */
    @FXML
    private TableView<Kunde> sceneKunden_alleKunden_table;

    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_id;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_ausweisNummer;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_vorName;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_nachName;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_geburstDatum;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_telefonNummer;
    @FXML
    private TableColumn<Kunde, String> sceneKunden_alleKunden_table_column_adresse;

    @FXML
    private Button sceneKunden_alleKunden_button_delete;

    /* Scene - Kunden (neue Kunden) */
    @FXML
    private TextField sceneKunden_neueKunden_field_vorname;

    @FXML
    private TextField sceneKunden_neueKunden_field_nachname;

    @FXML
    private TextField sceneKunden_neueKunden_field_ausweisnummer;

    @FXML
    private TextField sceneKunden_neueKunden_field_geburstdatum;

    @FXML
    private TextField sceneKunden_neueKunden_field_telefonnummer;

    @FXML
    private TextField sceneKunden_neueKunden_field_adresse;

    /* Versicherung */
    @FXML
    private HBox sceneVersicherung_buttons;

    @FXML
    private VBox sceneVersicherung_alleVertraege;

    @FXML
    private VBox sceneVersicherung_neueVertraege_erstellen;

    @FXML
    private Button sceneVersicherung_button_vertraege_auflisten;

    @FXML
    private Button sceneVersicherung_button_vertraege_erstellen;

    @FXML
    private Button sceneVersicherung_button_typen_bearbeiten;

    /* Daten */
    private ObservableList<Kunde> datenKunden = FXCollections.observableArrayList();

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
        if(event.getSource() == sceneKunden_button_auflisten){
            sceneKunden_alleKunden.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/alleKunden");
            sceneName.setText("Alle Kunden");
            listAllKunden();
        }
        else if (event.getSource() == sceneKunden_button_neueKunden){
            sceneKunden_neueKunden.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/neuerKunde");
            sceneName.setText("Neuer Kunde");
        }
    }

    @FXML
    private void handleBackToKundenClick(ActionEvent event){
        leftMenu_btnKunden.fire();
    }

    @FXML
    private void handleErstellenNeueKundenClick(ActionEvent event){
        String vorName = sceneKunden_neueKunden_field_vorname.getText();
        String nachName = sceneKunden_neueKunden_field_nachname.getText();
        String ausweisNummer = sceneKunden_neueKunden_field_ausweisnummer.getText();
        String geburstDatum = sceneKunden_neueKunden_field_geburstdatum.getText();
        String telefonNummer = sceneKunden_neueKunden_field_telefonnummer.getText();
        String adresse = sceneKunden_neueKunden_field_adresse.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date geburstDatum_dateFormat = null;
        try {
            geburstDatum_dateFormat = dateFormat.parse(geburstDatum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Kunde kunde = new Kunde(null, ausweisNummer, vorName, nachName, geburstDatum_dateFormat, telefonNummer, adresse);
        if (DatabaseKunden.erstelleNeuKunden(kunde)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Neuer Kunde wurde erfolgreich erstellt.");
            alert.show();
            leftMenu_btnKunden.fire();
        }
    }

    @FXML
    private void handleBackToVersicherungClick(ActionEvent event){
        leftMenu_btnVersicherung.fire();
    }

    @FXML
    private void handleSceneVersicherungButtonClicks(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearSceneHeader();
        hideAllSceneItems();

        initializeAlleKundenTableView();
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

    public void hideAllSceneKundenItems()
    {
        sceneKunden_buttons.setVisible(false);
        sceneKunden_alleKunden.setVisible(false);
        sceneKunden_neueKunden.setVisible(false);
    }

    public void initializeAlleKundenTableView()
    {
        sceneKunden_alleKunden_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneKunden_alleKunden_table_column_ausweisNummer.setCellValueFactory(new PropertyValueFactory<>("AusweisNummer"));
        sceneKunden_alleKunden_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("VorName"));
        sceneKunden_alleKunden_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("NachName"));
        sceneKunden_alleKunden_table_column_telefonNummer.setCellValueFactory(new PropertyValueFactory<>("TelefonNummer"));
        sceneKunden_alleKunden_table_column_adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));

        sceneKunden_alleKunden_button_delete.disableProperty().bind(Bindings.isEmpty(sceneKunden_alleKunden_table.getSelectionModel().getSelectedItems()));

        //TODO remove this hardcoded Kunde class later.
        Date d = new Date();
        d.setTime(23132);
        Kunde k = new Kunde("1", "1231231", "fasda", "dsada", d, "05/05/2332", "dsakjdasjd");
        datenKunden.add(k);
        sceneKunden_alleKunden_table.setItems(datenKunden);
    }

    public void listAllKunden()
    {

    }
}