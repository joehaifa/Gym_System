package com.example.demo;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LogInPage extends StackPane {

    private GymData gymData;
    private Stage gymStage;
    private TextField username;
    private TextField password;
    private PasswordField password2;
    private CheckBox check1;
    private Button login;

    public LogInPage(GymData gymData, Stage gymStage)
    {
        this.gymData = gymData;
        this.gymStage = gymStage;




        // Create a background image
        Image bg1 = new Image("file:///C:\\Users\\user\\Desktop\\final project prog\\final project prog\\images\\background.jpg");
        BackgroundImage background = new BackgroundImage(bg1,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        new BackgroundSize(screenWidth, screenHeight, false, false, false, false);

        // Create a GridPane
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(20);

        // Create an image
        Image ig1 = new Image("file:///C:\\Users\\user\\Desktop\\final project prog\\final project prog\\images\\logo.jpg");
        ImageView gym = new ImageView(ig1);
        gym.setFitWidth(137);
        gym.setFitHeight(137);

        // Create a StackPane for rectangle
        StackPane str = new StackPane();
        Rectangle r1 = new Rectangle();
        r1.setWidth(460);
        r1.setHeight(530);
        r1.setStroke(Color.WHITE);
        r1.setFill(Color.WHITE);
        r1.setArcWidth(15);
        r1.setArcHeight(15);
        str.getChildren().addAll(r1);

        // Create a stackPane so the text will be at the center
        Text Welcome = new Text("SIGN IN TO YOUR ACCOUNT");
        Welcome.setFont(Font.font("Open Sans", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Welcome.setFill(Color.BLACK);

        username = new TextField();
        username.setPromptText("Username");
        username.setMinHeight(55);
        username.setMinWidth(350);
        username.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");

        password = new TextField();
        password.setVisible(false);
        password.setPromptText("Password");
        password.setMinHeight(55);
        password.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");

        password2 = new PasswordField();
        password2.setVisible(true);
        password2.setPromptText("Password");
        password2.setMinHeight(55);
        password2.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
        password.textProperty().bindBidirectional(password2.textProperty());

        // Create a borderpane for checkbox
        check1 = new CheckBox("Show Password");
        check1.setPadding(new Insets(0, 0, 0, 5));

        login = new Button("LOGIN");
        login.setMaxSize(500, 250);
        login.setMinHeight(55);
        login.setStyle("-fx-background-radius: 9;-fx-color:#8CDBA9");

        // Make a GridPane
        gp.add(gym, 0, 0);
        gp.add(Welcome, 0, 1);
        gp.add(username, 0, 2);
        gp.add(password, 0, 3);
        gp.add(password2, 0, 3);
        gp.add(check1, 0, 4);
        gp.add(login, 0, 5);
        GridPane.setHalignment(Welcome, HPos.CENTER);
        GridPane.setHalignment(gym, HPos.CENTER);

        login.requestFocus();

        str.getChildren().addAll(gp);
        str.setBackground(new Background(background));

        this.getChildren().add(str);

        login.requestFocus();
        check1.requestFocus();

        login.setOnAction(event -> cantMakePassNull());
        check1.setOnAction(event -> showPass());
        username.setOnMouseClicked(event -> removeWhite0());
        password.setOnMouseClicked(event -> removeWhite1());
        password2.setOnMouseClicked(event -> removeWhite2());
    }
    // Fonction pour vérifier que le mot de passe n'est pas vide avant de se connecter
    public void cantMakePassNull() {
        boolean foundError= false;

        if (password.getText().isEmpty() && password2.getText().isEmpty()) {
            password.setStyle("-fx-border-color: red;-fx-border-radius: 9px;-fx-background-radius: 9;-fx-border-width: 2px;-fx-background-radius: 9;-fx-background-color: lightgrey");
            password2.setStyle("-fx-border-color: red;-fx-border-radius: 9px;-fx-background-radius: 9;-fx-border-width: 2px;-fx-background-radius: 9;-fx-background-color: lightgrey");
            foundError = true;
        }
        else if(!password.getText().isEmpty() && !password2.getText().isEmpty())
        {
            password.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width: 0px;");
            password2.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width: 0px;");
        }
        if(username.getText().isEmpty())
        {
            username.setStyle("-fx-border-color: red;-fx-border-radius: 9px;-fx-background-radius: 9;-fx-border-width: 2px;-fx-background-radius: 9;-fx-background-color: lightgrey;");
            foundError = true;
        }
        else if(!username.getText().isEmpty())
        {
            username.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width: 0px;");
        }


        String loginTrue = "0";
        if(!foundError)
        {
            String username = this.username.getText();
            String pass = gymData.hashPassword(this.password2.getText());
            loginTrue = checkLogin(username, pass);
        }

        if(!loginTrue.equals("0"))
        {
            GymApplication.changeSceneToApp(gymStage, loginTrue);
            username.clear();
            password.clear();
        }

    }
    // Fonction pour enlever les bordures rouges du champ de texte lors du clic
    public void removeWhite0() {
        username.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width: 0px;");
        password.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey;");
        password2.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey;");
    }

    public void removeWhite2() {
        password2.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width: 0px;");
        password.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
        username.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
    }

    public void removeWhite1() {
        password.setStyle("-fx-border-color: white;-fx-border-radius: 0px;-fx-background-radius: 9;-fx-border-width:0px;");
        password2.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
        username.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
    }
    // Fonction pour afficher ou masquer le mot de passe
    public void showPass() {
        if (check1.isSelected()) {
            password.setText(password2.getText());
            password.setVisible(true);
            password2.setVisible(false);
        } else {
            password.setVisible(false);
            password2.setVisible(true);
        }
        username.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
        password.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");
        password2.setStyle("-fx-background-radius: 9;-fx-background-color: lightgrey");

    }

    // Vérification des identifiants de connexion
    public String checkLogin(String username, String pass) {
        gymData.setLogins(GymData.retrieveAllLogIns());


        for (LogIn login : gymData.getLogins()) {
            if (username.equals(login.getUsername()) && pass.equals(login.getPassword())) {
                return login.getId();
            }
        }

        this.username.setStyle("-fx-border-color: red; -fx-border-radius: 9px; -fx-background-color: lightgrey;");
        this.password.setStyle("-fx-border-color: red; -fx-border-radius: 9px; -fx-background-color: lightgrey;");
        this.password2.setStyle("-fx-border-color: red; -fx-border-radius: 9px; -fx-background-color: lightgrey;");
        return "0";
    }


}