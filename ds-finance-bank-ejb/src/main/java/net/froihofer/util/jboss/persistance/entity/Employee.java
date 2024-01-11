package net.froihofer.util.jboss.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @Column(name="MANR")
    private int MANR;

    @Column(name="NAME")
    String name;
    @Column(name="GIVENNAME")
    String givenname;
    @Column(name="ADDRESS")
    String address;
    @Column(name="USERNAME", unique=true)
    String username;
    @Column(name="PASSWORD")
    String password;

    public Employee(){}

    public Employee(int MANR, String name, String givenname, String address, String username, String password) {
        this.MANR = MANR;
        this.name = name;
        this.givenname = givenname;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public int getMANR() {
        return MANR;
    }

    public String getName() {
        return name;
    }

    public String getGivenname() {
        return givenname;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setMANR(int MANR) {
        this.MANR = MANR;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerNr() {
        return MANR;
    }

    public void setCustomerNr(int customerNr) {
        this.MANR = customerNr;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                ", givenname='" + givenname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", MANr='" + MANR + "\'" +
                '}';
    }
}
