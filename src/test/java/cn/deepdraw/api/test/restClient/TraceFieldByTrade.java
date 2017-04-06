package cn.deepdraw.api.test.restClient;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.apache.http.MethodNotSupportedException;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.deepdraw.api.rest.client.Client;
import cn.deepdraw.api.rest.client.DeepdrawRestClientBuilders;
import cn.deepdraw.api.rest.client.RestResponse;

/**
 * @ClassName: TraceFieldByTrade
 * @Description: 按类目编历得行业字段
 * @author 袁良锭(yuanliangding@deepdraw.cn)
 * @date 2017年4月6日 上午11:19:55
 * 
 */
public class TraceFieldByTrade {

	private Client client = null;

	private String APP_KEY = "23618344";
	private String APP_SECRET = "d489895c2ad895fe4a4fdfc4586bedcc";
	private String SERVER_URL = "http://open.deepdraw.cn";

	private ObjectMapper mapper = null;

	private PrintWriter printWriter = null;

	@Before
	public void setup() throws IOException {
		String dir = "/home/yuanliangding/桌面/"; // 这个换成你自己的目录

		client = DeepdrawRestClientBuilders.deepdrawRestClientSetup(SERVER_URL, APP_KEY, APP_SECRET).build();

		mapper = new ObjectMapper();
		FileWriter fileWriter = new FileWriter(dir + "fields_" + new Date() + ".txt");
		printWriter = new PrintWriter(fileWriter);
	}

	@Test
	public void trace() throws Exception {
		RestResponse restResponse = null;

		/*
		 * 1:所有类目; 
		 * 2:服装;
		 * 490:母婴;
		 * 514 鞋类箱包
		 * 
		 * 更多类目信息，请填写以下代码  /trades;id=?/subs 处信息的参数为 1。
		 * 在导出文件中，的最前面部分“品类信息”处查看
		 * (在线json编辑器查看更文件：http://www.bejson.com/jsoneditoronline/)
		 * 
		 * */
		restResponse = client.get("/trades;id=2/subs", null, null);
		if (200 == restResponse.getStatusCode()) {

			JsonNode jsonNode = mapper.readTree(restResponse.getBody());

			printWriter.println("=======  品类信息  =======");
			printWriter.println(mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(mapper.readValue(restResponse.getBody(), List.class)));

			printField(jsonNode);
		}

		printWriter.flush();
		printWriter.close();

	}

	private void printField(JsonNode trades) {
		trades.forEach(trade -> {
			try {
				RestResponse restResponse = client.get("/trades;id=" + trade.path("id") + "/fields", null, null);

				printWriter.println(
						"------  字段信息（" + trade.path("name").textValue() + ",id=" + trade.path("id") + "）  ------");
				if (200 == restResponse.getStatusCode()) {
					printWriter.println(mapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(mapper.readValue(restResponse.getBody(), List.class)));
				} else {
					printWriter.println("######## 获取字段信息失败 ########");
				}

				printField(trade.path("children"));
			} catch (MethodNotSupportedException | IOException e) {
				e.printStackTrace();
			}
		});

	}

}
