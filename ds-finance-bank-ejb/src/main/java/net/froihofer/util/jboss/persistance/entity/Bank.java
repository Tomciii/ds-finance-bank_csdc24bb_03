package net.froihofer.util.jboss.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="BANK")
public class Bank implements Serializable {
    @Id
    private int id;

    @Column(name="BANKNAME")
    private String bankName;

    @Column(name="INVESTABLEVOLUME")
    private Double investableVolume;

    public Bank(int id, String bankName, Double investableVolume) {
        this.id = id;
        this.bankName = bankName;
        this.investableVolume = investableVolume;
    }

    public Bank() {

    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getInvestableVolume() {
        return investableVolume;
    }

    public void setInvestableVolume(Double investableVolume) {
        this.investableVolume = investableVolume;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", investableVolume='" + investableVolume + '\'' +
                '}';
    }
}
