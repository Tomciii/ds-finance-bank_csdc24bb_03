package net.froihofer.util.jboss.soapclient;

public class Requests {

    public static String findStockQuotesByCompanyName(String input) {
        return "<soapenv:Envelope xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\" \" +\n" +
                "                \"xmlns:trad=\\\"http://trading.ws.dsfinance.froihofer.net/\\\">\\n\" +\n" +
                "                \"   <soapenv:Header/>\\n\" +\n" +
                "                \"   <soapenv:Body>\\n\" +\n" +
                "                \"      <trad:findStockQuotesByCompanyName>\\n\" +\n" +
                "                \"         <partOfCompanyName>" + input + "</partOfCompanyName>\\n\" +\n" +
                "                \"      </trad:findStockQuotesByCompanyName>\\n\" +\n" +
                "                \"   </soapenv:Body>\\n\" +\n" +
                "                \"</soapenv:Envelope>\";";
    }
}
