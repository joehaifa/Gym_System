package com.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class HomePage extends VBox
{
    private BorderPane gymPage;
    private CustomerPage customerPage;
    private EmployeePage employeePage;
    private AppointmentPage appointmentPage;
    private PasswordPage passwordPage;



    private Button btCustomer = new Button("Customers");
    private Button btEmployee = new Button("Employees");
    private Button btAppointment = new Button("Appointments");
    private Button btPasswords = new Button("Passwords");



    private Font ft1 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 25);
    private String unselectedStyle = "-fx-background-radius: 20px ; "
            + "-fx-text-fill : black;"
            + "-fx-border-radius : 20px;"
            + "-fx-border-width: 5px;"
            + "-fx-border-color: #8CDBA9;";
    private String selectedStyle = "-fx-background-radius: 20px ;"
            + "-fx-text-fill : black;"
            + "-fx-background-color: #8CDBA9;"
            + "-fx-border-radius : 20px;"
            + "-fx-border-width: 5px;"
            + "-fx-border-color: #33AB5F;";






    public HomePage(BorderPane gymPage,CustomerPage customerPage, EmployeePage employeePage,
                    AppointmentPage appointmentPage, PasswordPage passwordPage)
    {
        super(30);


        this.gymPage = gymPage;
        this.customerPage = customerPage;
        this.employeePage = employeePage;
        this.appointmentPage = appointmentPage;
        this.passwordPage = passwordPage;

        setPadding(new Insets(20,20,20,20));
        setStyle("-fx-border-color : #8CDBA9; -fx-border-width : 0 10 0 0px;");

        /**------Buttons Fonts-------*/
        btCustomer.setFont(ft1);
        btEmployee.setFont(ft1);
        btPasswords.setFont(ft1);
        btAppointment.setFont(ft1);

        /**------Buttons Widths------*/
        btCustomer.setPrefWidth(200);
        btEmployee.setPrefWidth(200);
        btPasswords.setPrefWidth(200);
        btAppointment.setPrefWidth(200);

        /**-----Buttons Style---------*/
        btCustomer.setStyle(unselectedStyle);
        btEmployee.setStyle(unselectedStyle);
        btPasswords.setStyle(unselectedStyle);
        btAppointment.setStyle(unselectedStyle);



        /**----Selected Effect---------*/
        btCustomer.setOnAction(e ->
        {
            btCustomer.setStyle(selectedStyle);
            btEmployee.setStyle(unselectedStyle);
            btPasswords.setStyle(unselectedStyle);
            btAppointment.setStyle(unselectedStyle);
            gymPage.setCenter(this.customerPage);
        });


        btEmployee.setOnAction(e ->
        {
            btEmployee.setStyle(selectedStyle);
            btCustomer.setStyle(unselectedStyle);
            btPasswords.setStyle(unselectedStyle);
            btAppointment.setStyle(unselectedStyle);
            gymPage.setCenter(this.employeePage);
        });


        btAppointment.setOnAction(e ->
        {
            btAppointment.setStyle(selectedStyle);
            btCustomer.setStyle(unselectedStyle);
            btEmployee.setStyle(unselectedStyle);
            btPasswords.setStyle(unselectedStyle);
            gymPage.setCenter(this.appointmentPage);
        });


        btPasswords.setOnAction(e ->
        {
            btPasswords.setStyle(selectedStyle);
            btCustomer.setStyle(unselectedStyle);
            btEmployee.setStyle(unselectedStyle);
            btAppointment.setStyle(unselectedStyle);
            gymPage.setCenter(this.passwordPage);
            passwordPage.getPasswordData();
        });


        getChildren().addAll(btCustomer, btEmployee, btAppointment, btPasswords);
        setAlignment(Pos.CENTER);
    }



    public Button getBtPasswords(){return this.btPasswords;}

    public Button getBtCustomer(){return this.btCustomer;}
    public Button getBtEmployee(){return this.btEmployee;}
    public Button getBtAppointment(){return this.btAppointment;}

    public void deselectAllButtons()
    {
        btCustomer.setStyle(unselectedStyle);
        btEmployee.setStyle(unselectedStyle);
        btPasswords.setStyle(unselectedStyle);
        btAppointment.setStyle(unselectedStyle);
    }

}

