package net.froihofer.util.jboss.persistance.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shares")
public class Shares implements Serializable {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "depot_id", referencedColumnName = "id")
    private Depot depot;

    @Column(name="STOCKNAME")
    private String stockName;

    @Column(name="STOCKSHARES")
    private double stockShares;

    public Shares( Depot depot, String stockName, double stockShares) {

        this.depot = depot;
        this.stockName = stockName;
        this.stockShares = stockShares;
    }

    public Shares() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getStockShares() {
        return stockShares;
    }

    public void setStockShares(double stockShares) {
        this.stockShares = stockShares;
    }

    @Override
    public String toString() {
        return "Shares{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", stockShares=" + stockShares +
                '}';
    }
}
