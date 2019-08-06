
package com.ghroth251.fateService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ghroth251.fateService package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReturnMsg_QNAME = new QName("http://webService/", "returnMsg");
    private final static QName _ReturnMsgResponse_QNAME = new QName("http://webService/", "returnMsgResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ghroth251.fateService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReturnMsg }
     * 
     */
    public ReturnMsg createReturnMsg() {
        return new ReturnMsg();
    }

    /**
     * Create an instance of {@link ReturnMsgResponse }
     * 
     */
    public ReturnMsgResponse createReturnMsgResponse() {
        return new ReturnMsgResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "returnMsg")
    public JAXBElement<ReturnMsg> createReturnMsg(ReturnMsg value) {
        return new JAXBElement<ReturnMsg>(_ReturnMsg_QNAME, ReturnMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService/", name = "returnMsgResponse")
    public JAXBElement<ReturnMsgResponse> createReturnMsgResponse(ReturnMsgResponse value) {
        return new JAXBElement<ReturnMsgResponse>(_ReturnMsgResponse_QNAME, ReturnMsgResponse.class, null, value);
    }

}
