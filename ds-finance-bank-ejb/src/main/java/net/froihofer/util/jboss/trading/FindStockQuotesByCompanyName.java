
package net.froihofer.util.jboss.trading;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findStockQuotesByCompanyName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="findStockQuotesByCompanyName">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="partOfCompanyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findStockQuotesByCompanyName", propOrder = {
    "partOfCompanyName"
})
public class FindStockQuotesByCompanyName {

    @XmlElement(required = true)
    protected String partOfCompanyName;

    /**
     * Gets the value of the partOfCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartOfCompanyName() {
        return partOfCompanyName;
    }

    /**
     * Sets the value of the partOfCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartOfCompanyName(String value) {
        this.partOfCompanyName = value;
    }

}
