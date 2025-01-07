package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class AppointmentPage extends HBox
{
    private GymData gymData;




    private TextField tfCustomerFname = new TextField();
    private TextField tfCustomerLname = new TextField();

    private TextField tfEmployeeFname = new TextField();
    private TextField tfEmployeeLname = new TextField();



    private TextField tfCustomerId = new TextField();
    private TextField tfEmployeeId = new TextField();
    private TextField tfAppointmentId =new TextField();


    private TableView<Customer> tableCustomer = new TableView<Customer>();
    private TableView<Employee> tableEmployee = new TableView<Employee>();
    private TableView<Appointment> tableAppointment = new TableView<Appointment>();


    Button btSearchCustomer = new Button("Search");
    Button btSearchEmployee = new Button("Search");
    Button btAdd = new Button("Add");



    private DatePicker date = new DatePicker();
    private ComboBox<Integer> cbTime = new ComboBox<Integer>();








    private String btUnclickedStyle = "-fx-background-radius: 15px ;"
            + "-fx-background-color: #A9CCE3;"
            + "-fx-border-radius : 15px;"
            + "-fx-border-width : 2px;"
            + "-fx-border-color : darkblue;";

    private String btClickedStyle = "-fx-background-radius: 15px ;"
            + "-fx-background-color: #42647F;"
            + "-fx-border-radius : 15px;"
            + "-fx-border-width : 2px;"
            + "-fx-border-color : darkblue;";


    private String tfUnselectedStyle = "-fx-background-color: #8CDBA9; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5; "
            + "-fx-prompt-text-fill: #4d4d4d;";

    private String tfSelectedStyle = "-fx-background-color: #B4EDC5; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";


    private String dtStyle = "-fx-control-inner-background: #8DC5FF ;-fx-background-radius : 5;"
            + "-fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";








    public AppointmentPage(GymData gymdata)
    {
        super (80);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20,20,20,20));
        this.gymData = gymdata;
        getAppointmentData();












        /**-------------------------------------Table Appointment--------------------------------*/



        tableAppointment.setEditable(false);



        TableColumn appIdCol = new TableColumn("ID");
        appIdCol.setMinWidth(100);
        appIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("id"));

        TableColumn appCustIdCol = new TableColumn("Customer ID");
        appCustIdCol.setMinWidth(100);
        appCustIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("custId"));

        TableColumn appEmpIdCol = new TableColumn("Employee ID");
        appEmpIdCol.setMinWidth(100);
        appEmpIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("empId"));

        TableColumn appDateCol = new TableColumn("Date");
        appDateCol.setMinWidth(100);
        appDateCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("date"));

        TableColumn appTimeCol = new TableColumn("Time");
        appTimeCol.setMinWidth(100);
        appTimeCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("time"));





        tableAppointment.getColumns().addAll(appIdCol, appCustIdCol, appEmpIdCol, appDateCol, appTimeCol);

        tableAppointment.setRowFactory(tv -> new TableRow<Appointment>() {
            @Override
            protected void updateItem(Appointment item, boolean empty) {
                super.updateItem(item, empty);

                if (getIndex() % 2 == 0) {
                    // Even row
                    setStyle("-fx-background-color: #8CDBA9;");
                } else {
                    // Odd row
                    setStyle("-fx-background-color: #B4EDC5;");
                }
            }
        });
        ScrollPane scrollPaneAppointment = new ScrollPane(tableAppointment);















        /**-------------------------------------Table Customer--------------------------------*/

        tableCustomer.setEditable(false);

        TableColumn custIdCol = new TableColumn("ID");
        custIdCol.setMinWidth(100);
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));

        TableColumn custFnameCol = new TableColumn("First Name");
        custFnameCol.setMinWidth(100);
        custFnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));

        TableColumn custLnameCol = new TableColumn("Last Name");
        custLnameCol.setMinWidth(100);
        custLnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        tableCustomer.getColumns().addAll(custIdCol, custFnameCol, custLnameCol);

        tableCustomer.setRowFactory(tv -> new TableRow<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);

                if (getIndex() % 2 == 0) {
                    // Even row
                    setStyle("-fx-background-color: #8CDBA9;");
                } else {
                    // Odd row
                    setStyle("-fx-background-color: #B4EDC5;");
                }
            }
        });

        tableCustomer.setOnMouseClicked(event ->
        {
            Customer selectedCustomer = tableCustomer.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null)
            {
                String selectedText = selectedCustomer.getId();
                tfCustomerId.setText(selectedText);
            }
        });





        ScrollPane scrollPaneCustomer = new ScrollPane(tableCustomer);
        scrollPaneCustomer.setMaxHeight(150);
        scrollPaneCustomer.setMinWidth(320);





















        /**-------------------------------------Table Employee--------------------------------*/

        tableEmployee.setEditable(false);

        TableColumn empIdCol = new TableColumn("ID");
        empIdCol.setMinWidth(100);
        empIdCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));

        TableColumn empFnameCol = new TableColumn("First Name");
        empFnameCol.setMinWidth(100);
        empFnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));

        TableColumn empLnameCol = new TableColumn("Last Name");
        empLnameCol.setMinWidth(100);
        empLnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));

        tableEmployee.getColumns().addAll(empIdCol, empFnameCol, empLnameCol);

        tableEmployee.setRowFactory(tv -> new TableRow<Employee>() {
            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);

                if (getIndex() % 2 == 0) {
                    // Even row
                    setStyle("-fx-background-color: #8CDBA9;");
                } else {
                    // Odd row
                    setStyle("-fx-background-color: #B4EDC5;");
                }
            }
        });


        tableEmployee.setOnMouseClicked(event ->
        {
            Employee selectedEmployee = tableEmployee.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null)
            {
                String selectedText = selectedEmployee.getId();
                tfEmployeeId.setText(selectedText);
            }
        });
        ScrollPane scrollPaneEmployee = new ScrollPane(tableEmployee);
        scrollPaneEmployee.setMaxHeight(150);
        scrollPaneEmployee.setMinWidth(320);






        Integer hours[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        ObservableList<Integer> list = FXCollections.observableArrayList(hours);
        cbTime.getItems().addAll(list);




















        tfCustomerFname.setPromptText("Customer First Name");
        tfCustomerLname.setPromptText("Customer Last Name");
        tfEmployeeFname.setPromptText("Employee First Name");
        tfEmployeeLname.setPromptText("Employee Last Name");
        tfCustomerId.setPromptText("Customer ID");
        tfEmployeeId.setPromptText("Employee ID");

        tfCustomerFname.setStyle(tfUnselectedStyle);
        tfCustomerLname.setStyle(tfUnselectedStyle);
        tfEmployeeFname.setStyle(tfUnselectedStyle);
        tfEmployeeLname.setStyle(tfUnselectedStyle);
        tfCustomerId.setStyle(tfUnselectedStyle);
        tfEmployeeId.setStyle(tfUnselectedStyle);
        date.setStyle(dtStyle);
        cbTime.setStyle(dtStyle);

        btSearchCustomer.setStyle(btUnclickedStyle);
        btSearchEmployee.setStyle(btUnclickedStyle);
        btAdd.setStyle(btUnclickedStyle);






        /**---------TextField selected effect--------*/
        tfCustomerFname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfCustomerFname);
        });

        tfCustomerLname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfCustomerLname);
        });

        tfEmployeeFname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfEmployeeFname);
        });

        tfEmployeeLname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfEmployeeLname);
        });

        tfCustomerId.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfCustomerId);
        });

        tfEmployeeId.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfEmployeeId);
        });





        HBox dateBox = new HBox(5);
        dateBox.getChildren().addAll(new Text("Date :"), date);

        HBox timeBox = new HBox(5);
        timeBox.getChildren().addAll(new Text ("Time :"),cbTime);



        VBox vboxCustomerEmployee = new VBox(10);
        vboxCustomerEmployee.getChildren().addAll(
                tfCustomerFname, tfCustomerLname, btSearchCustomer,scrollPaneCustomer,
                new Text(" "), new Text(" "),
                tfEmployeeFname, tfEmployeeLname, btSearchEmployee, scrollPaneEmployee);


        cbTime.setValue(12);


        tfAppointmentId.setPromptText("Enter an appointment id to cancel it");
        Button btCancel=new Button("Cancel Appointment");
        btCancel.setStyle(btUnclickedStyle);
        HBox hboxCancel=new HBox();
        tfAppointmentId.setStyle(tfUnselectedStyle);
        hboxCancel.getChildren().addAll(tfAppointmentId,btCancel);


        VBox vboxAppointment = new VBox(10);
        vboxAppointment.getChildren().addAll(tfCustomerId, tfEmployeeId, dateBox, timeBox,
                btAdd,hboxCancel, scrollPaneAppointment);





        Platform.runLater(() -> btSearchEmployee.fire());
        Platform.runLater(() -> btSearchCustomer.fire());



        btSearchCustomer.setOnAction(e ->
        {
            getSimilarCustomer(tfCustomerFname.getText(), tfCustomerLname.getText());
            buttonClickedAnimation((Button) e.getSource());
        });
        btSearchEmployee.setOnAction(e ->
        {
            getSimilarEmployee(tfEmployeeFname.getText(), tfEmployeeLname.getText());
            buttonClickedAnimation((Button) e.getSource());
        });
        btAdd.setOnAction(e ->
        {
            add();
            buttonClickedAnimation((Button) e.getSource());
        });

        btCancel.setOnAction(e->{
            cancel();
            buttonClickedAnimation((Button) e.getSource());
        });


        getChildren().addAll(vboxCustomerEmployee,vboxAppointment);


    }












