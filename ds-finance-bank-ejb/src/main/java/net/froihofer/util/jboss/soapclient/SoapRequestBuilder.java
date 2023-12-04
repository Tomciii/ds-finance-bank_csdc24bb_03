package net.froihofer.util.jboss.soapclient;

public class SoapRequestBuilder {

    public static String findStockQuotesByCompanyName(String input) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <trad:findStockQuotesByCompanyName>\n" +
                "         <partOfCompanyName>" + input + "</partOfCompanyName>\n" +
                "      </trad:findStockQuotesByCompanyName>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String buy(String symbol, int shares) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <trad:buy>\n" +
                "         <symbol>" + symbol + "</symbol>\n" +
                "         <shares>" + shares + "</shares>\n" +
                "      </trad:buy>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String sell(String symbol, int shares) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <trad:sell>\n" +
                "         <symbol>" + symbol + "</symbol>\n" +
                "         <shares>" + shares + "</shares>\n" +
                "      </trad:sell>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String getStockQuotes(String symbol) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <trad:getStockQuotes>\n" +
                "         <symbols>" + symbol + "</symbols>\n" +
                "      </trad:getStockQuotes>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String getStockQuoteHistory(String symbol) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <trad:getStockQuoteHistory>\n" +
                "         <symbol>" + symbol + "</symbol>\n" +
                "      </trad:getStockQuoteHistory>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
