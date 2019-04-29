package ecustom.webservice.server.qibei;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IQiBeiService {
	
	//骑呗打款金额  qibdkje
	//骑呗预留金额  qibylje
	//本次抵扣金额  bencdkje
	//本次抵扣结余金额  bencdkjyje
	//流程Id  requestId

	@WebMethod(operationName="getQiBeiData", action="urn:ecustom.webservice.server.qibei.IQiBeiService.getQiBeiData")
	public String getQiBeiData(String requestId,String qibdkje, String qibylje, String bencdkje, String bencdkjyje,	List<DetailBean> detailList);
}
