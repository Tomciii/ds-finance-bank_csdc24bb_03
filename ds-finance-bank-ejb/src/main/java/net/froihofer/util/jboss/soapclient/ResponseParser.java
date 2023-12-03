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
        int start = soapXml.indexOf("<soap:Body>");
        int end = soapXml.indexOf("</soap:Body>");

        if (start != -1 && end != -1) {
            return soapXml.substring(start + "<soapenv:Body>".length() - 3, end);
        } else {
            return soapXml; // Return the original XML if <soapenv:Body> not found
        }
    }

    public static FindStockQuotesByCompanyNameResponse parseXml(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(FindStockQuotesByCompanyNameResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

     //   xml = xml.replace(" xmlns:ns2=\"http://trading.ws.dsfinance.froihofer.net/\"","");
        StringReader reader = new StringReader(xml);

        // Unmarshal the XML into your Java object
        return (FindStockQuotesByCompanyNameResponse) jaxbUnmarshaller.unmarshal(reader);
    }
}
