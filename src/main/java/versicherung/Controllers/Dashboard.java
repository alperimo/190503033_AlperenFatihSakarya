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
import javafx.util.StringConverter;
import org.w3c.dom.events.MouseEvent;
import versicherung.DatabasePerson;
import versicherung.DatabaseKunden;
import versicherung.DatabaseMitarbeiter;
import versicherung.DatabaseVersicherung;
import versicherung.Main;
import versicherung.Models.*;
import versicherung.Models.VersicherungsVertrag.PersonTyp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /* Scene - Mitarbeiter */
    @FXML
    private HBox sceneMitarbeiter_buttons;

    @FXML
    private VBox sceneMitarbeiter_alleMitarbeiter;

    @FXML
    private VBox sceneMitarbeiter_neueMitarbeiter;

    @FXML
    private StackPane sceneMitarbeiter_stackpane;

    @FXML
    private Button sceneMitarbeiter_button_auflisten;

    @FXML
    private Button sceneMitarbeiter_button_neueMitarbeiter;

    /* Scene - Mitarbeiter (alle Mitarbeiter) */
    @FXML
    private TableView<Mitarbeiter> sceneMitarbeiter_alleMitarbeiter_table;

    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_id;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_ausweisNummer;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_vorName;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_nachName;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_role;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_geburstDatum;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_telefonNummer;
    @FXML
    private TableColumn<Mitarbeiter, String> sceneMitarbeiter_alleMitarbeiter_table_column_adresse;

    @FXML
    private Button sceneMitarbeiter_alleMitarbeiter_button_delete;

    /* Scene - Mitarbeiter (neue Mitarbeiter) */
    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_vorname;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_nachname;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_ausweisnummer;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_role;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_geburstdatum;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_telefonnummer;

    @FXML
    private TextField sceneMitarbeiter_neueMitarbeiter_field_adresse;

    /* Scene - Versicherung */
    @FXML
    private StackPane sceneVersicherung_stackpane;

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

    // Scene - Versicherung (alle Versicherungsvertraege auflisten)
    @FXML
    private TableView<VersicherungsVertrag> sceneVersicherung_alleVertraege_table;

    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_id;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_versicherungstyp;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_vorName;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_nachName;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_ausweisNummer;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_role;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_startDatum;
    @FXML
    private TableColumn<VersicherungsVertrag, String> sceneVersicherung_alleVertraege_table_column_endDatum;

    @FXML
    private Button sceneVersicherung_alleVertraege_button_delete;

    // Scene - Versicherung (VersicherungsTypen bearbeiten)

    @FXML
    private TableView<VersicherungsTyp> sceneVersicherung_VersicherungsTypen_table;

    @FXML
    private TableColumn<VersicherungsTyp, String> sceneVersicherung_VersicherungsTypen_table_column_id;

    @FXML
    private TableColumn<VersicherungsTyp, String> sceneVersicherung_VersicherungsTypen_table_column_typname;

    @FXML
    private Button sceneVersicherung_VersicherungsTypen_button_delete;

    // Scene - Versicherung (neue Vertraege erstellen)

    @FXML
    private TextField sceneVersicherung_neueVertraege_field_personausweisnummer;
    
    @FXML
    private ComboBox<PersonTyp> sceneVersicherung_neueVertraege_combobox_persontyp = new ComboBox<>();

    @FXML
    private ComboBox<VersicherungsTyp> sceneVersicherung_neueVertraege_combobox_versicherungstyp = new ComboBox<>();

    @FXML
    private DatePicker sceneVersicherung_neueVertraege_datepicker_startdatum;

    @FXML
    private DatePicker sceneVersicherung_neueVertraege_datepicker_enddatum;
    
    /* Daten */
    private ObservableList<Kunde> datenKunden = FXCollections.observableArrayList();
    private ObservableList<Mitarbeiter> datenMitarbeiter = FXCollections.observableArrayList();
    private ObservableList<VersicherungsTyp> datenVersicherungsTypen = FXCollections.observableArrayList();
    private ObservableList<VersicherungsVertrag> datenVersicherungsVertraege = FXCollections.observableArrayList();

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

            hideAllSceneMitarbeiterItems();
            sceneMitarbeiter_buttons.setVisible(true);
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
    private void handleBackToMitarbeiterClick(ActionEvent event){
        leftMenu_btnMitarbeitern.fire();
    }

    @FXML
    private void handleSceneMitarbeiterButtonClicks(ActionEvent event)
    {
        hideAllSceneMitarbeiterItems();
        if(event.getSource() == sceneMitarbeiter_button_auflisten){
            sceneMitarbeiter_alleMitarbeiter.setVisible(true);
            sceneUrl.setText("/versicherung/mitarbeiter/alleMitarbeiter");
            sceneName.setText("Alle Mitarbeiter");
            refreshAlleMitarbeiterList();
        }
        else if (event.getSource() == sceneMitarbeiter_button_neueMitarbeiter){
            sceneMitarbeiter_neueMitarbeiter.setVisible(true);
            sceneUrl.setText("/versicherung/kunden/neuerMitarbeiter");
            sceneName.setText("Neuer Mitarbeiter");
        }
    }

    @FXML
    private void handleErstellenNeueMitarbeiterClick(ActionEvent event){
        String vorName = sceneMitarbeiter_neueMitarbeiter_field_vorname.getText();
        String nachName = sceneMitarbeiter_neueMitarbeiter_field_nachname.getText();
        String ausweisNummer = sceneMitarbeiter_neueMitarbeiter_field_ausweisnummer.getText();
        String role = sceneMitarbeiter_neueMitarbeiter_field_role.getText();
        String geburstDatum = sceneMitarbeiter_neueMitarbeiter_field_geburstdatum.getText();
        String telefonNummer = sceneMitarbeiter_neueMitarbeiter_field_telefonnummer.getText();
        String adresse = sceneMitarbeiter_neueMitarbeiter_field_adresse.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date geburstDatum_dateFormat = null;
        try {
            geburstDatum_dateFormat = dateFormat.parse(geburstDatum);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Mitarbeiter mitarbeiter = new Mitarbeiter(null, ausweisNummer, vorName, nachName, geburstDatum_dateFormat, telefonNummer, adresse, role);
        if (DatabaseMitarbeiter.erstelleNeuMitarbeiter(mitarbeiter)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Neuer Mitarbeiter wurde erfolgreich erstellt.");
            alert.show();
            leftMenu_btnMitarbeitern.fire();
        }
    }

    @FXML
    private void handleLoeschenMitarbeiterClick(ActionEvent event){
        Mitarbeiter selectedMitarbeiter = sceneMitarbeiter_alleMitarbeiter_table.getSelectionModel().getSelectedItem();

        if (selectedMitarbeiter == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mitarbeiter Löschen");
        alert.setHeaderText(selectedMitarbeiter.getVorName() + " " + selectedMitarbeiter.getNachName());
        alert.setContentText("Möchten Sie diesen Mitarbeiter wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (DatabaseMitarbeiter.loescheMitarbeiter(selectedMitarbeiter)){
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Mitarbeiter Löschen");
                confirmationAlert.setHeaderText(selectedMitarbeiter.getVorName() + " " + selectedMitarbeiter.getNachName());
                confirmationAlert.setContentText("Mitarbeiter wurde erfolgreich gelöscht.");
                confirmationAlert.show();
                refreshAlleKundenList();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Mitarbeiter Löschen");
                errorAlert.setHeaderText(selectedMitarbeiter.getVorName() + " " + selectedMitarbeiter.getNachName());
                errorAlert.setContentText("Mitarbeiter konnte nicht gelöscht werden.");
                errorAlert.show();
            }
        }else{
            System.out.println("Canceled");
        }
    }

    @FXML
    private void handleBackToVersicherungClick(ActionEvent event){
        leftMenu_btnVersicherung.fire();
    }

    @FXML
    private void handleSceneVersicherungButtonClicks(ActionEvent event) {
        hideAllSceneVersicherungsItems();
        if (event.getSource() == sceneVersicherung_button_vertraege_auflisten)
        {
            sceneVersicherung_alleVertraege.setVisible(true);
            refreshVersicherungsVertraegenList();
        }
        else if (event.getSource() == sceneVersicherung_button_vertraege_erstellen){
            sceneVersicherung_neueVertraege_erstellen.setVisible(true);
            refreshVersicherungsVertrageErtellenItems();
        }
        else if (event.getSource() == sceneVersicherung_button_typen_bearbeiten) {
            sceneVersicherung_VersicherungsTypen.setVisible(true);
            refreshVersicherungsTypenList();
        }
    }

    @FXML
    private void handleErstellenNeueVersicherungsVertraegeClick(ActionEvent event){
        String ausweisNummer = sceneVersicherung_neueVertraege_field_personausweisnummer.getText();

        PersonTyp personTyp = sceneVersicherung_neueVertraege_combobox_persontyp.getValue();
        VersicherungsTyp versicherungsTyp = sceneVersicherung_neueVertraege_combobox_versicherungstyp.getValue();

        String versicherungsTyp_id = versicherungsTyp.getId();

        Date startDatum = null;
        try {
            startDatum = new SimpleDateFormat("yyyy-MM-dd").parse(sceneVersicherung_neueVertraege_datepicker_startdatum.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDatum = null;
        try {
            endDatum = new SimpleDateFormat("yyyy-MM-dd").parse(sceneVersicherung_neueVertraege_datepicker_enddatum.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        VersicherungsVertrag versicherungsVertrag;
        Person person = DatabasePerson.getPersonFrom(ausweisNummer, personTyp);
        if (person != null){
            versicherungsVertrag = new VersicherungsVertrag(null, versicherungsTyp_id, versicherungsTyp.getName(), person, personTyp, startDatum, endDatum, null);
            if (DatabaseVersicherung.erstelleNeueVersicherungsVertraege(versicherungsVertrag)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Neuer Vertrag wurde erfolgreich erstellt.");
                alert.show();
                leftMenu_btnVersicherung.fire();
                refreshVersicherungsVertraegenList();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Person mit der Ausweisnummer " + ausweisNummer + " existiert nicht.");
            alert.show();
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

    @FXML
    private void handleLoeschenVersicherungsVertraegeClick(ActionEvent event)
    {
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearSceneHeader();
        hideAllSceneItems();

        initializeAlleKundenTableView();
        initializeAlleMitarbeiterTableView();
        initializeVersicherungsTypenTableView();
        initializeAlleVersicherungsVertraegeTableView();
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

    public void hideAllSceneMitarbeiterItems()
    {
        sceneMitarbeiter_stackpane.getChildren().forEach((scene) -> {
            scene.setVisible(false);
        });
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

    public void initializeAlleMitarbeiterTableView()
    {
        sceneMitarbeiter_alleMitarbeiter_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        sceneMitarbeiter_alleMitarbeiter_table_column_ausweisNummer.setCellValueFactory(new PropertyValueFactory<>("AusweisNummer"));
        sceneMitarbeiter_alleMitarbeiter_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("VorName"));
        sceneMitarbeiter_alleMitarbeiter_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("NachName"));
        sceneMitarbeiter_alleMitarbeiter_table_column_role.setCellValueFactory(new PropertyValueFactory<>("Arbeit_role"));
        sceneMitarbeiter_alleMitarbeiter_table_column_geburstDatum.setCellValueFactory(new PropertyValueFactory<>("GeburstDatum"));
        sceneMitarbeiter_alleMitarbeiter_table_column_telefonNummer.setCellValueFactory(new PropertyValueFactory<>("TelefonNummer"));
        sceneMitarbeiter_alleMitarbeiter_table_column_adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));

        sceneMitarbeiter_alleMitarbeiter_button_delete.disableProperty().bind(Bindings.isEmpty(sceneMitarbeiter_alleMitarbeiter_table.getSelectionModel().getSelectedItems()));

        refreshAlleMitarbeiterList();
    }

    public void refreshAlleMitarbeiterList()
    {
        try{
            ArrayList<Mitarbeiter> mitarbeiterList = DatabaseMitarbeiter.getAlleMitarbeiter();
            datenMitarbeiter = FXCollections.observableList(mitarbeiterList);
            sceneMitarbeiter_alleMitarbeiter_table.setItems(datenMitarbeiter);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void initializeAlleVersicherungsVertraegeTableView()
    {
        sceneVersicherung_alleVertraege_table_column_id.setCellValueFactory(new PropertyValueFactory<>("Vertrag_id"));
        sceneVersicherung_alleVertraege_table_column_versicherungstyp.setCellValueFactory(new PropertyValueFactory<>("Versicherungstyp_name"));
        sceneVersicherung_alleVertraege_table_column_vorName.setCellValueFactory(new PropertyValueFactory<>("Person_VorName"));
        sceneVersicherung_alleVertraege_table_column_nachName.setCellValueFactory(new PropertyValueFactory<>("Person_NachName"));
        sceneVersicherung_alleVertraege_table_column_ausweisNummer.setCellValueFactory(new PropertyValueFactory<>("Person_AusweisNummer"));
        sceneVersicherung_alleVertraege_table_column_role.setCellValueFactory(new PropertyValueFactory<>("Person_typName"));
        sceneVersicherung_alleVertraege_table_column_startDatum.setCellValueFactory(new PropertyValueFactory<>("StartDatum"));
        sceneVersicherung_alleVertraege_table_column_endDatum.setCellValueFactory(new PropertyValueFactory<>("EndDatum"));

        sceneVersicherung_alleVertraege_button_delete.disableProperty().bind(Bindings.isEmpty(sceneVersicherung_alleVertraege_table.getSelectionModel().getSelectedItems()));

        refreshVersicherungsVertraegenList();
    }

    public void refreshVersicherungsVertraegenList()
    {
        try{
            ArrayList<VersicherungsVertrag> versicherungsVertraegeList = DatabaseVersicherung.getAllVersicherungsVertraege();
            datenVersicherungsVertraege = FXCollections.observableList(versicherungsVertraegeList);
            sceneVersicherung_alleVertraege_table.setItems(datenVersicherungsVertraege);
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

    public void refreshVersicherungsVertrageErtellenItems()
    {
        // Combobox for PersonTyp
        sceneVersicherung_neueVertraege_combobox_persontyp.getItems().setAll(PersonTyp.values());

        sceneVersicherung_neueVertraege_combobox_persontyp.setConverter(new StringConverter<PersonTyp>() {
            @Override
            public String toString(PersonTyp object) {
                if (object == null) return null;
                return object.toString();
            }

            @Override
            public PersonTyp fromString(String string) {
                return PersonTyp.valueOf(string);
            }
        });

        sceneVersicherung_neueVertraege_combobox_persontyp.setValue(PersonTyp.Kunde);

        sceneVersicherung_neueVertraege_combobox_persontyp.valueProperty().addListener((observable, oldValue, newValue) -> {

        });

        // Combobox for VersicherungsTyp
        sceneVersicherung_neueVertraege_combobox_versicherungstyp.setItems(datenVersicherungsTypen);

        sceneVersicherung_neueVertraege_combobox_versicherungstyp.setConverter(new StringConverter<VersicherungsTyp>() {
            @Override
            public String toString(VersicherungsTyp object) {
                if (object == null) return null;
                return object.getName();
            }

            @Override
            public VersicherungsTyp fromString(String string) {
                for (VersicherungsTyp versicherungsTyp : datenVersicherungsTypen) {
                    if (versicherungsTyp.getName().equals(string)) {
                        return versicherungsTyp;
                    }
                }

                return null;
            }
        });

        if (!datenVersicherungsTypen.isEmpty())
            sceneVersicherung_neueVertraege_combobox_versicherungstyp.setValue(datenVersicherungsTypen.get(0));
    }
}