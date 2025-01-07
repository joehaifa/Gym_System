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
import java.time.Year;
import java.time.LocalDateTime;

public class EmployeePage extends VBox
{
    private GymData gymData;
    private GridPane gridPane = new GridPane();
    private CustomerPage customerPage;

    /**--------------------------------------------Texts and TextFields---------------------------------------------*/
    private Text txtId = new Text("ID:"); private TextField tfId = new TextField();
    private Text txtFname = new Text("First Name:"); private TextField tfFname = new TextField();
    private Text txtLname = new Text("Last Name:"); private TextField tfLname = new TextField();
    private Text txtPhoneNumber = new Text("Phone Number:"); private TextField tfPhoneNumber = new TextField();
    private Text txtEmail = new Text("Email:"); private TextField tfEmail = new TextField();
    private Text txtYearOfBirth = new Text("Year of Birth:"); private TextField tfYearOfBirth = new TextField();
    private Text txtPost = new Text("Post:"); private TextField tfPost = new TextField();
    private Text txtSalary = new Text("Salary:"); private TextField tfSalary = new TextField();


    /**--------------------------------------------------Fonts and Styles----------------------------------------------*/
    private Font ft1 = Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
    private String tfUnselectedStyle = "-fx-background-color: #8CDBA9; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;" ;
    private String tfSelectedStyle = "-fx-background-color: #B4EDC5; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;" ;
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
    private Font ft2 = Font.font("Calibri", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20);


    /**-----------------------------------------------------ERROR texts-----------------------------------------------*/

