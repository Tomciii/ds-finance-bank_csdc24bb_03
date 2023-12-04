package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import net.froihofer.dsfinance.ws.trading.FindStockQuotesByCompanyNameResponse;
import net.froihofer.dsfinance.ws.trading.ObjectFactory;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class ResponseParser {

    public static FindStockQuotesByCompanyNameResponse extract(String soapXmlResponse) throws JAXBException {
            String xmlContent = extractSoapBodyContent(soapXmlResponse);

            return parseXml(xmlContent);
    }

    private static String extractSoapBodyContent(String soapXml) {
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
        StringReader reader = new StringReader(xml);
        JAXBElement<FindStockQuotesByCompanyNameResponse> jaxbElement = jaxbUnmarshaller.unmarshal(new StreamSource(reader), FindStockQuotesByCompanyNameResponse.class);
        return jaxbElement.getValue();
    }
}
