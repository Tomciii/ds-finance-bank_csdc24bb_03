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

    String name;
    String givenname;
    String address;
    String username;
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
