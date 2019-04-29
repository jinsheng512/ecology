package _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade;

public class WSGeneratePaymentFacadeSrvProxyProxy implements _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy {
  private String _endpoint = null;
  private _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy wSGeneratePaymentFacadeSrvProxy = null;
  
  public WSGeneratePaymentFacadeSrvProxyProxy() {
    _initWSGeneratePaymentFacadeSrvProxyProxy();
  }
  
  public WSGeneratePaymentFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSGeneratePaymentFacadeSrvProxyProxy();
  }
  
  private void _initWSGeneratePaymentFacadeSrvProxyProxy() {
    try {
      wSGeneratePaymentFacadeSrvProxy = (new _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxyServiceLocator()).getWSGeneratePaymentFacade();
      if (wSGeneratePaymentFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSGeneratePaymentFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSGeneratePaymentFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSGeneratePaymentFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSGeneratePaymentFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy getWSGeneratePaymentFacadeSrvProxy() {
    if (wSGeneratePaymentFacadeSrvProxy == null)
      _initWSGeneratePaymentFacadeSrvProxyProxy();
    return wSGeneratePaymentFacadeSrvProxy;
  }
  
  public java.lang.String generatePayment(java.lang.String params) throws java.rmi.RemoteException, generatepaymentfacade.client.WSInvokeException{
    if (wSGeneratePaymentFacadeSrvProxy == null)
      _initWSGeneratePaymentFacadeSrvProxyProxy();
    return wSGeneratePaymentFacadeSrvProxy.generatePayment(params);
  }
  
  
}