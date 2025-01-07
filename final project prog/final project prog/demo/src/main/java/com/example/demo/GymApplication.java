package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GymApplication extends Application {

    private static Scene scene;
    private static GymData gymData = new GymData();
    private static Scene signInScene;

    public void start(Stage gymStage) {
        try {
            LogInPage loginPagePane = new LogInPage(gymData, gymStage);

            signInScene = new Scene(loginPagePane);
            gymStage.setScene(signInScene);
            gymStage.setFullScreen(true);
            gymStage.show();

            /**----------------------------functionality---------------------------*/


        } catch (Exception exc)
        {
            exc.printStackTrace();
            GymData.catchExceptions(exc.getMessage());
        }
    }

    public static void main(String[] args) {launch(args);}

    // Cette fonction change la scène actuelle de l'application en une nouvelle scène qui contient plusieurs pages
    public static void changeSceneToApp(Stage currentStage, String loginId)
    {
        BorderPane gymPagePane = new BorderPane();

        SettingsPage settingsPage = new SettingsPage();

        EmployeePage employeePagePane = new EmployeePage(gymData);
        CustomerPage customerPagePane = new CustomerPage(gymData);
        AppointmentPage appointmentPage = new AppointmentPage(gymData);
        PasswordPage passwordPage = new PasswordPage(gymData);


        HomePage homePagePane = new HomePage(gymPagePane,customerPagePane, employeePagePane, appointmentPage, passwordPage);
        TopPage topPagePane = new TopPage(gymPagePane, settingsPage, homePagePane, currentStage);
        if(!loginId.equals("1"))
        {

            homePagePane.getBtPasswords().setVisible(false);

        }
        gymPagePane.setLeft(homePagePane);
        gymPagePane.setTop(topPagePane);


        scene = new Scene(gymPagePane);
        currentStage.setScene(scene);
        currentStage.setFullScreen(true);

    }


    public static Scene getSignInScene() {
        return signInScene;
    }



}