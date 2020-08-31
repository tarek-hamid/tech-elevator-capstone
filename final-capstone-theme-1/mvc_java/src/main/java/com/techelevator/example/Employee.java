package com.techelevator.example;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private String position;
    private String office;
    private String startDate;
    private String salary;

    public Employee(){

    }

    public Employee(String firstName, String lastName, String position, String office, String startDate, String salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.office = office;
        this.startDate = startDate;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(office, employee.office) &&
                Objects.equals(startDate, employee.startDate) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position, office, startDate, salary);
    }
}
