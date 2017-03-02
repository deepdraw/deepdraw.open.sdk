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
		RestResponse restResponse = client.get("/test", null, null);
		System.out.println("body:" + restResponse.getBody());

		RestResponse restResponse_ = client.get("/product/1", null, null);
		System.out.println("body:" + restResponse_.getBody());

		RestResponse restResponse___ = client.get("/merchant/4/saleCalender;day=2021-09-25/listProduct", null, null);
		System.out.println("body:" + restResponse___.getBody());

	}

}
