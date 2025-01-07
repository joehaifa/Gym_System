package com.example.demo;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsPage extends GridPane {
    private static Text txtUrl = new Text("URL:");
    private static TextField tfUrl= new TextField();
    private static Text txtUsername = new Text("Username:");
    private static TextField tfUsername = new TextField();
    private static Text txtPass = new Text("Password:");
    private static TextField tfPass = new TextField ();



    private static Text txtActualUrl = new Text("Actual URL:");
    private static Text txtActualUrlAnswer = new Text();
    private static Text txtActualUsername = new Text("Actual Username:");
    private static Text txtActualUsernameAnswer = new Text();
    private static Text txtActualPass = new Text("Actual Password:");
    private static Text txtActualPassAnswer = new Text();


    private Button btUpdate = new Button("Update");









    private Font ft1 = Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
    private Font ft2 = Font.font("Calibri", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20);
    private String tfUnselectedStyle = "-fx-background-color: #8CDBA9; -fx-background-radius : 5;"
            + " -fx-border-color : black ; -fx-border-width : 2px; -fx-border-radius : 5;";

    private String btUnclickedStyle = "-fx-background-radius: 15px ;"
            + "-fx-background-color: #A9CCE3;"
            + "-fx-border-radius : 15px;"
            + "-fx-border-width : 2px;"
            + "-fx-border-color : darkblue;";

    private String txtActualStyle = "-fx-underline: true; -fx-fill : darkblue;";
    private String txtActualAnswerStyle = "-fx-fill : blue;";







    public SettingsPage()
    {
        setHgap(10);
        setVgap(20);
        setPadding(new Insets(30,30,30,30));
        setAlignment(Pos.CENTER_LEFT);
        getSettingsInfo();




        /**---------------------Adding to the GridPane---------------------*/
        add(txtUrl,0,0); add (tfUrl, 1, 0); add(new Text("                         "), 2, 0);
        add(btUpdate, 3,0);
        add(txtUsername,0,1); add (tfUsername, 1, 1);
        add(txtPass,0,2); add (tfPass, 1, 2);

        add(new Text("   "), 0,3);
        add(new Text("   "), 0,4);
        add(new Text("   "), 0,5);

        add(txtActualUrl,0,6); add (txtActualUrlAnswer, 1, 6);
        add(txtActualUsername,0,7); add (txtActualUsernameAnswer, 1, 7);
        add(txtActualPass,0,8); add (txtActualPassAnswer, 1, 8);






        /**------------------------------Styles & Fonts--------------------------*/

        txtUrl.setFont(ft1);
        txtActualUrl.setFont(ft2);
        txtActualUrlAnswer.setFont(ft1);
        txtActualUrl.setStyle(txtActualStyle);
        txtActualUrlAnswer.setStyle(txtActualAnswerStyle);

        txtUsername.setFont(ft1);
        txtActualUsername.setFont(ft2);
        txtActualUsernameAnswer.setFont(ft1);
        txtActualUsername.setStyle(txtActualStyle);
        txtActualUsernameAnswer.setStyle(txtActualAnswerStyle);

        txtPass.setFont(ft1);
        txtActualPass.setFont(ft2);
        txtActualPassAnswer.setFont(ft1);
        txtActualPass.setStyle(txtActualStyle);
        txtActualPassAnswer.setStyle(txtActualAnswerStyle);

        tfUrl.setStyle(tfUnselectedStyle);
        tfUsername.setStyle(tfUnselectedStyle);
        tfPass.setStyle(tfUnselectedStyle);

        btUpdate.setStyle(btUnclickedStyle);
        btUpdate.setFont(ft2);





        /**------------------------Layout and Size----------------------*/
        tfUrl.setMinWidth(400); tfUrl.setMaxWidth(400);
        tfUsername.setMinWidth(200); tfUsername.setMaxWidth(200);
        tfPass.setMinWidth(200); tfPass.setMaxWidth(200);

        GridPane.setHalignment(txtUrl, HPos.RIGHT);
        GridPane.setHalignment(txtUsername, HPos.RIGHT);
        GridPane.setHalignment(txtPass, HPos.RIGHT);
        GridPane.setHalignment(txtActualUrl, HPos.RIGHT);
        GridPane.setHalignment(txtActualUsername, HPos.RIGHT);
        GridPane.setHalignment(txtActualPass, HPos.RIGHT);

        btUpdate.setOnAction(e -> replaceSettings());

    }



    // Méthode pour lire les paramètres à partir du fichier de configuration et les afficher dans les champs de texte.
    private void getSettingsInfo() {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\user\\Desktop\\final project prog\\final project prog\\settings\\settings.txt"))) {
            if (scanner.hasNextLine()) {
                String url = scanner.nextLine();
                String username = scanner.hasNextLine() ? scanner.nextLine() : "";
                String password = scanner.hasNextLine() ? scanner.nextLine() : "";

                // Set values to the text fields
                tfUrl.setText(url);
                tfUsername.setText(username);
                tfPass.setText(password);

                // Display actual values (you might want to encrypt/secure the actual values in a real application)
                txtActualUrlAnswer.setText(url);
                txtActualUsernameAnswer.setText(username);
                txtActualPassAnswer.setText(password);
            }

        } catch (IOException e) {
            e.printStackTrace();
            GymData.catchExceptions(e.getMessage());
        }
    }


    // Méthode pour mettre à jour les paramètres dans le fichier de configuration avec les nouvelles valeurs.
    private void replaceSettings() {
        try (PrintWriter writer = new PrintWriter("C:\\Users\\User\\Desktop\\final project prog\\settings\\settings.txt")) {
            // Write new values to the file
            writer.println(tfUrl.getText());
            writer.println(tfUsername.getText());
            writer.println(tfPass.getText());

            // Display new values as actual values
            txtActualUrlAnswer.setText(tfUrl.getText());
            txtActualUsernameAnswer.setText(tfUsername.getText());
            txtActualPassAnswer.setText(tfPass.getText());

        } catch (IOException e) {
            e.printStackTrace();
            GymData.catchExceptions(e.getMessage());
        }
    }
}
