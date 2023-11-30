package net.froihofer.util.jboss;
import javax.xml.soap.*;

public class Bank {

    public String name;
    private String password;

    public Bank() {
        name = "csdc24bb_03";
        password = "oF0Queuhae";
    }

    public void getSOAPConnection(){
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Specify SOAP Envelope Namespace
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("web", "https://edu.dedisys.org/ds-finance/ws/TradingService");

            // Create SOAP Body
            SOAPBody soapBody = envelope.getBody();
            SOAPElement soapElement = soapBody.addChildElement("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:trad=\"http://trading.ws.dsfinance.froihofer.net/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <trad:findStockQuotesByCompanyName>\n" +
                    "         <partOfCompanyName>Apple</partOfCompanyName>\n" +
                    "      </trad:findStockQuotesByCompanyName>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>", "web");
            SOAPElement usernameElement = soapElement.addChildElement("Username", "web");
            usernameElement.addTextNode(name);
            SOAPElement passwordElement = soapElement.addChildElement("Password", "web");
            passwordElement.addTextNode(password);

            // Print the SOAP Message
            System.out.println("Request SOAP Message:");
            soapMessage.writeTo(System.out);
            System.out.println();

            // Replace the URL with the actual endpoint URL
            String endpointUrl = "https://edu.dedisys.org/ds-finance/ws/TradingService";
            SOAPMessage response = soapConnection.call(soapMessage, endpointUrl);

            // Process the SOAP Response
            System.out.println("Response SOAP Message:");
            response.writeTo(System.out);
            System.out.println();

            // Close the connection
            soapConnection.close();

        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request");
            e.printStackTrace();
        }
    }

}
