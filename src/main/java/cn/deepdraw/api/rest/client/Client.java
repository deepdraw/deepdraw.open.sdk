/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**   
 * @Title: Client.java 
 * @Package cn.deepdraw.api.rest.client 
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月26日 下午9:30:56 
 * @version V1.0   
 */
package cn.deepdraw.api.rest.client;

import java.io.IOException;
import java.util.Map;

import org.apache.http.MethodNotSupportedException;
import org.apache.http.client.ClientProtocolException;

/** 
 * @ClassName: Client 
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月26日 下午9:30:56 
 *  
 */
public interface Client {
	
	/** 
	 * @Title: get 
	 * @Description: TODO
	 * @param @param apiURL
	 * @param @param querys
	 * @param @param headers
	 * @param @return
	 * @param @throws MethodNotSupportedException
	 * @param @throws ClientProtocolException
	 * @param @throws IOException    设定文件 
	 * @return HttpResponse    返回类型 
	 * @throws 
	 */
	RestResponse get(String apiURL, Map<String, String> querys, Map<String, String> headers)
			throws MethodNotSupportedException, ClientProtocolException, IOException;

}
