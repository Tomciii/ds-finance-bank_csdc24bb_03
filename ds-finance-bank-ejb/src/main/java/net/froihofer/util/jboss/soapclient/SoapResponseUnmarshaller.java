package net.froihofer.util.jboss.soapclient;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class SoapResponseUnmarshaller {

    public static <T> T extract(String soapXmlResponse, Class<T> responseType) throws JAXBException {
        String xmlContent = extractSoapBodyContent(soapXmlResponse);
        return unmarshal(xmlContent, responseType);
    }

    private static String extractSoapBodyContent(String soapXml) {
        int start = soapXml.indexOf("<soap:Body>");
        int end = soapXml.indexOf("</soap:Body>");

        if (start != -1 && end != -1) {
            return soapXml.substring(start + "<soapenv:Body>".length() - 3, end);
        } else {
            return soapXml;
        }
    }

    public static <T> T unmarshal(String xml, Class<T> responseType) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(responseType);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        JAXBElement<T> jaxbElement = jaxbUnmarshaller.unmarshal(new StreamSource(reader), responseType);
        return jaxbElement.getValue();
    }
}