    private Text txtIdRequiredError = new Text ("Id is required.");
    private Text txtIdExistingError = new Text("Id already exists.");
    private Text txtIdNotExistingError = new Text("Id does not exist.");
    private Text txtFnameError = new Text("First name is required.");
    private Text txtLnameError = new Text("Last name is required.");
    private Text txtSalaryError = new Text("Invalid Salary.");
    private Text txtYearOfBirthError = new Text("Invalid year.");
    private Text txtPhoneNumberError = new Text("Phone number is required.");
    private Text txtEmailError = new Text("Invalid email format.");
    private TableView<Employee> table = new TableView<Employee>();
    private Font ft3 = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 15);

    /**-----------------------------------------------------Buttons------------------------------------------------*/
    private Button btAdd = new Button("Add");
    private Button btDelete = new Button("Delete");
    private Button btUpdate = new Button("Update");
    private Button btClear = new Button("Clear");


    /**------------------------------------------------Constructor--------------------------------------------*/

    public EmployeePage(GymData gymData)
    {
        /**-----------------------getting the gymData variable-------------------*/
        this.gymData = gymData;
        getEmployeeData();





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
        txtPost.setFont(ft1);
        txtSalary.setFont(ft1);


        /**--------Editing Width and Height for TextFields----------*/
        int a = 80;
        int b = 250;
        tfId.setMaxWidth(a);
        tfFname.setMaxWidth(b); tfFname.setMinWidth(b);
        tfLname.setMaxWidth(b); tfLname.setMinWidth(b);
        tfPhoneNumber.setMaxWidth(b); tfPhoneNumber.setMinWidth(b);
        tfEmail.setMaxWidth(b); tfEmail.setMinWidth(b);
        tfYearOfBirth.setMaxWidth(a); tfYearOfBirth.setMinWidth(a);
        tfPost.setMaxWidth(b); tfPost.setMinWidth(b);
        tfSalary.setMaxWidth(a);  tfSalary.setMinWidth(a); tfSalary.setMaxHeight(30); tfSalary.setMinHeight(30);

        /**---------------------------Editing Buttons Size, Font, Margin-------------------*/

        btAdd.setPrefWidth(100);
        btDelete.setPrefWidth(100);
        btUpdate.setPrefWidth(100);
        btClear.setPrefWidth(100);

        btAdd.setFont(ft2);
        btDelete.setFont(ft2);
        btUpdate.setFont(ft2);
        btClear.setFont(ft2);

        GridPane.setMargin(btAdd, new Insets(5,0,5,0));
        GridPane.setMargin(btDelete, new Insets(5,0,5,0));
        GridPane.setMargin(btUpdate, new Insets(5,0,5,0));
        GridPane.setMargin(btClear, new Insets(5,0,5,0));


        /**----------------------Editing TextField & Button colors-------------------*/


        tfId.setStyle(tfUnselectedStyle);
        tfFname.setStyle(tfUnselectedStyle);
        tfLname.setStyle(tfUnselectedStyle);
        tfPhoneNumber.setStyle(tfUnselectedStyle);
        tfEmail.setStyle(tfUnselectedStyle);
        tfYearOfBirth.setStyle(tfUnselectedStyle);
        tfPost.setStyle(tfUnselectedStyle);
        tfSalary.setStyle(tfUnselectedStyle);
        btAdd.setStyle(btUnclickedStyle);
        btDelete.setStyle(btUnclickedStyle);
        btUpdate.setStyle(btUnclickedStyle);
        btClear.setStyle(btUnclickedStyle);


        /**------------TextFields events -> changing Color  && error messages disappear---------*/

        tfId.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if(newVal)
            {
                tfSelectedStyle(tfId);
                txtIdExistingError.setFill(Color.TRANSPARENT);
                txtIdRequiredError.setFill(Color.TRANSPARENT);
                txtIdNotExistingError.setFill(Color.TRANSPARENT);
            }
        });

        tfFname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfFname);
                txtFnameError.setFill(Color.TRANSPARENT);
            }
        });

        tfLname.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfLname);
                txtLnameError.setFill(Color.TRANSPARENT);
            }
        });

        tfPhoneNumber.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfPhoneNumber);
                txtPhoneNumberError.setFill(Color.TRANSPARENT);
            }
        });

        tfEmail.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfEmail);
                txtEmailError.setFill(Color.TRANSPARENT);
            }
        });

        tfYearOfBirth.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfYearOfBirth);
                txtYearOfBirthError.setFill(Color.TRANSPARENT);
            }
        });

        tfSalary.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
            {
                tfSelectedStyle(tfSalary);
                txtSalaryError.setFill(Color.TRANSPARENT);
            }
        });

        tfPost.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal)
                tfSelectedStyle(tfPost);
        });


        /**-----------------Editing error texts Font, Color, Alignment, Margin---------------------*/
        txtIdRequiredError.setFont(ft3);
        txtIdExistingError.setFont(ft3);
        txtIdNotExistingError.setFont(ft3);
        txtFnameError.setFont(ft3);
        txtLnameError.setFont(ft3);
        txtSalaryError.setFont(ft3);
        txtYearOfBirthError.setFont(ft3);
        txtPhoneNumberError.setFont(ft3);
        txtEmailError.setFont(ft3);

        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtSalaryError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);

        GridPane.setHalignment(txtIdRequiredError, HPos.LEFT);
        GridPane.setHalignment(txtIdExistingError, HPos.LEFT);
        GridPane.setHalignment(txtIdNotExistingError, HPos.LEFT);
        GridPane.setHalignment(txtFnameError, HPos.RIGHT);
        GridPane.setHalignment(txtLnameError, HPos.RIGHT);
        GridPane.setHalignment(txtSalaryError, HPos.RIGHT);
        GridPane.setHalignment(txtYearOfBirthError, HPos.LEFT);
        GridPane.setHalignment(txtPhoneNumberError, HPos.LEFT);
        GridPane.setHalignment(txtEmailError, HPos.LEFT);

        GridPane.setValignment(txtIdRequiredError, VPos.TOP);
        GridPane.setValignment(txtIdExistingError, VPos.CENTER);
        GridPane.setValignment(txtIdNotExistingError, VPos.TOP);
        GridPane.setValignment(txtFnameError, VPos.TOP);
        GridPane.setValignment(txtLnameError, VPos.TOP);
        GridPane.setValignment(txtSalaryError, VPos.TOP);
        GridPane.setValignment(txtYearOfBirthError, VPos.TOP);
        GridPane.setValignment(txtPhoneNumberError, VPos.TOP);
        GridPane.setValignment(txtEmailError, VPos.TOP);

        GridPane.setMargin(txtIdExistingError, new Insets(0,0,0,-30));
        GridPane.setMargin(txtIdNotExistingError, new Insets(0, 0, 0, -30));
        GridPane.setMargin(txtIdRequiredError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtFnameError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtLnameError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtSalaryError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtYearOfBirthError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtPhoneNumberError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtEmailError, new Insets(-10,0,0,0));


        /**----------------------Adding nodes to GridPane------------------------*/

        gridPane.add(txtIdNotExistingError, 1, 0);
        gridPane.add(txtId, 0, 1);
        gridPane.add(tfId, 1, 1);
        gridPane.add(txtIdExistingError, 2, 1);
        gridPane.add(txtIdRequiredError, 1, 2);

        gridPane.add(txtFname, 0,3);  gridPane.add(tfFname, 1,3);
        gridPane.add(txtFnameError, 1, 4);

        gridPane.add(txtLname, 0,5);  gridPane.add(tfLname, 1,5);
        gridPane.add(txtLnameError, 1, 6);

        gridPane.add(txtPhoneNumber, 4,3);  gridPane.add(tfPhoneNumber, 5,3);
        gridPane.add(txtPhoneNumberError,5,4);

        gridPane.add(txtEmail, 4,5);  gridPane.add(tfEmail, 5,5);
        gridPane.add(txtEmailError, 5,6);

        gridPane.add(txtYearOfBirth, 0,7);  gridPane.add(tfYearOfBirth, 1,7);
        gridPane.add(txtYearOfBirthError, 1,8);

        gridPane.add(txtSalary, 1,7);  gridPane.add(tfSalary,2,7);
        gridPane.add(txtSalaryError, 2, 8);

        gridPane.add(txtPost, 4,7);  gridPane.add(tfPost, 5,7);

        gridPane.add(new Text("             "), 6,0);
        gridPane.add(btAdd, 7,1);
        gridPane.add(btDelete, 7, 3);
        gridPane.add(btUpdate, 7, 5);
        gridPane.add(btClear, 7, 7);


        GridPane.setHalignment(txtId, HPos.RIGHT);


        /**---------------------------------ColumnSpan-----------------------------------*/
        GridPane.setColumnSpan(tfFname,2);
        GridPane.setColumnSpan(tfLname,2);
        GridPane.setColumnSpan(tfPhoneNumber,2);
        GridPane.setColumnSpan(tfEmail,2);

        /**-----------------------------------Alignment of Texts---------------------------*/
        GridPane.setHalignment(txtId, HPos.RIGHT);
        GridPane.setHalignment(txtFname, HPos.RIGHT);
        GridPane.setHalignment(txtLname, HPos.RIGHT);
        GridPane.setHalignment(txtPhoneNumber, HPos.RIGHT);
        GridPane.setHalignment(txtEmail, HPos.RIGHT);
        GridPane.setHalignment(txtYearOfBirth, HPos.RIGHT);
        GridPane.setHalignment(txtPost, HPos.RIGHT);
        GridPane.setHalignment(txtSalary, HPos.RIGHT); GridPane.setMargin(txtSalary, new Insets(0, -25, 0, 0));
        GridPane.setHalignment(tfSalary,HPos.RIGHT);

        /**---------------------------------Table----------------------------------*/
        table.setEditable(false);



        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));

        TableColumn fnameCol = new TableColumn("First Name");
        fnameCol.setMinWidth(100);
        fnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));

        TableColumn lnameCol = new TableColumn("Last Name");
        lnameCol.setMinWidth(100);
        lnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));

        TableColumn phoneNumberCol = new TableColumn("Phone Number");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));

        TableColumn yobCol = new TableColumn("Year Of Birth");
        yobCol.setMinWidth(100);
        yobCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("yearOfBirth"));

        TableColumn postCol = new TableColumn("Post");
        postCol.setMinWidth(100);
        postCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("post"));

        TableColumn salaryCol = new TableColumn("Salary");
        salaryCol.setMinWidth(100);
        salaryCol.setCellValueFactory(new PropertyValueFactory<Employee, Float>("salary"));






        table.getColumns().addAll(idCol, fnameCol, lnameCol, phoneNumberCol,emailCol, yobCol, postCol, salaryCol);
        table.setMinWidth(802);
        table.setMaxWidth(802);

        table.setRowFactory(tv -> new TableRow<Employee>() {
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



        table.setOnMouseClicked(event ->
        {
            Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null)
            {
                String selectedId = selectedEmployee.getId();
                String selectedFname = selectedEmployee.getFirstName();
                String selectedLname = selectedEmployee.getLastName();
                String selectedPn = selectedEmployee.getPhoneNumber();
                String selectedEmail = selectedEmployee.getEmail();
                int selectedYob = selectedEmployee.getYearOfBirth();
                String selectedPost = selectedEmployee.getPost();
                double selectedSalary = selectedEmployee.getSalary();

                tfId.setText(selectedId);
                tfFname.setText(selectedFname);
                tfLname.setText(selectedLname);
                tfPhoneNumber.setText(selectedPn);
                tfEmail.setText(selectedEmail);
                tfYearOfBirth.setText(String.valueOf(selectedYob));
                tfPost.setText(selectedPost);
                tfSalary.setText(String.valueOf(selectedSalary));
            }
        });


        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setMaxWidth(820);




        getChildren().addAll(gridPane, scrollPane);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(20, 10, 20, 10));
        VBox.setMargin(scrollPane, new Insets(54,0,0,0));







        /**-------------------------Buttons events--------------------------*/
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


        GridPane.setHalignment(txtId, HPos.RIGHT);
    }

    /**---------------------------------------------End of Constructor-------------------------------------------*/

    /**---------------------------------------------------Functions----------------------------------------------*/

    // Méthode exécutée lors du clic sur un bouton. Applique un style de "clic" puis effectue une animation avec une durée de 180ms.
    // Une fois l'animation terminée, rétablit le style initial du bouton.
    private void buttonClickedAnimation(Button bt)
    {
        bt.setStyle(btClickedStyle);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(180), e -> {}));

        animation.setOnFinished(e -> {bt.setStyle(btUnclickedStyle);});

        animation.setCycleCount(1);
        animation.play();

    }
    // Méthode pour appliquer un style différent aux champs de texte en fonction de la sélection.
    // Chaque champ de texte a son style appliqué en fonction de sa sélection ou non.
    private void tfSelectedStyle(TextField tfSelected)
    {
        if(tfSelected == tfId)
            tfId.setStyle(tfSelectedStyle);
        else
            tfId.setStyle(tfUnselectedStyle);

        if(tfSelected == tfFname)
            tfFname.setStyle(tfSelectedStyle);
        else
            tfFname.setStyle(tfUnselectedStyle);

        if(tfSelected == tfLname)
            tfLname.setStyle(tfSelectedStyle);
        else
            tfLname.setStyle(tfUnselectedStyle);

        if(tfSelected == tfPhoneNumber)
            tfPhoneNumber.setStyle(tfSelectedStyle);
        else
            tfPhoneNumber.setStyle(tfUnselectedStyle);

        if(tfSelected == tfEmail)
            tfEmail.setStyle(tfSelectedStyle);
        else
            tfEmail.setStyle(tfUnselectedStyle);

        if(tfSelected == tfYearOfBirth)
            tfYearOfBirth.setStyle(tfSelectedStyle);
        else
            tfYearOfBirth.setStyle(tfUnselectedStyle);

        if(tfSelected == tfPost)
            tfPost.setStyle(tfSelectedStyle);
        else
            tfPost.setStyle(tfUnselectedStyle);

        if(tfSelected == tfSalary)
            tfSalary.setStyle(tfSelectedStyle);
        else
            tfSalary.setStyle(tfUnselectedStyle);
    }

    // Méthode pour réinitialiser les champs de saisie et les erreurs affichées.
    // Tous les champs de texte sont vidés et les erreurs sont masquées.
    private void clear()
    {
        tfFname.setStyle(tfUnselectedStyle);
        tfLname.setStyle(tfUnselectedStyle);
        tfPhoneNumber.setStyle(tfUnselectedStyle);
        tfEmail.setStyle(tfUnselectedStyle);
        tfYearOfBirth.setStyle(tfUnselectedStyle);
        tfPost.setStyle(tfUnselectedStyle);
        tfSalary.setStyle(tfUnselectedStyle);

        tfId.setText("");
        tfFname.setText("");
        tfLname.setText("");
        tfPhoneNumber.setText("");
        tfEmail.setText("");
        tfYearOfBirth.setText("");
        tfPost.setText("");
        tfSalary.setText("");

        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtSalaryError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);
    }
    // Méthode pour ajouter un employé. Vérifie les erreurs dans les champs de saisie et ajoute l'employé si tout est valide.
    private void add()
    {
        getEmployeeData();
        GymData.retrieveAllCustomers();

        String id = tfId.getText();
        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        String post = tfPost.getText();
        int yob = Year.now().getValue();
        float salary = 0;
        Boolean foundError = false;


        try
        {
            yob = Integer.parseInt(tfYearOfBirth.getText());
            if(yob > Year.now().getValue()|| yob<=0)
            {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        }
        catch (NumberFormatException exc)
        {
            String str = tfYearOfBirth.getText();
            if (!str.isEmpty())
            {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }

        }
        try
        {
            salary = Float.parseFloat(tfSalary.getText());
            if(salary < 0)
            {
                foundError = true;
                txtSalaryError.setFill(Color.RED);
            }
        }
        catch (NumberFormatException exc)
        {
            String str = tfSalary.getText();
            if (!str.isEmpty())
            {
                foundError = true;
                txtSalaryError.setFill(Color.RED);
            }
        }


        if(id.equals(""))
        {
            foundError = true;
            txtIdRequiredError.setFill(Color.RED);
        }
        if(GymData.searchIdCustomersAndEmployees(id, gymData))
        {
            foundError = true;
            txtIdExistingError.setFill(Color.RED);
        }
        if(fname.equals(""))
        {
            foundError = true;
            txtFnameError.setFill(Color.RED);
        }

        if(lname.equals(""))
        {
            foundError = true;
            txtLnameError.setFill(Color.RED);
        }

        if(phoneNumber.equals(""))
        {
            foundError = true;
            txtPhoneNumberError.setFill(Color.RED);
        }

        if(!Person.checkEmail(email))
        {
            foundError = true;
            txtEmailError.setFill(Color.RED);
        }


        if(!foundError)
        {
            try
            {
                GymData.addEmployee(fname, lname, phoneNumber, email, yob, post, salary, id);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Employee Page : Add button : " + exc.getMessage());
            }
        }
        getEmployeeData();

    }










    // Méthode pour mettre à jour les informations d'un employé. Vérifie les erreurs dans les champs de saisie et met à jour les informations si tout est valide.
    private void update() {
        String id = tfId.getText();
        String fname = tfFname.getText();
        String lname = tfLname.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        String post = tfPost.getText();
        int yob = Year.now().getValue();
        float salary = 0;
        Boolean foundError = false;


        try
        {
            yob = Integer.parseInt(tfYearOfBirth.getText());
            if(yob > Year.now().getValue()|| yob <=0)
            {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }
        }
        catch (NumberFormatException exc)
        {
            String str = tfYearOfBirth.getText();
            if (!str.isEmpty())
            {
                foundError = true;
                txtYearOfBirthError.setFill(Color.RED);
            }

        }
        try
        {
            salary = Float.parseFloat(tfSalary.getText());
            if(salary < 0)
            {
                foundError = true;
                txtSalaryError.setFill(Color.RED);
            }
        }
        catch (NumberFormatException exc)
        {
            String str = tfSalary.getText();
            if (!str.isEmpty())
            {
                foundError = true;
                txtSalaryError.setFill(Color.RED);
            }
        }



        if (id.equals("")) {
            foundError = true;
            txtIdRequiredError.setFill(Color.RED);
        } else if (!GymData.searchIdEmployees(id, gymData)) {
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

        if(!foundError)
        {
            try
            {
                GymData.updateEmployee(fname, lname, phoneNumber, email, yob, post, salary, id);
                clear();
            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Employee Page : Add button : " + exc.getMessage());
            }
        }
        getEmployeeData();

    }

    // Méthode pour supprimer un employé. Vérifie si l'employé existe et le supprime si aucune erreur n'est trouvée.
    private void delete()
    {
        getEmployeeData();
        txtIdExistingError.setFill(Color.TRANSPARENT);
        txtIdRequiredError.setFill(Color.TRANSPARENT);
        txtIdNotExistingError.setFill(Color.TRANSPARENT);
        txtFnameError.setFill(Color.TRANSPARENT);
        txtLnameError.setFill(Color.TRANSPARENT);
        txtYearOfBirthError.setFill(Color.TRANSPARENT);
        txtPhoneNumberError.setFill(Color.TRANSPARENT);
        txtEmailError.setFill(Color.TRANSPARENT);

        String id = tfId.getText();

        boolean foundError = true;


        for(Employee employee: gymData.getEmployees())
        {
            if(id.equals(employee.getId()))
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
                GymData.deleteEmployee(id);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Employee Page : Add button : " + exc.getMessage());
            }
        }
        getEmployeeData();
    }

    // Méthode pour récupérer les données des employés et les afficher dans un tableau.
    public void getEmployeeData()
    {
        this.gymData.setEmployees(GymData.retrieveAllEmployees());
        ObservableList<Employee> data = FXCollections.observableArrayList(gymData.getEmployees());
        table.setItems(data);

    }
}
