package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBException;
import net.froihofer.dsfinance.ws.trading.BuyResponse;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;

import java.io.*;
import java.net.HttpURLConnection;

public class SoapRequests {
    public static FindStockQuotesByCompanyNameResponse findStockQuotesByCompanyName(String input) throws IOException, JAXBException {
        String soapRequest = SoapRequestBuilder.findStockQuotesByCompanyName(input);
        HttpURLConnection connection = SoapClientProperties.getHttpURLConnection();

        sendRequest(soapRequest, connection);

        try (InputStream is = connection.getInputStream()) {
            StringBuilder responseContent = getResponse(is);

            System.out.println("Response Message: " + responseContent);

            return SoapResponseUnmarshaller.extract(responseContent.toString(), FindStockQuotesByCompanyNameResponse.class);
        } finally {
            connection.disconnect();
        }
    }

    public static BuyResponse buy(String symbol, int shares) throws IOException, JAXBException {
        String soapRequest = SoapRequestBuilder.buy(symbol, shares);
        HttpURLConnection connection = SoapClientProperties.getHttpURLConnection();

        sendRequest(soapRequest, connection);

        try (InputStream is = connection.getInputStream()) {
            StringBuilder responseContent = getResponse(is);

            System.out.println("Response Message: " + responseContent);

            return SoapResponseUnmarshaller.extract(responseContent.toString(), BuyResponse.class);
        } finally {
            connection.disconnect();
        }
    }

    private static StringBuilder getResponse(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder responseContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        return responseContent;
    }

    private static void sendRequest(String soapRequest, HttpURLConnection connection) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] soapBytes = soapRequest.getBytes();
            os.write(soapBytes, 0, soapBytes.length);
        }
    }
}
