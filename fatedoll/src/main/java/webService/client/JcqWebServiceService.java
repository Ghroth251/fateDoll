
package webService.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "JcqWebServiceService", targetNamespace = "http://ghroth251.com/", wsdlLocation = "http://localhost:51419/fate?wsdl")
public class JcqWebServiceService
    extends Service
{

    private final static URL JCQWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException JCQWEBSERVICESERVICE_EXCEPTION;
    private final static QName JCQWEBSERVICESERVICE_QNAME = new QName("http://ghroth251.com/", "JcqWebServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:51419/fate?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        JCQWEBSERVICESERVICE_WSDL_LOCATION = url;
        JCQWEBSERVICESERVICE_EXCEPTION = e;
    }

    public JcqWebServiceService() {
        super(__getWsdlLocation(), JCQWEBSERVICESERVICE_QNAME);
    }

    public JcqWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), JCQWEBSERVICESERVICE_QNAME, features);
    }

    public JcqWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, JCQWEBSERVICESERVICE_QNAME);
    }

    public JcqWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, JCQWEBSERVICESERVICE_QNAME, features);
    }

    public JcqWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public JcqWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns JcqWebService
     */
    @WebEndpoint(name = "JcqWebServicePort")
    public JcqWebService getJcqWebServicePort() {
        return super.getPort(new QName("http://ghroth251.com/", "JcqWebServicePort"), JcqWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns JcqWebService
     */
    @WebEndpoint(name = "JcqWebServicePort")
    public JcqWebService getJcqWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ghroth251.com/", "JcqWebServicePort"), JcqWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (JCQWEBSERVICESERVICE_EXCEPTION!= null) {
            throw JCQWEBSERVICESERVICE_EXCEPTION;
        }
        return JCQWEBSERVICESERVICE_WSDL_LOCATION;
    }

}