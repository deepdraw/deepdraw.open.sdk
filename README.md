## 深绘开放平台SDK

```
     _____    _______  _______  _____      _____    _____    __  __                __
    / ___ \  / _____/ / _____/ / ___ \    / ___ \  / __  \  /  \ \ \      /\      / /
   / /  / / / /____  / /____  / /__/ /   / /  / / / /__/ / / /\ \ \ \    /  \    / /
  / /  / / / _____/ / _____/ / _____/   / /  / / /   ___/ / /__\ \ \ \  / /\ \  / /
 / /__/ / / /____  / /____  / /        / /__/ / / /\ \   / ______ \ \ \/ /  \ \/ /
/______/ /______/ /______/ /_/        /______/ /_/  \_\ /_/      \_\ \__/    \__/

```                    
> 该SDK用于访问深绘开放平台，使用方法：

####1. 为Rest客户端做准备
```
Builder builder = DeepdrawRestClientBuilders.deepdrawRestClientSetup(SERVER_URL, APP_KEY, APP_SECRET); 
```
><small> 参数说明：</small><br />
><small> SERVER_URL	开放平台服务器地址，填http://open.soomey.net。</small><br />
><small> APP_KEY	授权信息。填23618344。</small><br />
><small> APP_SECRET	授权信息。填d489895c2ad895fe4a4fdfc4586bedcc。</small><br />

Rest客户端就是模拟成一个浏览器进行对深绘开放平台发起请求访问(这里不建议在浏览器端直接进行访问，以免安全您的安全验证信息被泄漏盗用）。


####2. 创建客户端
```
Client client = builder.build();
```


####3. 发起请求
```
RestResponse restResponse = client.get(apiURL, querys, headersl);
```
><small> 参数说明：</small><br />
><small> apiURL		api的url地址，具体可以参考《api参数说明》。</small><br />
><small> querys		请求中必要的查询参数，比如分页信息的每页条目数和启始页码。有些api没有要求提供查询参数。具体可以参考《api参数说明》。</small><br />
><small> headers		请求中必要的http头信息。有些api没有要求提供头信息。具体可以参考《api参数说明》。</small><br />