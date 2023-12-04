
package net.froihofer.util.jboss.trading;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for publicStockQuote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="publicStockQuote">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="floatShares" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="lastTradePrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="lastTradeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="marketCapitalization" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="stockExchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publicStockQuote", propOrder = {
    "companyName",
    "floatShares",
    "lastTradePrice",
    "lastTradeTime",
    "marketCapitalization",
    "stockExchange",
    "symbol"
})
public class PublicStockQuote {

    protected String companyName;
    protected Long floatShares;
    protected BigDecimal lastTradePrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastTradeTime;
    protected Long marketCapitalization;
    protected String stockExchange;
    protected String symbol;

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the floatShares property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFloatShares() {
        return floatShares;
    }

    /**
     * Sets the value of the floatShares property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFloatShares(Long value) {
        this.floatShares = value;
    }

    /**
     * Gets the value of the lastTradePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLastTradePrice() {
        return lastTradePrice;
    }

    /**
     * Sets the value of the lastTradePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLastTradePrice(BigDecimal value) {
        this.lastTradePrice = value;
    }

    /**
     * Gets the value of the lastTradeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastTradeTime() {
        return lastTradeTime;
    }

    /**
     * Sets the value of the lastTradeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastTradeTime(XMLGregorianCalendar value) {
        this.lastTradeTime = value;
    }

    /**
     * Gets the value of the marketCapitalization property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    /**
     * Sets the value of the marketCapitalization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMarketCapitalization(Long value) {
        this.marketCapitalization = value;
    }

    /**
     * Gets the value of the stockExchange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStockExchange() {
        return stockExchange;
    }

    /**
     * Sets the value of the stockExchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStockExchange(String value) {
        this.stockExchange = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbol(String value) {
        this.symbol = value;
    }


    @Override
    public String toString() {
        return "PublicStockQuote{" +
                "companyName='" + companyName + '\'' +
                ", floatShares=" + floatShares +
                ", lastTradePrice=" + lastTradePrice +
                ", lastTradeTime=" + lastTradeTime +
                ", marketCapitalization=" + marketCapitalization +
                ", stockExchange='" + stockExchange + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
