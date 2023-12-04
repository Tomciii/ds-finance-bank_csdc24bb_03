package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBException;
import net.froihofer.util.jboss.trading.*;

import java.io.IOException;

public class SoapClient {

    public SoapClient() {
    }

    public static FindStockQuotesByCompanyNameResponse findStockQuotesByCompanyName(String companyName) throws JAXBException, IOException {
        System.out.println("Yoo, funktioniert 1B");
        System.out.println(SoapRequests.findStockQuotesByCompanyName(companyName));
        return SoapRequests.findStockQuotesByCompanyName(companyName);
    }

    public static BuyResponse buy(String symbol, int shares) throws JAXBException, IOException {
        return SoapRequests.buy(symbol, shares);
    }

    public static SellResponse sell(String symbol, int shares) throws JAXBException, IOException {
        return SoapRequests.sell(symbol, shares);
    }

    public static GetStockQuotesResponse getStockQuotes(String symbol) throws JAXBException, IOException {
        return SoapRequests.getStockQuotes(symbol);
    }

    // Will return Status 401 (Unauthorized) - Wir kommen nicht auf diesen Endpoint
    public static GetStockQuoteHistoryResponse getStockQuoteHistory(String symbol) throws JAXBException, IOException {
        return SoapRequests.getStockQuoteHistory(symbol);
    }
}
