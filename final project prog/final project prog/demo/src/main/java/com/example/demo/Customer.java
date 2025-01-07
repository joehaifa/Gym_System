package com.example.demo;

import java.time.LocalDate;

public class Customer extends Person {
    private LocalDate startDate;
    private LocalDate endDate;


    public Customer(String fname, String lname, String phoneNumber, String email, int year,
                    LocalDate startDate, LocalDate endDate, String id) throws IllegalArgumentException
    {
        super(fname, lname, phoneNumber, email, year, id);

        if (startDate.compareTo(endDate) > 0)
            throw new IllegalArgumentException("Start date is greater than end date.");
        else
        {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }


    public Customer(String fname, String lname, String id)
    {
        super(fname, lname, id);
    }


    public String getId()
    {
        return super.getId();
    }

    public void setId(String id) throws IllegalArgumentException {super.setId(id);}



    public String getFirstName()
    {
        return super.getFirstName();
    }
    public void setFirstName(String fname) throws IllegalArgumentException
    {
        super.setFirstName(fname);
    }




    public String getLastName()
    {
        return super.getLastName();
    }
    public void setLastName(String lname) throws IllegalArgumentException
    {
        super.setLastName(lname);
    }




    public String getPhoneNumber()
    {
        return super.getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber)
    {
        super.setPhoneNumber(phoneNumber);
    }

    public String getEmail()
    {
        return super.getEmail();
    }
    public void setEmail(String email) throws IllegalArgumentException
    {
        super.setEmail(email);
    }



    public LocalDate getStartDate()
    {
        return this.startDate;
    }
    public void setStartDate(LocalDate startDate)
    {
        if (startDate.compareTo(this.endDate) > 0)
            throw new IllegalArgumentException("Start date is greater than end date.");
    }


    public LocalDate getEndDate()
    {
        return this.endDate;
    }
    public void setEndDate(LocalDate endDate)
    {
        if(this.startDate.compareTo(endDate) > 0)
            throw new IllegalArgumentException("start date is greater than end date.");
    }







    public String toString()
    {
        String str = "\nID : " + super.getId()
                + "\nFirst name : " + super.getFirstName()
                + "\nLast name : " + super.getLastName()
                + "\nPhone number : " + super.getPhoneNumber()
                + "\nEmail : " + super.getEmail()
                + "\nStart date : " + this.startDate
                + "\nEnd date : " + this.endDate;
        return str;
    }


}
