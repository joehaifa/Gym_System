package com.example.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

class GymDataTest {

    private GymData gymData;

    @BeforeEach
    void setUp() {

        gymData=new GymData();
        // Mock data for testing
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John", "Doe", "123456789", "john.doe@example.com", 1990, LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1), "002"));
        gymData.setCustomers(customers);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jane", "Smith", "987654321", "jane.smith@example.com", 1985, "Trainer", 2500, "002"));
        gymData.setEmployees(employees);
    }

    @Test
    void testSearchIdCustomersAndEmployees_FoundInCustomers() {
        assertTrue(gymData.searchIdCustomersAndEmployees("cust002", gymData));
    }

    @Test
    void testSearchIdCustomersAndEmployees_FoundInEmployees() {
        assertTrue(gymData.searchIdCustomersAndEmployees("cust002", gymData));
    }

    @Test
    void testSearchIdCustomersAndEmployees_NotFound() {
        assertFalse(gymData.searchIdCustomersAndEmployees("UNKNOWN", gymData));
    }

    @Test
    void testSearchIdCustomers_Found() {
        assertTrue(gymData.searchIdCustomers("cust002", gymData));
    }

    @Test
    void testSearchIdCustomers_NotFound() {
        assertFalse(gymData.searchIdCustomers("UNKNOWN", gymData));
    }

    @Test
    void testSearchIdEmployees_Found() {
        assertTrue(gymData.searchIdEmployees("cust002", gymData));
    }

    @Test
    void testSearchIdEmployees_NotFound() {
        assertFalse(gymData.searchIdEmployees("UNKNOWN", gymData));
    }

    @Test
    public void testAddCustomer() {

        gymData.addCustomer("John", "Doe", "1234567890", "john.doe@example.com", 1985, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 1), "001");

