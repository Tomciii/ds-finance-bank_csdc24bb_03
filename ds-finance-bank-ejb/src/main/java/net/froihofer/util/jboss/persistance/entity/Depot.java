package net.froihofer.util.jboss.persistance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="DEPOT")
public class Depot implements Serializable {
    @Column(name ="ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="CUSTOMERNR")
    private int customernr;

    @OneToMany(mappedBy = "depot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shares> shares = new ArrayList<>();


    public List<Shares> getShares() {
        return shares;
    }

    public Depot(int id, List<Shares> stocks) {
        this.id = id;
        this.shares = stocks;
    }

    public Depot(int id, int customerNr, List<Shares> stocks) {
        this.id = id;
        this.customernr = customerNr;
        this.shares = stocks;
    }

    public Depot() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setShares(List<Shares> stocks) {
        this.shares = stocks;
    }

    public int getCustomernr() {
        return customernr;
    }

    public void setCustomernr(int customerNr) {
        this.customernr = customerNr;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
                ", customernr=" + customernr +
                ", shares=" + shares +
                '}';
    }
}
