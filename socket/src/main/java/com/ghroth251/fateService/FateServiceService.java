
package com.ghroth251.fateService;

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
@WebServiceClient(name = "fateServiceService", targetNamespace = "http://webService/", wsdlLocation = "http://localhost:11451/fate?wsdl")
public class FateServiceService
    extends Service
{

    private final static URL FATESERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException FATESERVICESERVICE_EXCEPTION;
    private final static QName FATESERVICESERVICE_QNAME = new QName("http://webService/", "fateServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:11451/fate?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FATESERVICESERVICE_WSDL_LOCATION = url;
        FATESERVICESERVICE_EXCEPTION = e;
    }

    public FateServiceService() {
        super(__getWsdlLocation(), FATESERVICESERVICE_QNAME);
    }

    public FateServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FATESERVICESERVICE_QNAME, features);
    }

    public FateServiceService(URL wsdlLocation) {
        super(wsdlLocation, FATESERVICESERVICE_QNAME);
    }

    public FateServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FATESERVICESERVICE_QNAME, features);
    }

    public FateServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FateServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FateService
     */
    @WebEndpoint(name = "fateServicePort")
    public FateService getFateServicePort() {
        return super.getPort(new QName("http://webService/", "fateServicePort"), FateService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FateService
     */
    @WebEndpoint(name = "fateServicePort")
    public FateService getFateServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webService/", "fateServicePort"), FateService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FATESERVICESERVICE_EXCEPTION!= null) {
            throw FATESERVICESERVICE_EXCEPTION;
        }
        return FATESERVICESERVICE_WSDL_LOCATION;
    }

}