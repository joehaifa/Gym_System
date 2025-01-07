package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TopPage extends HBox
{
    private Button btBack = new Button();
    private Button btSettings =new Button();
    private int sizeOfButton = 60;


    private BorderPane gymPage;
    private HomePage homePage;









    public TopPage(BorderPane gymPage, GridPane settingsPage, HomePage hPage, Stage stage)
    {
        this.gymPage = gymPage;
        this.homePage = hPage;
        setSpacing(1128);

        /**---------Back Arrow Image Button-------*/
        ImageView backArrowImgView = new ImageView("C:\\Users\\user\\Desktop\\final project prog\\final project prog\\images\\back-arrow.png");
        backArrowImgView.setFitWidth(sizeOfButton);
        backArrowImgView.setFitHeight(sizeOfButton);
        btBack.setGraphic(backArrowImgView);


        /**---------Settings Image Button-------*/
        ImageView settingsImgView = new ImageView("C:\\Users\\user\\Desktop\\final project prog\\final project prog\\images\\settings.png");
        settingsImgView.setFitWidth(sizeOfButton);
        settingsImgView.setFitHeight(sizeOfButton);
        btSettings.setGraphic(settingsImgView);



        getChildren().addAll(btBack, btSettings);
        setStyle("-fx-border-color : #8CDBA9; -fx-border-width : 0 0 10 0px;");









        btSettings.setOnAction(e ->
        {
            gymPage.setCenter(settingsPage);
            homePage.deselectAllButtons();

        });

        btBack.setOnAction(e -> {
            stage.setScene(GymApplication.getSignInScene());
            stage.setFullScreen(true);

        });


    }



}
