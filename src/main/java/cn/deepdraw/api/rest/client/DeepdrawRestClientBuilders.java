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
package cn.deepdraw.api.rest.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import cn.deepdraw.api.rest.apigateway.ApiGatewaySignUtil;
import cn.deepdraw.api.rest.apigateway.Constant;

/**
 * @ClassName: DeepdrawRestClientBuilders
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月26日 下午9:33:24
 * 
 */
public class DeepdrawRestClientBuilders {

	public static final String VERSION = "0.7.0";

	public static final String Description = "~_^";

	public static Builder deepdrawRestClientSetup(String host, String appKey, String appSecret) {
		return new DeepdrawRestClientBuilder(host, appKey, appSecret);
	}

	private static class DeepdrawRestClientBuilder implements Builder {

		private String host = null;

		private String appKey = null;

		private String appSecret = null;

		public DeepdrawRestClientBuilder(String host, String appKey, String appSecret) {
			super();
			this.host = host;
			this.appKey = appKey;
			this.appSecret = appSecret;
		}

		@Override
		public Client build() {
			return new DeepdrawRestClient();
		}

		private class DeepdrawRestClient implements Client {

			@Override
			public RestResponse get(String apiURL, Map<String, String> querys, Map<String, String> headers) {
				return execute(new HttpGet(host + apiURL), apiURL, querys, headers);
			}

			private RestResponse execute(HttpRequestBase httpRequestBase, String apiURL, Map<String, String> querys,
					Map<String, String> headers) {
				if (headers == null) {
					headers = new HashMap<String, String>();
				}
				headers.put(Constant.X_CA_TIMESTAMP, String.valueOf(new Date().getTime()));
				headers.put(Constant.X_CA_KEY, appKey);
				headers.put(Constant.X_CA_SIGNATURE, ApiGatewaySignUtil.signatureStr(appSecret,
						httpRequestBase.getMethod(), apiURL, headers, querys, null));
				ApiGatewaySignUtil.addSignatureHeaders(headers);

				headers.forEach((k, v) -> httpRequestBase.addHeader(k, v));

				RestResponse restResponse = null;
				try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
					CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpRequestBase);
					restResponse = convert(closeableHttpResponse);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				return restResponse;
			}

			private RestResponse convert(CloseableHttpResponse response) throws IOException {
				RestResponse res = new RestResponse();

				res.setStatusCode(response.getStatusLine().getStatusCode());
				res.setBody(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
				Arrays.stream(response.getAllHeaders()).forEach(h -> res.setHeader(h.getName(), h.getValue()));

				return res;
			}

		}

	}

}
