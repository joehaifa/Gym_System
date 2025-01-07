package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;


public class CustomerPage extends VBox {
    private GymData gymData;

    private GridPane gridPane = new GridPane();




    /**
     * --------------------------------------------Texts and TextFields---------------------------------------------
     */
    private Text txtId = new Text("ID:");
    private TextField tfId = new TextField();
    private Text txtFname = new Text("First Name:");
    private TextField tfFname = new TextField();
    private Text txtLname = new Text("Last Name:");
    private TextField tfLname = new TextField();
    private Text txtPhoneNumber = new Text("Phone Number:");
    private TextField tfPhoneNumber = new TextField();
    private Text txtEmail = new Text("Email:");
    private TextField tfEmail = new TextField();
    private Text txtYearOfBirth = new Text("Year of Birth:");
    private TextField tfYearOfBirth = new TextField();
    private Text txtStartDate = new Text("Start date:");
    private DatePicker dtStartDate = new DatePicker();
    private Text txtEndDate = new Text("End date:");
    private DatePicker dtEndDate = new DatePicker();
    //private Text txtStatus = new Text("Active:"); private CheckBox cbStatus = new CheckBox();


    //Label lblStatus = new Label(txtStatus.getText(), cbStatus);


    /**
     * --------------------------------------------------Fonts and Styles----------------------------------------------
     */
    private Font ft1 = Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
    private String tfUnselectedStyle = "-fx-background-color: #8CDBA9; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";
    private String tfSelectedStyle = "-fx-background-color: #B4EDC5; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";
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

    private String dtStyle = "-fx-control-inner-background: #8DC5FF ;-fx-background-radius : 5;"
            + "-fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";
    private Font ft2 = Font.font("Calibri", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20);
    private Color statusCheckedColor = Color.LIMEGREEN;
    private Color statusUncheckedColor = Color.RED;


    /**
     * -----------------------------------------------------ERROR texts-----------------------------------------------
     */

    private Text txtIdRequiredError = new Text("Id is required.");
    private Text txtIdExistingError = new Text("Id already exists.");
    private Text txtIdNotExistingError = new Text("Id does not exist.");
    private Text txtFnameError = new Text("First name is required.");
    private Text txtLnameError = new Text("Last name is required.");
    private Text txtPhoneNumberError = new Text("Phone number is required");
    private Text txtEmailError = new Text("Invalid email format.");
    private Text txtYearOfBirthError = new Text("Invalid year.");
    private Text txtDatesError = new Text("Invalid Dates.");
    private Text txtEndDateError = new Text("Invalid end date");



    private TableView<Customer> table = new TableView<Customer>();


