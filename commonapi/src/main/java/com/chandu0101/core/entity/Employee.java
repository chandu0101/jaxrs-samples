package com.chandu0101.core.entity;

/**
 * Created by chandrasekharkode on 5/20/14.
 */
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!getId().equals(employee.getId())) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
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


}
