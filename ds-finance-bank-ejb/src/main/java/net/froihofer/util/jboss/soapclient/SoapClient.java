package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBException;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;

import java.io.*;
import java.net.HttpURLConnection;

public class SoapClient {
    public static void main(String[] args) throws Exception {

        FindStockQuotesByCompanyNameResponse apple = findStockQuotesByCompanyName("Apple");

    }

    public static FindStockQuotesByCompanyNameResponse findStockQuotesByCompanyName(String input) throws IOException, JAXBException {
        String soapRequest = Requests.findStockQuotesByCompanyName(input);

        HttpURLConnection connection = SoapClientProperties.getHttpURLConnection();

        sendRequest(soapRequest, connection);

        try (InputStream is = connection.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder responseContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            System.out.println("Response Message: " + responseContent);

            return ResponseParser.extract(responseContent.toString());
        } finally {
            connection.disconnect();
        }
    }

    private static void sendRequest(String soapRequest, HttpURLConnection connection) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] soapBytes = soapRequest.getBytes();
            os.write(soapBytes, 0, soapBytes.length);
        }
    }
}
