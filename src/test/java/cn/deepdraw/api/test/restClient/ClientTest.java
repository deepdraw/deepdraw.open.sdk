/**   
 * @Title: ClientTest.java 
 * @Package cn.deepdraw.api.test.restClient 
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年3月1日 下午6:09:55 
 * @version V1.0   
 */
package cn.deepdraw.api.test.restClient;

import org.junit.Before;
import org.junit.Test;

import cn.deepdraw.api.rest.client.Client;
import cn.deepdraw.api.rest.client.DeepdrawRestClientBuilders;
import cn.deepdraw.api.rest.client.RestResponse;

/**
 * @ClassName: ClientTest
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年3月1日 下午6:09:55
 * 
 */
public class ClientTest {

	private Client client = null;

	private String APP_KEY = "23618344";
	private String APP_SECRET = "d489895c2ad895fe4a4fdfc4586bedcc";
	private String SERVER_URL = "http://open.soomey.net";

	@Before
	public void setup() {
		client = DeepdrawRestClientBuilders.deepdrawRestClientSetup(SERVER_URL, APP_KEY, APP_SECRET).build();
	}

	@Test
	public void getTest() throws Exception {
		RestResponse restResponse = null;
		
		restResponse = client.get("/merchants;id=1", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/merchants;name=wang", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/merchants;id=1/saleCalender", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/merchants;id=3/saleCalender;day=2020-10-07/listProduct", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/products;id=1", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/products;id=1/pictures", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/products;id=1/visionResource", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/trades;id=1/subs", null, null);
		System.out.println("body:" + restResponse.getBody());
		
		restResponse = client.get("/trades;id=1860/fields", null, null);
		System.out.println("body:" + restResponse.getBody());
		
//		restResponse = client.get("/test", null, null);
//		System.out.println("body:" + restResponse.getBody());

	}

}
