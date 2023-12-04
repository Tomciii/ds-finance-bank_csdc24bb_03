package net.froihofer.util.jboss.soapclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class SoapClientProperties {

    private static final String username = "";
    private static final String password = "";

    private static String getBasicAuthHeader() {
        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        return "Basic " + encodedCredentials;
    }

    public static HttpURLConnection getHttpURLConnection() throws IOException {
        URL url = new URL("https://edu.dedisys.org/ds-finance/ws/TradingService");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("Authorization", getBasicAuthHeader());
        connection.setDoOutput(true);
        return connection;
    }
}
