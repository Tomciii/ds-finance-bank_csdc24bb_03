package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBException;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;

import java.io.*;

public class SoapClient {
    public static void main(String[] args) throws Exception {

        FindStockQuotesByCompanyNameResponse apple = findStockQuotesByCompanyName("Apple");
    }

    public static FindStockQuotesByCompanyNameResponse findStockQuotesByCompanyName(String input) throws JAXBException, IOException {
        return SoapRequests.findStockQuotesByCompanyName(input);
    }


}
