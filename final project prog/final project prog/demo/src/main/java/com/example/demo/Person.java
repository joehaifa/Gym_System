package com.example.demo;

import java.time.Year;

public class Person
{

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int yearOfBirth;


    public Person(String fname, String lname, String phoneNumber, String email, int year, String id)
            throws IllegalArgumentException
    {
        boolean foundError = false;

        if( id == null)
        {
            foundError = true;
            throw new IllegalArgumentException("Id is empty.");
        }
        if(fname == null)
        {
            foundError = true;
            throw new IllegalArgumentException("First name is empty.");
        }
        if(lname == null)
        {
            foundError = true;
            throw new IllegalArgumentException("Last name is empty.");
        }
        if (email == null)
        {
            foundError = true;
            throw new IllegalArgumentException("Email is empty.");
        }
        if (!checkEmail(email))
        {
            foundError = true;
            throw new IllegalArgumentException("Email has invalid format.");
        }
        if (!checkYear(year))
        {
            foundError = true;
            throw new IllegalArgumentException("Year of birth is in the future.");
        }



        if(!foundError)
        {
            this.firstName = fname;
            this.lastName = lname;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.yearOfBirth = year;
            this.id = id;
        }


    }


    public Person(String fname, String lname, String id)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.id = id;
    }






    /**----------------getters and setter-------------------*/
    public String getId() {
        return id;
    }
    public void setId(String id) throws IllegalArgumentException
    {
        if (id != null)
            this.id = id;
        else
            throw new IllegalArgumentException("Id is empty.");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fname) throws IllegalArgumentException{
        if(fname == null)
            throw new IllegalArgumentException("First name is empty.");
        else
            this.firstName = fname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lname) throws IllegalArgumentException{
        if (lname == null)
            throw new IllegalArgumentException("Last name is empty.");
        else
            this.lastName = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException{
        if (email == null)
            throw new IllegalArgumentException("Email is empty");
        else if (!checkEmail(email))
            throw new IllegalArgumentException("Email has invalid format.");
        else
            this.email = email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int year) throws IllegalArgumentException{
        if (!checkYear(year))
            throw new IllegalArgumentException("Year of birth in in the future.");
        else
            this.yearOfBirth = year;
    }



    public static boolean checkEmail(String email)
    {
        int atCounter = 0;
        int dotCounter = 0;
        String c;
        for (int i = 0; i <= email.length() - 1; i++)
        {
            c = email.substring(i,i+1);
            if(c.equals("@"))
                atCounter++;
            else if(c.equals("."))
                dotCounter++;
        }

        if(atCounter == 1 && dotCounter >= 1)
            return true;
        else
            return false;
    }

    private boolean checkYear(int year)
    {
        Year currentYear = Year.now();
        if(year <= currentYear.getValue())
            return true;
        else
            return false;
    }
}
