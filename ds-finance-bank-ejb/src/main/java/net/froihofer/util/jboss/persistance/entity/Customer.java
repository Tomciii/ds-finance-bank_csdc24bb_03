package net.froihofer.util.jboss.persistance.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="CUSTOMERNR")
    private int customernr;

    @Column(name="GIVENNAME")
    private String givenname;

    @Column(name="NAME")
    protected String name;

    @Column(name="ADDRESS")
    protected String addresse;

    @Column(name = "BANKDEPOTID")
    private int bankDepotID;

    public Customer(int customernr, String name, String givenname, String addresse, int bankDepotID) {
        this.customernr = customernr;
        this.name = name;
        this.addresse = addresse;
        this.givenname = givenname;
        this.bankDepotID = bankDepotID;
    }

    public Customer() {

    }

    public int getBankDepotID() {
        return bankDepotID;
    }

    public void setBankDepotID(int bankDepotID) {
        this.bankDepotID = bankDepotID;
    }



    public int getCustomerNr() {
        return customernr;
    }




    public void setCustomerNr(int customerNr) {
        this.customernr = customerNr;
    }

    public int getCustomernr() {
        return customernr;
    }

    public void setCustomernr(int customernr) {
        this.customernr = customernr;
    }



    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customernr=" + customernr +
                ", givenname='" + givenname + '\'' +
                ", name='" + name + '\'' +
                ", addresse='" + addresse + '\'' +
                ", bankDepotID=" + bankDepotID +
                '}';
    }
}
