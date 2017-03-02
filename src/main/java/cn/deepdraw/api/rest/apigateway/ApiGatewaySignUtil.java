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
 * @Title: SignUtil.java 
 * @Package cn.deepdraw.api.rest.util 
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月25日 下午9:19:47 
 * @version V1.0   
 */
package cn.deepdraw.api.rest.apigateway;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SignUtil
 * @Description: TODO
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年2月25日 下午9:19:47
 * 
 */
public class ApiGatewaySignUtil {

	private static final String HMAC_SHA256 = "HmacSHA256";

	private static final String UTF8 = "UTF-8";

	private static final String NL = "\n";

	private static final List<String> baseSignHeaders = Arrays.asList("Accept", "Content-MD5", "Content-Type", "Date");

	/**
	 * @param secret
	 * @param method
	 * @param path
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @return
	 */
	public static String signatureStr(String secret, String method, String path, Map<String, String> headers,
			Map<String, String> querys, Map<String, String> bodys) {
		try {
			Mac hmacSha256 = Mac.getInstance(HMAC_SHA256);
			byte[] keyBytes = secret.getBytes("UTF-8");
			hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, HMAC_SHA256));

			return new String(
					Base64.encodeBase64(
							hmacSha256.doFinal(buildSignStr(method, path, headers, querys, bodys).getBytes(UTF8))),
					UTF8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String buildSignStr(String method, String path, Map<String, String> headers,
			Map<String, String> querys, Map<String, String> bodys) {
		StringBuilder sb = baseToSign(method, headers);
		sb.append(headersToSign(headers));
		sb.append(urlToSign(path, querys, bodys));

		return sb.toString();
	}

	private static StringBuilder baseToSign(String method, Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();

		sb.append(method.toUpperCase()).append(NL);
		if (null != headers) {
			sb.append(StringUtils.trimToEmpty(headers.get(baseSignHeaders.get(0)))).append(NL)
					.append(StringUtils.trimToEmpty(headers.get(baseSignHeaders.get(1)))).append(NL)
					.append(StringUtils.trimToEmpty(headers.get(baseSignHeaders.get(2)))).append(NL)
					.append(StringUtils.trimToEmpty(headers.get(baseSignHeaders.get(3))));
		}
		return sb.append(NL);
	}

	private static String headersToSign(Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();

		if (null != headers) {
			Map<String, String> sortedHeaders = new TreeMap<String, String>();
			sortedHeaders.putAll(headers);

			sortedHeaders.forEach((n, v) -> {
				sb.append(n).append(":").append(StringUtils.trimToEmpty(v)).append(NL);
			});
		}

		return sb.toString();
	}

	private static String urlToSign(String path, Map<String, String> querys, Map<String, String> bodys) {
		StringBuilder sb = new StringBuilder();

		sb.append(StringUtils.trimToEmpty(path));

		Map<String, String> sortMap = new TreeMap<String, String>();
		if (null != querys) {
			sortMap.putAll(querys);
		}

		if (null != bodys) {
			sortMap.putAll(bodys);
		}

		StringBuilder sbParam = new StringBuilder();
		sortMap.forEach((k, v) -> sbParam.append("&").append(k).append(StringUtils.isBlank(v) ? "" : "=" + v));

		if (0 < sbParam.length()) {
			sb.append("?").append(sbParam.deleteCharAt(0));
		}

		return sb.toString();
	}

	public static void addSignatureHeaders(Map<String, String> headers) {
		Set<String> headersTosign = new TreeSet<String>(headers.keySet());
		headersTosign.removeAll(baseSignHeaders);
		headersTosign.remove(Constant.X_CA_SIGNATURE);
		headers.put(Constant.X_CA_SIGNATURE_HEADERS, headersTosign.stream().reduce((a, b) -> a + "," + b).get());
	}

}
