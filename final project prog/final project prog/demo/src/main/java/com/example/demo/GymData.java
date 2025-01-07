package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



public class GymData {
     static String url ;
     static String username ;
     static String  password ;
    private ArrayList<LogIn> logins;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<Appointment> appointments;

    public GymData()
    {
        this.logins = new ArrayList<LogIn>();
        this.employees = new ArrayList<Employee>();
        this.customers = new ArrayList<Customer>();
        this.appointments = new ArrayList<Appointment>();
        getSettingsInfo();

    }



    public ArrayList<LogIn> getLogins() { return this.logins; }
    public ArrayList<Employee> getEmployees()
    {
        return this.employees;
    }
    public ArrayList<Customer> getCustomers()
    {
        return this.customers;
    }
    public ArrayList<Appointment> getAppointments() { return this.appointments; }


    public void setLogins(ArrayList<LogIn> logins)
    {
        this.logins = logins;
    }
    public void setCustomers(ArrayList<Customer> customers) { this.customers = customers; }
    public void setEmployees(ArrayList<Employee> employees) { this.employees = employees; }
    public void setAppointments(ArrayList<Appointment> appointments) { this.appointments = appointments; }







    // Recherche un client ou un employé par ID dans les listes de clients et d'employés

    public static boolean searchIdCustomersAndEmployees(String id, GymData gymData)
    {
        for (Customer cust : gymData.getCustomers())
            if(cust.getId().equals(id))
                return true;
        for (Employee emp : gymData.getEmployees())
            if(emp.getId().equals(id))
                return true;
        return false;
    }
    // Recherche un client par ID dans la liste des clients
    public static boolean searchIdCustomers(String id, GymData gymData)
    {
        for (Customer cust : gymData.getCustomers())
            if(cust.getId().equals(id))
                return true;
        return false;
    }

    // Recherche un employé par ID dans la liste des employés
    public static boolean searchIdEmployees(String id, GymData gymData)
    {
        for (Employee emp : gymData.getEmployees())
            if(emp.getId().equals(id))
                return true;
        return false;
    }









