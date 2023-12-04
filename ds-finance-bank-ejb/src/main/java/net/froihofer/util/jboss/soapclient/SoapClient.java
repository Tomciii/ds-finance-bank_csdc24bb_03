package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBException;
import net.froihofer.dsfinance.ws.trading.BuyResponse;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;

import java.io.*;

public class SoapClient {
    public static void main(String[] args) throws Exception {

        FindStockQuotesByCompanyNameResponse apple = findStockQuotesByCompanyName("Apple");
    //    BuyResponse apple2 = buy("Apple", 2);
    //    System.out.println(apple2);
    }

    public static FindStockQuotesByCompanyNameResponse findStockQuotesByCompanyName(String input) throws JAXBException, IOException {
        return SoapRequests.findStockQuotesByCompanyName(input);
    }

    public static BuyResponse buy(String symbol, int shares) throws JAXBException, IOException {
        return SoapRequests.buy(symbol, shares);
    }

}
