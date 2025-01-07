package com.example.demo;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Appointment
{
    private String id;
    private String custId;
    private String empId;
    private LocalDate date;
    private int time;


    public Appointment (String custId, String empId, LocalDate date, int time, String id)
            throws Exception
    {
        this.custId = custId;
        this.empId = empId;
        this.date = date;
        if((time < 0) || (time > 23))
            this.time = 12;
        else
            this.time = time;
        this.id = id;
    }


    /**-----------------------Getters-------------------*/
    public String getId() {
        return this.id;
    }


    public String getCustId() {
        return custId;
    }

    public String getEmpId() {
        return empId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTime() { return this.time; }



    /**-----------------------Setters-------------------*/

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setDate(LocalDate date) throws Exception {
        this.date = date;
    }

    public void setTime( int time)
    {
        if((time < 0) || (time > 23))
            this.time = 12;
        else
            this.time = time;
    }


}