    private Color red = new Color(1, 0, 0, 1);
    private Color transparent = new Color(1, 1, 1, 0);
    private Font ft3 = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 15);


    /**
     * -----------------------------------------------------Buttons------------------------------------------------
     */
    private Button btAdd = new Button("Add");
    private Button btDelete = new Button("Delete");
    private Button btUpdate = new Button("Update");
    private Button btClear = new Button("Clear");


    /**
     * -------------------------------------------------Constructor---------------------------------------------
     */
    public CustomerPage(GymData gymData) {
        /**-----------------------getting the gymData variable-------------------*/
        this.gymData = gymData;
        getCustomerData();

        /**------------Overall alignment and design of this GridPane------------*/
        gridPane.setVgap(2);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);


        /**----------------------Fonts for texts-----------------------*/
        txtId.setFont(ft1);
        txtFname.setFont(ft1);
        txtLname.setFont(ft1);
        txtPhoneNumber.setFont(ft1);
        txtEmail.setFont(ft1);
        txtYearOfBirth.setFont(ft1);
        txtStartDate.setFont(ft1);
        txtEndDate.setFont(ft1);
        //txtStatus.setFont(ft1);


        /**--------Editing Width and Height for TextFields----------*/
        int a = 80;
        int b = 250;
        tfId.setMaxWidth(a);
        tfFname.setMaxWidth(b);
        tfFname.setMinWidth(b);
        tfLname.setMaxWidth(b);
        tfLname.setMinWidth(b);
        tfPhoneNumber.setMaxWidth(b);
        tfPhoneNumber.setMinWidth(b);
        tfEmail.setMaxWidth(b);
        tfEmail.setMinWidth(b);
        tfYearOfBirth.setMaxWidth(a);
        tfYearOfBirth.setMinWidth(a);
        dtStartDate.setMaxWidth(120);
        dtStartDate.setMinWidth(120);
        dtEndDate.setMaxWidth(120);
        dtEndDate.setMinWidth(120);


        //lblStatus.setFont(ft1);
        //lblStatus.setContentDisplay(ContentDisplay.RIGHT);
        //lblStatus.setTextFill(statusCheckedColor);


        //cbStatus.setSelected(true);


        /**---------------------------Editing Buttons Size, Font, Margin-------------------*/

        btAdd.setPrefWidth(100);
        btDelete.setPrefWidth(100);
        btUpdate.setPrefWidth(100);
        btClear.setPrefWidth(100);

        btAdd.setFont(ft2);
        btDelete.setFont(ft2);
        btUpdate.setFont(ft2);
        btClear.setFont(ft2);


        GridPane.setMargin(btAdd, new Insets(5, 0, 5, 0));
        GridPane.setMargin(btDelete, new Insets(5, 0, 5, 0));
        GridPane.setMargin(btUpdate, new Insets(5, 0, 5, 0));
        GridPane.setMargin(btClear, new Insets(5, 0, 5, 0));


        /**----------------------Editing TextField & Button colors-------------------*/


        tfId.setStyle(tfUnselectedStyle);
        tfFname.setStyle(tfUnselectedStyle);
        tfLname.setStyle(tfUnselectedStyle);
        tfPhoneNumber.setStyle(tfUnselectedStyle);
        tfEmail.setStyle(tfUnselectedStyle);
        tfYearOfBirth.setStyle(tfUnselectedStyle);
        dtStartDate.setStyle(dtStyle);
        dtEndDate.setStyle(dtStyle);
        btAdd.setStyle(btUnclickedStyle);
        btDelete.setStyle(btUnclickedStyle);
        btUpdate.setStyle(btUnclickedStyle);
        btClear.setStyle(btUnclickedStyle);


        /**------------TextFields events -> changing Color  && error messages disappear---------*/

        tfId.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfId);
                txtIdRequiredError.setFill(Color.TRANSPARENT);
                txtIdExistingError.setFill((Color.TRANSPARENT));
                txtIdNotExistingError.setFill(Color.TRANSPARENT);
            }
        });
        tfFname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfFname);
                txtFnameError.setFill(Color.TRANSPARENT);
            }
        });

        tfLname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfLname);
                txtLnameError.setFill(Color.TRANSPARENT);
            }
        });

        tfPhoneNumber.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfPhoneNumber);
                txtPhoneNumberError.setFill(Color.TRANSPARENT);
            }
        });

        tfEmail.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfEmail);
                txtEmailError.setFill(Color.TRANSPARENT);
            }
        });

        tfYearOfBirth.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfYearOfBirth);
                txtYearOfBirthError.setFill(Color.TRANSPARENT);
            }
        });


        /**-----------------Editing error texts Font, Color, Alignment, Margin---------------------*/
        txtIdRequiredError.setFont(ft3);
        txtIdExistingError.setFont(ft3);
        txtIdNotExistingError.setFont(ft3);
        txtFnameError.setFont(ft3);
        txtLnameError.setFont(ft3);
        txtDatesError.setFont(ft3);
        txtYearOfBirthError.setFont(ft3);
        txtPhoneNumberError.setFont(ft3);
        txtEmailError.setFont(ft3);

        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtDatesError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);

        GridPane.setHalignment(txtIdRequiredError, HPos.LEFT);
        GridPane.setHalignment(txtIdExistingError, HPos.LEFT);
        GridPane.setHalignment(txtIdNotExistingError, HPos.LEFT);
        GridPane.setHalignment(txtFnameError, HPos.RIGHT);
        GridPane.setHalignment(txtLnameError, HPos.RIGHT);
        GridPane.setHalignment(txtDatesError, HPos.LEFT);
        GridPane.setHalignment(txtYearOfBirthError, HPos.LEFT);
        GridPane.setHalignment(txtPhoneNumberError, HPos.LEFT);
        GridPane.setHalignment(txtEmailError, HPos.LEFT);

        GridPane.setValignment(txtIdRequiredError, VPos.TOP);
        GridPane.setValignment(txtIdExistingError, VPos.CENTER);
        GridPane.setValignment(txtIdNotExistingError, VPos.BOTTOM);
        GridPane.setValignment(txtFnameError, VPos.TOP);
        GridPane.setValignment(txtLnameError, VPos.TOP);
        GridPane.setValignment(txtDatesError, VPos.TOP);
        GridPane.setValignment(txtYearOfBirthError, VPos.TOP);
        GridPane.setValignment(txtPhoneNumberError, VPos.TOP);
        GridPane.setValignment(txtEmailError, VPos.TOP);

        GridPane.setMargin(txtIdExistingError, new Insets(0, 0, 0, -30));
        GridPane.setMargin(txtIdNotExistingError, new Insets(0, 0, 0, -30));
        GridPane.setMargin(txtIdRequiredError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtFnameError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtLnameError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtDatesError, new Insets(5, 0, 0, -0));
        GridPane.setMargin(txtYearOfBirthError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtPhoneNumberError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtEmailError, new Insets(-10, 0, 0, 0));


        /**----------------------Adding nodes to GridPane------------------------*/

        gridPane.add(txtIdNotExistingError, 1, 0);
        gridPane.add(txtId, 0, 1);
        gridPane.add(tfId, 1, 1);
        gridPane.add(txtIdExistingError, 2, 1);
        gridPane.add(txtIdRequiredError, 1, 2);

        gridPane.add(txtFname, 0, 3);
        gridPane.add(tfFname, 1, 3);
        gridPane.add(txtFnameError, 1, 4);

        gridPane.add(txtLname, 0, 5);
        gridPane.add(tfLname, 1, 5);
        gridPane.add(txtLnameError, 1, 6);

        gridPane.add(txtPhoneNumber, 3, 3);
        gridPane.add(tfPhoneNumber, 4, 3);
        gridPane.add(txtPhoneNumberError, 4, 4);

        gridPane.add(txtEmail, 3, 5);
        gridPane.add(tfEmail, 4, 5);
        gridPane.add(txtEmailError, 4, 6);

        gridPane.add(txtYearOfBirth, 0, 7);
        gridPane.add(tfYearOfBirth, 1, 7);
        gridPane.add(txtYearOfBirthError, 1, 8);


        gridPane.add(new Text("             "), 7, 0);
        gridPane.add(btAdd, 8, 1);
        gridPane.add(btDelete, 8, 3);
        gridPane.add(btUpdate, 8, 5);
        gridPane.add(btClear, 8, 7);


        gridPane.add(txtStartDate, 0, 9);
        gridPane.add(dtStartDate, 1, 9);
        gridPane.add(txtDatesError, 2, 10);

        gridPane.add(txtEndDate, 2, 9);
        gridPane.add(dtEndDate, 3, 9);


        /**---------------------------------ColumnSpan-----------------------------------*/
        GridPane.setColumnSpan(tfFname, 2);
        GridPane.setColumnSpan(tfLname, 2);
        GridPane.setColumnSpan(tfPhoneNumber, 2);
        GridPane.setColumnSpan(tfEmail, 2);


        /**-----------------------------------Alignment of Texts---------------------------*/
        GridPane.setHalignment(txtId, HPos.RIGHT);
        GridPane.setHalignment(txtFname, HPos.RIGHT);
        GridPane.setHalignment(txtLname, HPos.RIGHT);
        GridPane.setHalignment(txtPhoneNumber, HPos.RIGHT);
        GridPane.setHalignment(txtEmail, HPos.RIGHT);
        GridPane.setHalignment(txtYearOfBirth, HPos.RIGHT);
        GridPane.setHalignment(txtStartDate, HPos.RIGHT);
        GridPane.setHalignment(txtEndDate, HPos.RIGHT);
        //GridPane.setHalignment(txtStatus, HPos.RIGHT);

        //GridPane.setHalignment(lblStatus,HPos.LEFT);


















        /**---------------------------------Table----------------------------------*/
        table.setEditable(false);



        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));

        TableColumn fnameCol = new TableColumn("First Name");
        fnameCol.setMinWidth(100);
        fnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));

        TableColumn lnameCol = new TableColumn("Last Name");
        lnameCol.setMinWidth(100);
        lnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        TableColumn phoneNumberCol = new TableColumn("Phone Number");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));

        TableColumn yobCol = new TableColumn("Year Of Birth");
        yobCol.setMinWidth(100);
        yobCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("yearOfBirth"));

        TableColumn startDateCol = new TableColumn("Start Date");
        startDateCol.setMinWidth(100);
        startDateCol.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("startDate"));

        TableColumn endDateCol = new TableColumn("End Date");
        endDateCol.setMinWidth(100);
        endDateCol.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("endDate"));










        table.getColumns().addAll(idCol, fnameCol, lnameCol, phoneNumberCol,emailCol, yobCol, startDateCol, endDateCol);
        table.setMinWidth(802);
        table.setMaxWidth(802);

        table.setRowFactory(tv -> new TableRow<Customer>() {
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


        table.setOnMouseClicked(event ->
        {
            Customer selectedCustomer = table.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null)
            {
                String selectedId = selectedCustomer.getId();
                String selectedFname = selectedCustomer.getFirstName();
                String selectedLname = selectedCustomer.getLastName();
                String selectedPn = selectedCustomer.getPhoneNumber();
                String selectedEmail = selectedCustomer.getEmail();
                int selectedYob = selectedCustomer.getYearOfBirth();
                LocalDate selectedStartDate = selectedCustomer.getStartDate();
                LocalDate selectedEndDate = selectedCustomer.getEndDate();

                tfId.setText(selectedId);
                tfFname.setText(selectedFname);
                tfLname.setText(selectedLname);
                tfPhoneNumber.setText(selectedPn);
                tfEmail.setText(selectedEmail);
                tfYearOfBirth.setText(String.valueOf(selectedYob));
                dtStartDate.setValue(selectedStartDate);
                dtEndDate.setValue(selectedEndDate);
            }
        });


        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setMaxWidth(820);




        getChildren().addAll(gridPane, scrollPane);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20, 10, 20, 10));









        btClear.setOnAction(e ->
        {
            buttonClickedAnimation((Button) e.getSource());
            clear();
        });

        btAdd.setOnAction(e ->
        {
            buttonClickedAnimation((Button) e.getSource());
            add();
        });

        btUpdate.setOnAction(e ->
        {
            buttonClickedAnimation((Button) e.getSource());
            update();
        });

        btDelete.setOnAction(e ->
        {
            buttonClickedAnimation((Button) e.getSource());
            delete();
        });


    }
    // Lorsque le bouton est cliqué, applique une animation sur le bouton en changeant son style et en réinitialisant après un court délai.
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

    // Change le style des champs de texte selon celui qui est sélectionné
    private void tfSelectedStyle(TextField tfSelected) {
        if (tfSelected == tfId)
            tfId.setStyle(tfSelectedStyle);
        else
            tfId.setStyle(tfUnselectedStyle);

        if (tfSelected == tfFname)
            tfFname.setStyle(tfSelectedStyle);
        else
            tfFname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfLname)
            tfLname.setStyle(tfSelectedStyle);
        else
            tfLname.setStyle(tfUnselectedStyle);

        if (tfSelected == tfPhoneNumber)
            tfPhoneNumber.setStyle(tfSelectedStyle);
        else
            tfPhoneNumber.setStyle(tfUnselectedStyle);

        if (tfSelected == tfEmail)
            tfEmail.setStyle(tfSelectedStyle);
        else
            tfEmail.setStyle(tfUnselectedStyle);

        if (tfSelected == tfYearOfBirth)
            tfYearOfBirth.setStyle(tfSelectedStyle);
        else
            tfYearOfBirth.setStyle(tfUnselectedStyle);

    }
    // Réinitialise tous les champs et messages d'erreur
    private void clear() {
        tfFname.setStyle(tfUnselectedStyle);
        tfLname.setStyle(tfUnselectedStyle);
        tfPhoneNumber.setStyle(tfUnselectedStyle);
        tfEmail.setStyle(tfUnselectedStyle);
        tfYearOfBirth.setStyle(tfUnselectedStyle);

        tfId.setText("");
        tfFname.setText("");
        tfLname.setText("");
        tfPhoneNumber.setText("");
        tfEmail.setText("");
        tfYearOfBirth.setText("");
        dtStartDate.setValue(null);
        dtEndDate.setValue(null);

        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtDatesError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);
    }

    // Ajoute un client après avoir vérifié les erreurs de saisie et effectué des validations
    private void add() {
        getCustomerData();


        String id = tfId.getText();
        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        int yob = Year.now().getValue();
        LocalDate startDate = dtStartDate.getValue();
        LocalDate endDate = dtEndDate.getValue();
        Boolean foundError = false;


        try {
            yob = Integer.parseInt(tfYearOfBirth.getText());
            if (yob > Year.now().getValue()|| yob<=0) {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        } catch (NumberFormatException exc) {
            String str = tfYearOfBirth.getText();
            if (!str.isEmpty()) {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        }

        if ((startDate == null) || (endDate == null)) {
            foundError = true;
            txtDatesError.setFill(Color.RED);
        } else if (startDate.compareTo(endDate) > 0) {
            foundError = true;
            txtDatesError.setFill(Color.RED);
        }


        if (id.equals("")) {
            foundError = true;
            txtIdRequiredError.setFill(Color.RED);
        }


        if (fname.equals("")) {
            foundError = true;
            txtFnameError.setFill(Color.RED);
        }

        if (lname.equals("")) {
            foundError = true;
            txtLnameError.setFill(Color.RED);
        }

        if (phoneNumber.equals("")) {
            foundError = true;
            txtPhoneNumberError.setFill(Color.RED);
        }

        if (!Person.checkEmail(email)) {
            foundError = true;
            txtEmailError.setFill(Color.RED);
        }

        if (!foundError)
        {
            try
            {
                GymData.addCustomer(fname, lname, phoneNumber, email, yob, startDate, endDate, id);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Customer Page : Add button : " + exc.getMessage());
            }
        }
        getCustomerData();
    }

    // Met à jour un client existant après avoir vérifié les erreurs de saisie
    private void update() {
        getCustomerData();

        String id = tfId.getText();
        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        int yob = Year.now().getValue();
        LocalDate startDate = dtStartDate.getValue();
        LocalDate endDate = dtEndDate.getValue();
        Boolean foundError = false;


        try {
            yob = Integer.parseInt(tfYearOfBirth.getText());
            if (yob > Year.now().getValue()|| yob<=0) {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        } catch (NumberFormatException exc) {
            String str = tfYearOfBirth.getText();
            if (!str.isEmpty()) {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        }

        if ((startDate == null) || (endDate == null)) {
            foundError = true;
            txtDatesError.setFill(Color.RED);
        } else if (startDate.compareTo(endDate) > 0) {
            foundError = true;
            txtDatesError.setFill(Color.RED);
        }


        if (id.equals("")) {
            foundError = true;
            txtIdRequiredError.setFill(Color.RED);
        } else if (!GymData.searchIdCustomers(id, gymData)) {
            foundError = true;
            txtIdNotExistingError.setFill(Color.RED);
        } else {
            txtIdExistingError.setFill(Color.TRANSPARENT);
        }

        if (fname.equals("")) {
            foundError = true;
            txtFnameError.setFill(Color.RED);
        }

        if (lname.equals("")) {
            foundError = true;
            txtLnameError.setFill(Color.RED);
        }

        if (phoneNumber.equals("")) {
            foundError = true;
            txtPhoneNumberError.setFill(Color.RED);
        }

        if (!Person.checkEmail(email)) {
            foundError = true;
            txtEmailError.setFill(Color.RED);
        }


        if (!foundError)
        {
            if(!foundError)
            {
                try
                {
                    GymData.updateCustomer(fname, lname, phoneNumber, email, yob, startDate, endDate, id);
                    clear();

                } catch (Exception exc) {
                    GymData.catchExceptions(LocalDateTime.now() + "   Customer Page : Add button : " + exc.getMessage());
                }
            }
            getCustomerData();
        }
    }
    // Supprime un client
    private void delete()
    {
        getCustomerData();
        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtDatesError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);

        String id = tfId.getText();

        boolean foundError = true;


        for(Customer customer: gymData.getCustomers())
        {
            if(id.equals(customer.getId()))
            {
                foundError = false;
            }
        }

        if(foundError)
        {
            txtIdNotExistingError.setFill(Color.RED);
        }

        if(id.equals(""))
        {
            foundError = true;
            txtIdRequiredError.setFill(Color.RED);
        }




        if(!foundError)
        {
            try
            {
                GymData.deleteCustomer(id);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Customer Page : Add button : " + exc.getMessage());
            }
        }
        getCustomerData();
    }



    // Récupère les données des clients et les affiche dans la table
    public void getCustomerData()
    {
        this.gymData.setCustomers(GymData.retrieveAllCustomers());
        ObservableList<Customer> data = FXCollections.observableArrayList(gymData.getCustomers());
        table.setItems(data);

    }
}
