package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;

import java.io.StringReader;

public class ResponseParser {

    public static FindStockQuotesByCompanyNameResponse extract(String soapXmlResponse) throws JAXBException {
            String xmlContent = extractSoapBodyContent(soapXmlResponse);

            return parseXml(xmlContent);
    }

    public static String extractSoapBodyContent(String soapXml) {
        int start = soapXml.indexOf("<soapenv:Body>");
        int end = soapXml.indexOf("</soapenv:Body>");

        if (start != -1 && end != -1) {
            return soapXml.substring(start + "<soapenv:Body>".length(), end);
        } else {
            return soapXml; // Return the original XML if <soapenv:Body> not found
        }
    }

    public static FindStockQuotesByCompanyNameResponse parseXml(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(FindStockQuotesByCompanyNameResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // Use a StringReader to read the XML content
        StringReader reader = new StringReader(xml);

        // Unmarshal the XML into your Java object
        return (FindStockQuotesByCompanyNameResponse) jaxbUnmarshaller.unmarshal(reader);
    }
}