    // Récupère tous les enregistrements de connexions à partir de la base de données
    public static ArrayList<LogIn> retrieveAllLogIns()
    {   System.out.print(url);
        System.out.println(username);
        System.out.println(password);
        System.out.println("Retrieving logins...");
        ArrayList<LogIn> logins = new ArrayList<LogIn>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM login");

            while (rs.next())
            {
                logins.add(new LogIn(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            System.out.println(logins.get(0));

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during login retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return logins;
    }

    // Ajoute une nouvelle connexion dans la base de données
    public static void addLogIn(String user, String pass)
    {
        System.out.println("Adding login...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO login (login_un, login_pass) VALUES ('" + user + "', '" +  hashPassword(pass) + "')";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during login adding: " + exc.getMessage());
            exc.printStackTrace();
        }
    }

    // Met à jour une connexion existante dans la base de données

    public static void updateLogIn(String id, String user, String pass)
    {
        System.out.println("Updating login...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "UPDATE login SET login_un = '" + user + "', login_pass = '" + hashPassword( pass) + "' WHERE login_id = " + id;
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during login updating: " + exc.getMessage());
            exc.printStackTrace();
        }
    }


    // Supprime une connexion de la base de données
    public static void deleteLogIn(String id)
    {
        System.out.println("Deleting login...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "DELETE FROM login WHERE login_id =" + id;
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during login deleting: " + exc.getMessage());
            exc.printStackTrace();
        }
    }



















    public static ArrayList<Customer> retrieveAllCustomers()
    {
        System.out.println("Retrieving customers...");
        ArrayList<Customer> customers = new ArrayList<Customer>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer");

            while (rs.next())
            {
                customers.add(new Customer(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getDate(7).toLocalDate(),
                        rs.getDate(8).toLocalDate(), rs.getString(1)));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during login retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return customers;
    }


    public static void addCustomer(String fname, String lname, String phoneNumber, String email, int year,
                                   LocalDate startDate, LocalDate endDate, String id)
    {
        System.out.println("Adding customer...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO customer (cust_id, cust_fname, cust_lname, cust_pn, cust_email, cust_yob, cust_start_date, cust_end_date)"
                    + " VALUES ('" +"cust" +id + "', '" + fname + "', '" + lname + "', '" + phoneNumber + "', '" + email + "', " + year
                    + ", '" + Date.valueOf(startDate) + "', '" + Date.valueOf(endDate) + "');";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during customer adding: " + exc.getMessage());
            exc.printStackTrace();
        }
    }

    public static void updateCustomer(String fname, String lname, String phoneNumber, String email, int year,
                                      LocalDate startDate, LocalDate endDate, String id)
    {
        System.out.println("Updating customer...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "UPDATE customer SET cust_fname = '" +  fname +"', cust_lname = '" + lname + "', cust_pn = '" + phoneNumber + "', "
                    + "cust_email = '" + email + "', cust_yob = " + year + ", cust_start_date = '" + Date.valueOf(startDate)
                    + "', cust_end_date = '" + Date.valueOf(endDate) + "' WHERE cust_id = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during customer updating: " + exc.getMessage());
            exc.printStackTrace();
        }
    }



    public static void deleteCustomer(String id)
    {
        System.out.println("Deleting customer...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "DELETE FROM customer WHERE cust_id = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during customer deleting: " + exc.getMessage());
            exc.printStackTrace();
        }
    }




    // Fonction pour récupérer tous les clients depuis la base de données
    public static ArrayList<Employee> retrieveAllEmployees()
    {
        System.out.println("Retrieving employees...");
        ArrayList<Employee> employees = new ArrayList<Employee>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            while (rs.next())
            {
                employees.add(new Employee(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7),
                        rs.getFloat(8), rs.getString(1)));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during login retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return employees;
    }

    // Fonction pour ajouter un client à la base de données
    public static void addEmployee(String fname, String lname, String phoneNumber, String email, int year,
                                   String post, float salary, String id)
    {
        System.out.println("Adding employee...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO employee (emp_id, emp_fname, emp_lname, emp_pn, emp_email, emp_yob, emp_post, emp_salary)"
                    + " VALUES ('" + "emp"+ id + "', '" + fname + "', '" + lname + "', '" + phoneNumber + "', '" + email + "', " + year
                    + ", '" + post + "', " + salary + ");";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during employee adding: " + exc.getMessage());
            exc.printStackTrace();
        }
    }

    // Fonction pour mettre à jour les informations d'un client dans la base de données
    public static void updateEmployee(String fname, String lname, String phoneNumber, String email, int year,
                                      String post, float salary, String id)
    {
        System.out.println("Updating employee...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "UPDATE employee SET emp_fname = '" + fname + "', emp_lname = '" + lname + "', emp_pn = '" + phoneNumber + "', "
                    + "emp_email = '" + email + "', emp_yob = " + year + ", emp_post = '" + post + "', emp_salary = " + salary
                    + " WHERE emp_id = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during employee updating: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    // Fonction pour supprimer un client de la base de données
    public static void deleteEmployee(String id)
    {
        System.out.println("Deleting employee...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "DELETE FROM employee WHERE emp_id = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during employee deleting: " + exc.getMessage());
            exc.printStackTrace();
        }
    }












    // Récupère tous les clients depuis la base de données et les retourne sous forme de liste
    public static ArrayList<Customer> retrieveSimilarCustomer(String fname, String lname)
    {
        System.out.println("Retrieving similar customers...");
        ArrayList<Customer> customers = new ArrayList<Customer>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cust_id, cust_fname, cust_lname FROM customer "
                    + "WHERE cust_fname LIKE '%" + fname + "%' AND cust_lname LIKE '%" + lname + "%';");

            while (rs.next())
            {
                customers.add(new Customer(rs.getString(2), rs.getString(3), rs.getString(1)));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during similar customer retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return customers;
    }







    // Récupère tous les employés similaires à partir de la base de données et les retourne sous forme de liste
    public static ArrayList<Employee> retrieveSimilarEmployee(String fname, String lname)
    {
        System.out.println("Retrieving similar employees...");
        ArrayList<Employee> employees = new ArrayList<Employee>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT emp_id, emp_fname, emp_lname FROM employee "
                    + "WHERE emp_fname LIKE '%" + fname + "%' AND emp_lname LIKE '%" + lname + "%';");

            while (rs.next())
            {
                employees.add(new Employee(rs.getString(2), rs.getString(3), rs.getString(1)));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during similar employee retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return employees;
    }





    // Récupère tous les rendez-vous à partir de la base de données et les retourne sous forme de liste
    public static ArrayList<Appointment> retrieveAllAppointments()
    {
        System.out.println("Retrieving appointments...");
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment");

            while (rs.next())
            {
                appointments.add(new Appointment(rs.getString(2), rs.getString(3),
                        rs.getDate(4).toLocalDate(), rs.getInt(5), rs.getString(1)));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception exc)
        {

            GymData.catchExceptions("Exception during login retrieval: " + exc.getMessage());
            exc.printStackTrace();
        }
        return appointments;
    }
    // Ajoute un rendez-vous à la base de données
    public static void addAppointment(String custId, String empId, LocalDate date, int time)
    {
        System.out.println("Adding appointment...");


        try
        {

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String query = "INSERT INTO appointment (app_cust_id, app_emp_id, app_date, app_time) " +
                    "VALUES ('"+ custId + "', '" + empId + "', '" + Date.valueOf(date) + "', " + time + ")";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        }
        catch(Exception exc)
        {
            GymData.catchExceptions("Exception during employee adding: " + exc.getMessage());
            exc.printStackTrace();
        }


    }
    // Vérifie si un rendez-vous avec un ID spécifique existe dans la base de données
    public static boolean findAppId(String appId) {
        boolean exists = false;

        try {

            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT COUNT(*) FROM appointment WHERE app_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, appId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Check if the count is greater than 0
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception exc) {
            GymData.catchExceptions("Exception during findAppId: " + exc.getMessage());
            exc.printStackTrace();
        }

        return exists;
    }
    // Supprime un rendez-vous basé sur l'ID du rendez-vous
    public static void deleteAppointment(String appId) {
        try {

            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM appointment WHERE app_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, appId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment with ID " + appId + " has been deleted.");
            } else {
                System.out.println("No appointment found with ID " + appId + ".");
            }

            pstmt.close();
            conn.close();
        } catch (Exception exc) {
            GymData.catchExceptions("Exception during deleteAppointment: " + exc.getMessage());
            exc.printStackTrace();
        }
    }











    // Gère l'écriture des exceptions dans un fichier de log
    public static void catchExceptions(String message)
    {
        try(PrintWriter output = new PrintWriter(new File("C:\\Users\\User\\Desktop\\final project prog\\errors\\errors.txt")))
        {
            output.append(message);
        }
        catch (IOException exc)
        {
            System.out.println("An error occurred while writing to the file: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    // Vérifie si un rendez-vous génère un conflit (même date et heure pour le client ou l'employé)
    public static boolean conflictAppointment(String custId, String empId, LocalDate date, int time) {
        ArrayList<Appointment> allAppointments = retrieveAllAppointments();

        for (Appointment appointment : allAppointments) {
            // Check if the customer has another appointment at the same date and time
            if (appointment.getCustId().equals(custId) &&
                    appointment.getDate().equals(date) &&
                    appointment.getTime() == time) {
                return false; // Conflict found for customer
            }

            // Check if the employee has another appointment at the same date and time
            if (appointment.getEmpId().equals(empId) &&
                    appointment.getDate().equals(date) &&
                    appointment.getTime() == time) {
                return false; // Conflict found for employee
            }
        }

        return true; // No conflicts found, can add appointment
    }
    // Hache le mot de passe pour le rendre sécurisé
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    // Récupère les paramètres de connexion à la base de données depuis un fichier
    private void getSettingsInfo() {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\user\\Desktop\\final project prog\\final project prog\\settings\\settings.txt"))) {
            if (scanner.hasNextLine()) {
                 url = scanner.nextLine();
                 username = scanner.hasNextLine() ? scanner.nextLine() : "";
                 password = scanner.hasNextLine() ? scanner.nextLine() : "";


            }

        } catch (IOException e) {
            e.printStackTrace();
            GymData.catchExceptions(e.getMessage());
        }
    }



}
