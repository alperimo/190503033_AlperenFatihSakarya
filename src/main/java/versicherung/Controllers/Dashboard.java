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
import versicherung.DatabaseVersicherung;
import versicherung.Main;
import versicherung.Models.Kunde;
import versicherung.Models.VersicherungsTyp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
    private StackPane sceneVersicherung_stackpane;

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
    private VBox sceneVersicherung_VersicherungsTypen;

    @FXML
    private Button sceneVersicherung_button_vertraege_auflisten;

    @FXML
    private Button sceneVersicherung_button_vertraege_erstellen;

    @FXML
    private Button sceneVersicherung_button_typen_bearbeiten;

    @FXML
    private TableView<VersicherungsTyp> sceneVersicherung_VersicherungsTypen_table;

    @FXML
    private TableColumn<VersicherungsTyp, String> sceneVersicherung_VersicherungsTypen_table_column_id;

    @FXML
    private TableColumn<VersicherungsTyp, String> sceneVersicherung_VersicherungsTypen_table_column_typname;

    @FXML
    private Button sceneVersicherung_VersicherungsTypen_button_delete;
    
    /* Daten */
    private ObservableList<Kunde> datenKunden = FXCollections.observableArrayList();
    private ObservableList<VersicherungsTyp> datenVersicherungsTypen = FXCollections.observableArrayList();


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

            hideAllSceneVersicherungsItems();
            sceneVersicherung_buttons.setVisible(true);
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
            refreshAlleKundenList();
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
        hideAllSceneVersicherungsItems();
        if (event.getSource() == sceneVersicherung_button_typen_bearbeiten){
            sceneVersicherung_VersicherungsTypen.setVisible(true);
            refreshVersicherungsTypenList();
        }
    }

    @FXML
    private void handleLoeschenKundenClick(ActionEvent event){
        Kunde selectedKunde = sceneKunden_alleKunden_table.getSelectionModel().getSelectedItem();

        if (selectedKunde == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Kunde Löschen");
        alert.setHeaderText(selectedKunde.getVorName() + " " + selectedKunde.getNachName());
        alert.setContentText("Möchten Sie diesen Kunde wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (DatabaseKunden.loescheKunde(selectedKunde)){
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Kunde Löschen");
                confirmationAlert.setHeaderText(selectedKunde.getVorName() + " " + selectedKunde.getNachName());
                confirmationAlert.setContentText("Kunde wurde erfolgreich gelöscht.");
                confirmationAlert.show();
                refreshAlleKundenList();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Kunde Löschen");
                errorAlert.setHeaderText(selectedKunde.getVorName() + " " + selectedKunde.getNachName());
                errorAlert.setContentText("Kunde konnte nicht gelöscht werden.");
                errorAlert.show();
            }
        }else{
            System.out.println("Canceled");
        }
    }

    @FXML
    private void handleLoeschenVersicherungsTypClick(ActionEvent event)
    {
        VersicherungsTyp selectedVersicherungsTyp = sceneVersicherung_VersicherungsTypen_table.getSelectionModel().getSelectedItem();

        if (selectedVersicherungsTyp == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("VersicherungsTyp Löschen");
        alert.setHeaderText(selectedVersicherungsTyp.getName());
        alert.setContentText("Möchten Sie diesen VersicherungsTyp wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // create a instance of versicherungsTyp
            VersicherungsTyp versicherungsTyp = new VersicherungsTyp(selectedVersicherungsTyp.getId(), selectedVersicherungsTyp.getName());

            // control whether the versicherungsTyp is deleted
            if (DatabaseVersicherung.loescheVersicherungsTyp(versicherungsTyp)){
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("VersicherungsTyp Löschen");
                confirmationAlert.setHeaderText(selectedVersicherungsTyp.getName());
                confirmationAlert.setContentText("VersicherungsTyp wurde erfolgreich gelöscht.");
                confirmationAlert.show();
                refreshVersicherungsTypenList();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("VersicherungsTyp Löschen");
                errorAlert.setHeaderText(selectedVersicherungsTyp.getName());
                errorAlert.setContentText("VersicherungsTyp konnte nicht gelöscht werden.");
                errorAlert.show();
            }
        }else{
            System.out.println("Canceled");
        }
    }

    @FXML
    private void handleErstellenVersicherungsTypClick(ActionEvent event){
        // open a input dialog
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Neuer VersicherungsTyp");

        dialog.setHeaderText("Neuer VersicherungsTyp");

        dialog.setContentText("Bitte geben Sie den Namen des neuen VersicherungsTyps ein:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            String versicherungsTypName = result.get();

            // create a instance of versicherungsTyp and save it to the database
            VersicherungsTyp versicherungsTyp = new VersicherungsTyp(null, versicherungsTypName);
            if (DatabaseVersicherung.erstelleNeuVersicherungsTyp(versicherungsTyp)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Neuer VersicherungsTyp ' " + versicherungsTypName + " ' erfolgreich erstellt.");
                alert.show();
                refreshVersicherungsTypenList();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearSceneHeader();
        hideAllSceneItems();

        initializeAlleKundenTableView();
        initializeVersicherungsTypenTableView();
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

    public void hideAllSceneVersicherungsItems()
    {
        sceneVersicherung_stackpane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
    }

    public void initializeAlleKundenTableView()
    {
        sceneKunden_alleKunden_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneKunden_alleKunden_table_column_ausweisNummer.setCellValueFactory(new PropertyValueFactory<>("AusweisNummer"));
        sceneKunden_alleKunden_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("VorName"));
        sceneKunden_alleKunden_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("NachName"));
        sceneKunden_alleKunden_table_column_geburstDatum.setCellValueFactory(new PropertyValueFactory<>("GeburstDatum"));
        sceneKunden_alleKunden_table_column_telefonNummer.setCellValueFactory(new PropertyValueFactory<>("TelefonNummer"));
        sceneKunden_alleKunden_table_column_adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));

        sceneKunden_alleKunden_button_delete.disableProperty().bind(Bindings.isEmpty(sceneKunden_alleKunden_table.getSelectionModel().getSelectedItems()));

        refreshAlleKundenList();
    }

    public void refreshAlleKundenList()
    {
        try{
            ArrayList<Kunde> kundenList = DatabaseKunden.getAlleKunden();
            datenKunden = FXCollections.observableList(kundenList);
            sceneKunden_alleKunden_table.setItems(datenKunden);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void initializeVersicherungsTypenTableView()
    {
        sceneVersicherung_VersicherungsTypen_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneVersicherung_VersicherungsTypen_table_column_typname.setCellValueFactory(new PropertyValueFactory<>("Name"));

        sceneVersicherung_VersicherungsTypen_button_delete.disableProperty().bind(Bindings.isEmpty(sceneVersicherung_VersicherungsTypen_table.getSelectionModel().getSelectedItems()));

        refreshVersicherungsTypenList();
    }

    public void refreshVersicherungsTypenList()
    {
        try{
            ArrayList<VersicherungsTyp> versicherungsTypenList = DatabaseVersicherung.getAllVersicherungsTypen();
            datenVersicherungsTypen = FXCollections.observableList(versicherungsTypenList);
            sceneVersicherung_VersicherungsTypen_table.setItems(datenVersicherungsTypen);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}