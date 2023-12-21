package common.dto;

import java.io.Serializable;

public class StockDTO implements Serializable {
    protected String companyName;
    protected Long floatShares;
    protected String lastTradePrice;
    protected String lastTradeTime;
    protected Long marketCapitalization;
    protected String stockExchange;
    protected String symbol;


    public StockDTO(String companyName, Long floatShares, String lastTradePrice, String lastTradeTime, Long marketCapitalization, String stockExchange, String symbol) {
        this.companyName = companyName;
        this.floatShares = floatShares;
        this.lastTradePrice = lastTradePrice;
        this.lastTradeTime = lastTradeTime;
        this.marketCapitalization = marketCapitalization;
        this.stockExchange = stockExchange;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "companyName='" + companyName + '\'' +
                ", floatShares=" + floatShares +
                ", lastTradePrice=" + lastTradePrice +
                ", lastTradeTime=" + lastTradeTime +
                ", marketCapitalization=" + marketCapitalization +
                ", stockExchange='" + stockExchange + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
