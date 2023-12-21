package common.dto;

import java.io.Serializable;

public class SharesDTO implements Serializable {

    private DepotDTO depot;

    private String stockName;

    private double stockShares;

    public SharesDTO(DepotDTO depot, String stockName, int stockShares) {
        this.depot = depot;
        this.stockName = stockName;
        this.stockShares = stockShares;
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

    @Override
    public String toString() {
        return "SharesDTO{" +
                "stockName='" + stockName + '\'' +
                ", stockShares=" + stockShares +
                '}';
    }
}