// Cette méthode permet de rechercher des clients similaires
// en fonction de leur prénom et nom, et de mettre à jour la table des clients.

    public void getSimilarCustomer(String fname, String lname)
    {
        this.gymData.setCustomers(GymData.retrieveSimilarCustomer(fname,lname));
        ObservableList<Customer> data = FXCollections.observableArrayList(gymData.getCustomers());
        tableCustomer.setItems(data);

    }

    // Cette méthode permet de rechercher des employés similaires
    // en fonction de leur prénom et nom, et de mettre à jour la table des employés.
    public void getSimilarEmployee(String fname, String lname)
    {
        this.gymData.setEmployees(GymData.retrieveSimilarEmployee(fname,lname));
        ObservableList<Employee> data = FXCollections.observableArrayList(gymData.getEmployees());
        tableEmployee.setItems(data);

    }
    // Cette méthode ajoute un rendez-vous après avoir vérifié que les données sont valides
   // et qu'il n'y a pas de conflits d'horaires.
    void add()
    {
        getAppointmentData();
        String custId = tfCustomerId.getText();
        String empId = tfEmployeeId.getText();
        LocalDate date = this.date.getValue();
        int time = cbTime.getValue();

        boolean foundError = false;

        if (!GymData.searchIdCustomers(custId, gymData)){
            foundError = true;
            showValidationError("This customer do not exist");
        }

        if(!GymData.searchIdEmployees(empId, gymData)){
            foundError = true;
            showValidationError("This employee do not exist");
        }

        if(date == null){
            foundError = true;
            showValidationError("date cannot be null");
        }


        if(!foundError)
        {
            try
            {
                if(GymData.conflictAppointment(custId, empId, date, time)) {
                    GymData.addAppointment(custId, empId, date, time);
                    clear();
                }
                else
                    showValidationError("One person can not have 2 appointments at the same time");


            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Customer Page : Add button : " + exc.getMessage());
            }
        }
        getAppointmentData();
    }

    // Cette méthode permet de récupérer tous les rendez-vous
    // et de mettre à jour la table des rendez-vous.
    public void getAppointmentData()
    {
        this.gymData.setAppointments(GymData.retrieveAllAppointments());
        ObservableList<Appointment> data = FXCollections.observableArrayList(gymData.getAppointments());
        tableAppointment.setItems(data);

    }
    // Cette méthode réinitialise tous les champs de saisie à leurs valeurs par défaut.
    private void clear()
    {
        tfCustomerFname.setText("");
        tfCustomerLname.setText("");
        tfEmployeeFname.setText("");
        tfEmployeeLname.setText("");
        tfEmployeeId.setText("");
        tfCustomerId.setText("");
        date.setValue(null);
        cbTime.setValue(12);

        tfCustomerFname.setStyle(tfUnselectedStyle);
        tfCustomerLname.setStyle(tfUnselectedStyle);
        tfEmployeeFname.setStyle(tfUnselectedStyle);
        tfEmployeeLname.setStyle(tfUnselectedStyle);
        tfEmployeeId.setStyle(tfUnselectedStyle);
        tfCustomerId.setStyle(tfUnselectedStyle);
    }



    // Cette méthode met à jour le style des champs de texte pour indiquer
    // visuellement lequel est actuellement sélectionné.
    private void tfSelectedStyle(TextField tfSelected) {
        if (tfSelected == tfCustomerFname)
            tfCustomerFname.setStyle(tfSelectedStyle);
        else
            tfCustomerFname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfCustomerLname)
            tfCustomerLname.setStyle(tfSelectedStyle);
        else
            tfCustomerLname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfEmployeeFname)
            tfEmployeeFname.setStyle(tfSelectedStyle);
        else
            tfEmployeeFname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfEmployeeLname)
            tfEmployeeLname.setStyle(tfSelectedStyle);
        else
            tfEmployeeLname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfCustomerId)
            tfCustomerId.setStyle(tfSelectedStyle);
        else
            tfCustomerId.setStyle(tfUnselectedStyle);

        if (tfSelected == tfEmployeeId)
            tfEmployeeId.setStyle(tfSelectedStyle);
        else
            tfEmployeeId.setStyle(tfUnselectedStyle);
    }
    // Cette méthode permet d'annuler un rendez-vous en fonction de l'identifiant fourni
    // et met à jour la table des rendez-vous.
    void cancel(){
        String app_id=tfAppointmentId.getText();
        if(app_id.isEmpty()||gymData.findAppId(app_id)==false){
            showValidationError("unvalid appointment id");
        }
        else{
            gymData.deleteAppointment(app_id);
            getAppointmentData();
        }
        tfAppointmentId.clear();
        getAppointmentData();
    }

    // Cette méthode crée une animation visuelle lorsque le bouton est cliqué.
    private void buttonClickedAnimation(Button bt) {
        bt.setStyle(btClickedStyle);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(180), e -> {
        }));

        animation.setOnFinished(e -> {
            bt.setStyle(btUnclickedStyle);
        });

        animation.setCycleCount(1);
        animation.play();

    }
    // Cette méthode affiche une alerte d'erreur de validation avec un message personnalisé.
    private void showValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
