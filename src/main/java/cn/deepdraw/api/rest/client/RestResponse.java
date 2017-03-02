/**   
 * @Title: Response.java 
 * @Package cn.deepdraw.api.rest.client 
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月25日 下午9:36:08 
 * @version V1.0   
 */
package cn.deepdraw.api.rest.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Response
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月25日 下午9:36:08
 * 
 */
public class RestResponse {

	private int statusCode;
	private Map<String, String> headers;
	private String body;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getHeader(String key) {
		String headValue = null;
		if (null != headers) {
			headValue = headers.get(key);
		}
		return headValue;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void setHeader(String key, String value) {
		if (null == this.headers) {
			this.headers = new HashMap<String, String>();
		}
		this.headers.put(key, value);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
