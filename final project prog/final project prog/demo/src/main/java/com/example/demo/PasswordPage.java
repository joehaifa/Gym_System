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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;

public class PasswordPage extends HBox
{
    private GridPane gridPane = new GridPane();
    private TableView<LogIn> table = new TableView<LogIn>();
    private GymData gymData;




    private Text txtId = new Text("ID:");
    private TextField tfId = new TextField();
    private Text txtUsername = new Text("Username:");
    private TextField tfUsername = new TextField();
    private Text txtPassword = new Text("Password:");
    private TextField tfPassword = new TextField();




    private Text txtIdError = new Text("Invalid ID.");
    private Text txtIdOwnerError = new Text("Cannot delete the login of the Owner!");
    private Text txtUsernameRequiredError = new Text("Username is required.");
    private Text txtUsernameExistingError = new Text("Username already exists.");
    private Text txtPasswordError = new Text("Password is required.");





    private Button btAdd = new Button("Add");
    private Button btDelete = new Button("Delete");
    private Button btUpdate = new Button("Update");
    private Button btClear = new Button("Clear");





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

    private Font ft1 = Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
    private Font ft2 = Font.font("Calibri", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20);
    private Font ft3 = Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 15);





    public PasswordPage(GymData gymData)
    {
        this.gymData = gymData;
        getPasswordData();
        setSpacing(40);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(30, 30, 30, 30));















        /**----------------Text Edits-----------------*/
        txtId.setFont(ft1);
        txtUsername.setFont(ft1);
        txtPassword.setFont(ft1);



        /**------------TextField Edits---------------*/
        tfId.setStyle(tfUnselectedStyle);
        tfUsername.setStyle(tfUnselectedStyle);
        tfPassword.setStyle(tfUnselectedStyle);



        /**--------------TextError Edits-------------*/
        txtIdError.setFont(ft3);
        txtIdOwnerError.setFont(ft3);
        txtUsernameRequiredError.setFont(ft3);
        txtUsernameExistingError.setFont(ft3);
        txtPasswordError.setFont(ft3);

        txtIdError.setFill(Color.TRANSPARENT);
        txtIdOwnerError.setFill(Color.TRANSPARENT);
        txtUsernameRequiredError.setFill(Color.TRANSPARENT);
        txtUsernameExistingError.setFill(Color.TRANSPARENT);
        txtPasswordError.setFill(Color.TRANSPARENT);



        /**---------TextField selected effect--------*/
        tfId.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfId);
                txtIdError.setFill(Color.TRANSPARENT);
                txtIdOwnerError.setFill(Color.TRANSPARENT);
            }
        });
        tfUsername.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfUsername);
                txtUsernameRequiredError.setFill(Color.TRANSPARENT);
                txtUsernameExistingError.setFill(Color.TRANSPARENT);
            }
        });

        tfPassword.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal) {
                tfSelectedStyle(tfPassword);
                txtPasswordError.setFill(Color.TRANSPARENT);
            }
        });











        /**--------------Buttons--------------*/
        btAdd.setPrefWidth(100);
        btDelete.setPrefWidth(100);
        btUpdate.setPrefWidth(100);
        btClear.setPrefWidth(100);

        btAdd.setFont(ft2);
        btDelete.setFont(ft2);
        btUpdate.setFont(ft2);
        btClear.setFont(ft2);

        btAdd.setStyle(btUnclickedStyle);
        btUpdate.setStyle(btUnclickedStyle);
        btDelete.setStyle(btUnclickedStyle);
        btClear.setStyle(btUnclickedStyle);



        GridPane.setMargin(btAdd, new Insets(5, 5, 5, 5));
        GridPane.setMargin(btDelete, new Insets(5, 5, 5, 5));
        GridPane.setMargin(btUpdate, new Insets(5, 5, 5, 5));
        GridPane.setMargin(btClear, new Insets(5, 5, 5, 5));


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






        /**--------Adding everything to the gridpane--------*/
        gridPane.add(txtId,0,1);
        gridPane.add(tfId,1,1);
        gridPane.add(txtIdError, 1,2);
        gridPane.add(txtIdOwnerError, 0, 0);

        gridPane.add(txtUsername, 0, 3);
        gridPane.add(tfUsername,1,3);
        gridPane.add(txtUsernameRequiredError,1,4);
        gridPane.add(txtUsernameExistingError,0, 4);

        gridPane.add(txtPassword, 0, 5);
        gridPane.add(tfPassword,1,5);
        gridPane.add(txtPasswordError,1,6);

        gridPane.add(btAdd, 0,7);
        gridPane.add(btUpdate,1,7);
        gridPane.add(btDelete,0,8);
        gridPane.add(btClear, 1,8);



        /**---------------Alignment of every node---------*/
        GridPane.setHalignment(txtId, HPos.RIGHT);
        GridPane.setHalignment(txtUsername, HPos.RIGHT);
        GridPane.setHalignment(txtPassword, HPos.RIGHT);

        GridPane.setHalignment(txtIdError, HPos.LEFT);
        GridPane.setHalignment(txtIdOwnerError, HPos.RIGHT);
        GridPane.setHalignment(txtUsernameRequiredError, HPos.LEFT);
        GridPane.setHalignment(txtUsernameExistingError, HPos.RIGHT);
        GridPane.setHalignment(txtPasswordError, HPos.LEFT);

        GridPane.setValignment(txtIdError, VPos.TOP);
        GridPane.setValignment(txtIdOwnerError, VPos.TOP);
        GridPane.setValignment(txtUsernameRequiredError, VPos.TOP);
        GridPane.setValignment(txtUsernameExistingError, VPos.TOP);
        GridPane.setValignment(txtPasswordError, VPos.TOP);

        GridPane.setMargin(txtIdError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtIdOwnerError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtUsernameRequiredError, new Insets(-10, 0, 0, 0));
        GridPane.setMargin(txtUsernameExistingError, new Insets(-10,0,0,0));
        GridPane.setMargin(txtPasswordError, new Insets(-10, 0, 0, 0));


        GridPane.setColumnSpan(txtIdOwnerError,2);


        gridPane.setHgap(5);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);













        /**---------------------------------Table----------------------------------*/
        table.setEditable(false);



        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<LogIn, String>("id"));

        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(new PropertyValueFactory<LogIn, String>("username"));

        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.setMinWidth(100);
        passwordCol.setCellValueFactory(new PropertyValueFactory<LogIn, String>("password"));

        table.getColumns().addAll(idCol, usernameCol, passwordCol);
        table.setMinWidth(302);
        table.setMaxWidth(302);

        table.setRowFactory(tv -> new TableRow<LogIn>() {
            @Override
            protected void updateItem(LogIn item, boolean empty) {
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
            LogIn selectedLogin = table.getSelectionModel().getSelectedItem();
            if (selectedLogin != null)
            {
                String selectedId = selectedLogin.getId();
                String selectedUsername = selectedLogin.getUsername();
                String selectedPass = selectedLogin.getPassword();

                tfId.setText(selectedId);
                tfUsername.setText(selectedUsername);
                tfPassword.setText(selectedPass);
            }
        });

        /*
        ImageView img = new ImageView("C:\\Users\\User\\Desktop\\final project prog\\images\\lock.png");
        img.setFitWidth(200);
        img.setFitHeight(400);

         */

        getChildren().addAll(gridPane, table);
    }

    // Méthode pour récupérer les données de connexion et afficher les informations dans le tableau
    public void getPasswordData()
    {
        this.gymData.setLogins(GymData.retrieveAllLogIns());
        ObservableList<LogIn> data = FXCollections.observableArrayList(gymData.getLogins());
        table.setItems(data);

    }





    // Méthode pour appliquer un style particulier aux champs de texte lorsqu'ils sont sélectionnés
    private void tfSelectedStyle(TextField tfSelected) {
        if (tfSelected == tfId)
            tfId.setStyle(tfSelectedStyle);
        else
            tfId.setStyle(tfUnselectedStyle);

        if (tfSelected == tfUsername)
            tfUsername.setStyle(tfSelectedStyle);
        else
            tfUsername.setStyle(tfUnselectedStyle);

        if (tfSelected == tfPassword)
            tfPassword.setStyle(tfSelectedStyle);
        else
            tfPassword.setStyle(tfUnselectedStyle);
    }


    // Méthode pour appliquer une animation de changement de style lorsqu'un bouton est cliqué
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





    // Méthode pour ajouter une nouvelle connexion
    private void add()
    {
        getPasswordData();
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        txtIdError.setFill(Color.TRANSPARENT);
        txtIdOwnerError.setFill(Color.TRANSPARENT);
        Boolean foundError = false;



        if (username.equals(""))
        {
            foundError = true;
            txtUsernameRequiredError.setFill(Color.RED);
        }

        if (password.equals("")) {
            foundError = true;
            txtPasswordError.setFill(Color.RED);
        }

        for(LogIn login: gymData.getLogins())
        {
            if(username.equals(login.getUsername()))
            {
                foundError = true;
                txtUsernameExistingError.setFill(Color.RED);
            }
        }

        if (!foundError)
        {
            try
            {
                GymData.addLogIn(username, password);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Password Page : Add button : " + exc.getMessage());
            }
        }
        getPasswordData();

    }



    // Méthode pour mettre à jour une connexion existante
    private void update ()
    {
        getPasswordData();
        String id = tfId.getText();
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        boolean foundError = true;

        for(LogIn login: gymData.getLogins())
        {
            if(id.equals(login.getId()))
            {
                foundError = false;
            }
        }
        if(foundError)
        {
            txtIdError.setFill(Color.RED);
        }

        for(LogIn login: gymData.getLogins())
        {
            if(username.equals(login.getUsername()))
            {
                if(!id.equals(login.getId()))
                {
                    foundError = true;
                    txtUsernameRequiredError.setFill(Color.RED);
                }
            }
        }

        if(id.equals(""))
        {
            foundError = true;
            txtIdError.setFill(Color.RED);
        }

        if (username.equals(""))
        {
            foundError = true;
            txtUsernameRequiredError.setFill(Color.RED);
        }

        if (password.equals("")) {
            foundError = true;
            txtPasswordError.setFill(Color.RED);
        }


        if(!foundError)
        {
            try
            {
                GymData.updateLogIn(id, username, password);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Password Page : Add button : " + exc.getMessage());
            }
        }
        getPasswordData();
    }





    // Méthode pour supprimer une connexion
    private void delete()
    {
        txtUsernameRequiredError.setFill(Color.TRANSPARENT);
        txtPasswordError.setFill(Color.TRANSPARENT);
        getPasswordData();
        String id = tfId.getText();

        boolean foundError = true;


        for(LogIn login: gymData.getLogins())
        {
            if(id.equals(login.getId()))
            {
                foundError = false;
            }
        }

        if(foundError)
        {
            txtIdError.setFill(Color.RED);
        }

        if(id.equals(""))
        {
            foundError = true;
            txtIdError.setFill(Color.RED);
        }

        if(id.equals("1"))
        {
            foundError = true;
            txtIdOwnerError.setFill(Color.RED);
        }




        if(!foundError)
        {
            try
            {
                GymData.deleteLogIn(id);
                clear();

            } catch (Exception exc) {
                GymData.catchExceptions(LocalDateTime.now() + "   Password Page : Add button : " + exc.getMessage());
            }
        }
        getPasswordData();
    }
    // Méthode pour réinitialiser les champs et masquer les erreurs
    private void clear()
    {
        tfId.setText("");
        tfUsername.setText("");
        tfPassword.setText("");

        txtIdError.setFill(Color.TRANSPARENT);
        txtUsernameRequiredError.setFill(Color.TRANSPARENT);
        txtPasswordError.setFill(Color.TRANSPARENT);
    }




}
