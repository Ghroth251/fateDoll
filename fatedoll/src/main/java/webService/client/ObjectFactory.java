
package webService.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webService.client package. 
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

    private final static QName _LinkSocketResponse_QNAME = new QName("http://ghroth251.com/", "linkSocketResponse");
    private final static QName _GetDataByComder_QNAME = new QName("http://ghroth251.com/", "getDataByComder");
    private final static QName _SendJcqMsgResponse_QNAME = new QName("http://ghroth251.com/", "sendJcqMsgResponse");
    private final static QName _SendJcqMsg_QNAME = new QName("http://ghroth251.com/", "sendJcqMsg");
    private final static QName _GetDataByComderResponse_QNAME = new QName("http://ghroth251.com/", "getDataByComderResponse");
    private final static QName _LinkSocket_QNAME = new QName("http://ghroth251.com/", "linkSocket");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webService.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDataByComder }
     * 
     */
    public GetDataByComder createGetDataByComder() {
        return new GetDataByComder();
    }

    /**
     * Create an instance of {@link SendJcqMsgResponse }
     * 
     */
    public SendJcqMsgResponse createSendJcqMsgResponse() {
        return new SendJcqMsgResponse();
    }

    /**
     * Create an instance of {@link LinkSocketResponse }
     * 
     */
    public LinkSocketResponse createLinkSocketResponse() {
        return new LinkSocketResponse();
    }

    /**
     * Create an instance of {@link GetDataByComderResponse }
     * 
     */
    public GetDataByComderResponse createGetDataByComderResponse() {
        return new GetDataByComderResponse();
    }

    /**
     * Create an instance of {@link LinkSocket }
     * 
     */
    public LinkSocket createLinkSocket() {
        return new LinkSocket();
    }

    /**
     * Create an instance of {@link SendJcqMsg }
     * 
     */
    public SendJcqMsg createSendJcqMsg() {
        return new SendJcqMsg();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkSocketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "linkSocketResponse")
    public JAXBElement<LinkSocketResponse> createLinkSocketResponse(LinkSocketResponse value) {
        return new JAXBElement<LinkSocketResponse>(_LinkSocketResponse_QNAME, LinkSocketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataByComder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "getDataByComder")
    public JAXBElement<GetDataByComder> createGetDataByComder(GetDataByComder value) {
        return new JAXBElement<GetDataByComder>(_GetDataByComder_QNAME, GetDataByComder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendJcqMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "sendJcqMsgResponse")
    public JAXBElement<SendJcqMsgResponse> createSendJcqMsgResponse(SendJcqMsgResponse value) {
        return new JAXBElement<SendJcqMsgResponse>(_SendJcqMsgResponse_QNAME, SendJcqMsgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendJcqMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "sendJcqMsg")
    public JAXBElement<SendJcqMsg> createSendJcqMsg(SendJcqMsg value) {
        return new JAXBElement<SendJcqMsg>(_SendJcqMsg_QNAME, SendJcqMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataByComderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "getDataByComderResponse")
    public JAXBElement<GetDataByComderResponse> createGetDataByComderResponse(GetDataByComderResponse value) {
        return new JAXBElement<GetDataByComderResponse>(_GetDataByComderResponse_QNAME, GetDataByComderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkSocket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ghroth251.com/", name = "linkSocket")
    public JAXBElement<LinkSocket> createLinkSocket(LinkSocket value) {
        return new JAXBElement<LinkSocket>(_LinkSocket_QNAME, LinkSocket.class, null, value);
    }

}