        // Check if the customer is added by retrieving all customers
        ArrayList<Customer> customers = gymData.retrieveAllCustomers();
        boolean customerAdded = customers.stream().anyMatch(c -> c.getId().equals("cust001"));
        assert customerAdded : "Customer was not added successfully";
    }

    @Test
    public void testAddEmployee() {

        gymData.addEmployee("Alice", "Smith", "1234567890", "alice.smith@example.com", 1992, "Manager", 50000, "001");

        // Check if the employee is added by retrieving all employees
        ArrayList<Employee> employees = gymData.retrieveAllEmployees();
        boolean employeeAdded = employees.stream().anyMatch(e -> e.getId().equals("emp001"));
        assert employeeAdded : "Employee was not added successfully";
    }
    @Test
    public void testAddAppointment() {

        gymData.addAppointment("cust001", "emp001", LocalDate.of(2023, 1, 15),12);

        // Verify if the appointment is added
        ArrayList<Appointment> appointments = gymData.getAppointments();
        boolean appointmentAdded = appointments.stream().anyMatch(a -> a.getCustId().equals("cust001"));
        assert appointmentAdded : "Appointment was not added successfully";
    }
    @Test
    public void testDeleteAppointment() {

        gymData.deleteAppointment("cust001");

        // Check if the appointment is deleted
        ArrayList<Appointment> appointments = gymData.getAppointments();
        boolean appointmentDeleted = appointments.stream().noneMatch(a -> a.getCustId().equals("cust001"));
        assert appointmentDeleted : "Appointment was not deleted successfully";
    }


    @Test
    public void testUpdateCustomer() {

        gymData.updateCustomer("Jane", "Doe", "0987654321", "jane.doe@example.com", 1990, LocalDate.of(2023, 2, 1), LocalDate.of(2024, 2, 1), "cust001");

        // Verify if the customer is updated by retrieving the customer info
        ArrayList<Customer> customers = gymData.retrieveAllCustomers();
        Customer updatedCustomer = customers.stream().filter(c -> c.getId().equals("cust001")).findFirst().orElse(null);
        assert updatedCustomer != null && updatedCustomer.getPhoneNumber().equals("0987654321") : "Customer was not updated successfully";
    }

    @Test
    public void testDeleteCustomer() {

        gymData.deleteCustomer("cust001");

        // Check if the customer is deleted
        ArrayList<Customer> customers = gymData.retrieveAllCustomers();
        boolean customerDeleted = customers.stream().noneMatch(c -> c.getId().equals("cust001"));
        assert customerDeleted : "Customer was not deleted successfully";
    }






    @Test
    public void testUpdateEmployee() {

        gymData.updateEmployee("Alice", "Smith", "9876543210", "alice.smith@newdomain.com", 1992, "Senior Manager", 60000, "emp001");

        // Verify if the employee is updated by retrieving the employee info
        ArrayList<Employee> employees = gymData.retrieveAllEmployees();
        Employee updatedEmployee = employees.stream().filter(e -> e.getId().equals("emp001")).findFirst().orElse(null);
        assert updatedEmployee != null && updatedEmployee.getPhoneNumber().equals("9876543210") : "Employee was not updated successfully";
    }

    @Test
    void testRetrieveAllCustomers() {
        ArrayList<Customer> customers = gymData.retrieveAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }

    @Test
    void testRetrieveAllEmployees() {
        ArrayList<Employee> employees = gymData.retrieveAllEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }



    @Test
    void testDeleteEmployee() {
        int initialSize = gymData.getEmployees().size();
        GymData.deleteEmployee("emp001");
        assertEquals(initialSize - 1, gymData.getEmployees().size());
    }

    @Test
    void testAddLogIn() {
        GymData gymData = new GymData();  // Création de l'objet gymData
        int initialSize = gymData.retrieveAllLogIns().size();  // Utilisation de l'objet gymData

        // Ajouter un login
        gymData.addLogIn("user1", "password1");

        // Vérifier après ajout
        int newSize = gymData.retrieveAllLogIns().size();
        assertEquals(initialSize + 1, newSize, "Login should be added to the database");

        // Vérifier si le login ajouté existe bien dans la liste
        boolean exists = gymData.retrieveAllLogIns().stream()
                .anyMatch(login -> login.getUsername().equals("user1"));
        assertTrue(exists, "New login should be present in the database");
    }
    @Test
    void testUpdateLogIn() {
        GymData gymData = new GymData();  // Création de l'objet gymData
        gymData.addLogIn("user2", "password2");
        int initialSize = gymData.retrieveAllLogIns().size();

        // Récupérer l'ID du login ajouté pour la mise à jour
        String loginId = gymData.retrieveAllLogIns().stream()
                .filter(login -> login.getUsername().equals("user2"))
                .findFirst().orElseThrow()
                .getId();

        // Mettre à jour le login
        gymData.updateLogIn(loginId, "updatedUser", "updatedPassword");

        // Vérifier que la mise à jour a bien eu lieu
        boolean exists = gymData.retrieveAllLogIns().stream()
                .anyMatch(login -> login.getUsername().equals("updatedUser"));
        assertTrue(exists, "Updated login should be present in the database");

        // Vérifier que l'ancien login n'existe plus
        boolean oldExists = gymData.retrieveAllLogIns().stream()
                .anyMatch(login -> login.getUsername().equals("user2"));
        assertFalse(oldExists, "Old login should be removed after update");
    }
    @Test
    void testDeleteLogIn() {
        GymData gymData = new GymData();  // Création de l'objet gymData
        gymData.addLogIn("user3", "password3");
        int initialSize = gymData.retrieveAllLogIns().size();

        // Récupérer l'ID du login ajouté pour la suppression
        String loginId = gymData.retrieveAllLogIns().stream()
                .filter(login -> login.getUsername().equals("user3"))
                .findFirst().orElseThrow()
                .getId();

        // Supprimer le login
        gymData.deleteLogIn(loginId);

        // Vérifier que le login a été supprimé
        boolean exists = gymData.retrieveAllLogIns().stream()
                .anyMatch(login -> login.getUsername().equals("user3"));
        assertFalse(exists, "Deleted login should not be present in the database");

        // Vérifier que la taille a diminué
        int newSize = gymData.retrieveAllLogIns().size();
        assertEquals(initialSize - 1, newSize, "Login should be removed from the database");
    }



}
