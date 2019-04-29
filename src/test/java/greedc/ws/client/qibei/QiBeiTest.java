package greedc.ws.client.qibei;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ecustom.Log;
import greedc.ws.client.qb.DetailBean;
import greedc.ws.client.qb.QiBeiServiceLocator;
import greedc.ws.client.qb.QiBeiServicePortType;

public class QiBeiTest {
	
	private final static Log log = new Log(QiBeiTest.class);
	public final static String WSDL_LOCATION = "http://192.168.130.107:8088/services/QiBeiService";
	private String info;
	List<DetailBean> detailList;
	
	@Test
	public void testFlow() {
		
		QiBeiServicePortType service = null;
		
			try {
				service = new QiBeiServiceLocator().getQiBeiServiceHttpPort(new URL(WSDL_LOCATION));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (javax.xml.rpc.ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			detailList = new ArrayList<DetailBean>();
			DetailBean bean = new DetailBean();
			bean.setDingdh("1539916880134249074968");
			bean.setHetwb("<a href='http://haikongbl.haikongjinrong.com/mobile/renting/filelist?pcid=1538119453&orderuuid=1538033831162338383749' target='_blank'>附件</a>");;
			
			detailList.add(bean);

			
			DetailBean bean1 = new DetailBean();
			bean1.setDingdh("153983542310004551011");
			bean1.setHetwb("<a href='http://haikongbl.haikongjinrong.com/mobile/renting/filelist?pcid=1538119453&orderuuid=1538033831162338383749' target='_blank'>附件</a>");
			detailList.add(bean1);
		
//			<a href='http://haikongbl.haikongjinrong.com/mobile/renting/filelist?pcid=1538119453&orderuuid=1538033831162338383749' target='_blank'>附件</a>
			
			
			DetailBean[] detail = detailList.toArray(new DetailBean[detailList.size()]);
			
		
			try {
				info =  service.getQiBeiData("26725","100", "200", "500", "3",detail);
			} catch (java.rmi.RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("info=" + info);
	
	}
	


	
	
	
	
}


