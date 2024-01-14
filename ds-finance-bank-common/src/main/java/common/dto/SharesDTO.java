package common.dto;

import java.io.Serializable;

public class SharesDTO implements Serializable {

    private DepotDTO depot;

    private String stockName;

    private double stockShares;

    private double stockValue;

    private String fullName;

    public SharesDTO(DepotDTO depot, String stockName, int stockShares) {
        this.depot = depot;
        this.stockName = stockName;
        this.stockShares = stockShares;
    }

    public SharesDTO(DepotDTO depot, String stockName, int stockShares, double stockValue) {
        this.depot = depot;
        this.stockName = stockName;
        this.stockShares = stockShares;
    }

    public SharesDTO(DepotDTO depot, String stockName, double stockShares, double stockValue, String fullName) {
        this.depot = depot;
        this.stockName = stockName;
        this.stockShares = stockShares;
        this.stockValue = stockValue;
        this.fullName = fullName;
    }

    public SharesDTO() {

    }

    public DepotDTO getDepot() {
        return depot;
    }

    public void setDepot(DepotDTO depot) {
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


    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double depotValue) {
        this.stockValue = depotValue;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    /*@Override
    public String toString() {
        return "SharesDTO{" +
                "stockName='" + stockName + '\'' +
                ", stockShares=" + stockShares +
                '}';
    }*/

    @Override
    public String toString(){
        return System.lineSeparator()+
                "------------------------"+System.lineSeparator()+
                "Full legal Name:"+ fullName +System.lineSeparator()+
                "Stocksymbol: " + stockName +System.lineSeparator()+
                "Amount of Shares: " + stockShares +System.lineSeparator()+
                "Total Value of Shares: "+ stockValue + System.lineSeparator()+
                "------------------------";
    }
}
