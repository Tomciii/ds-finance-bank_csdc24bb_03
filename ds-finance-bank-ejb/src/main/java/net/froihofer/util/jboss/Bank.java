package net.froihofer.util.jboss;
import jakarta.xml.bind.JAXBException;
import net.froihofer.util.jboss.soapclient.SoapClient;
import net.froihofer.util.jboss.soapclient.SoapClientProperties;
import net.froihofer.util.jboss.trading.FindStockQuotesByCompanyNameResponse;

import javax.xml.soap.*;
import java.io.IOException;

public class Bank {

    public String name;
    private String password;


    public Bank() {
        name = "csdc24bb_03";
        password = "oF0Queuhae";
        SoapClientProperties.username = name;
        SoapClientProperties.password = password;
        System.out.println("Yo");
    }

    public FindStockQuotesByCompanyNameResponse getFindStockQuotesByCompanyNameResponse() throws JAXBException, IOException {
        System.out.println("Yoo, funktioniert 1A");
        System.out.println(SoapClient.findStockQuotesByCompanyName("Apple"));
        return SoapClient.findStockQuotesByCompanyName("Apple");
    }

}
