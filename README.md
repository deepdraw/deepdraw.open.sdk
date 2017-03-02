# deepdraw.open.sdk
深绘开放平台SDK

该SDK用于访问深绘开放平台，使用方法：

（1）为Rest客户端做准备（Rest客户端就是模拟成一个浏览器进行对深绘开放平台发起请求访问。这里不建议在浏览器端直接进行访问，以免安全您的安全验证信息被泄漏盗用）。
	Builder builder = DeepdrawRestClientBuilders.deepdrawRestClientSetup(SERVER_URL, APP_KEY, APP_SECRET);
	参数说明：
		SERVER_URL	开放平台服务器地址，填http://open.soomey.net。
		APP_KEY	授权信息。填23618344。
		APP_SECRET	授权信息。填d489895c2ad895fe4a4fdfc4586bedcc。
（2）创建客户端。
	Client client = builder.build();
（3）发起请求。
	RestResponse restResponse = client.get(apiURL, querys, headersl);
	参数说明：
		apiURL		api的url地址，具体可以参考《api参数说明》。
		querys		请求中必要的查询参数，比如分页信息的每页条目数和启始页码。有些api没有要求提供查询参数。具体可以参考《api参数说明》。
		headers		请求中必要的http头信息。有些api没有要求提供头信息。具体可以参考《api参数说明》。
