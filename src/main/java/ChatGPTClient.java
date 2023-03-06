import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ChatGPTClient {
    public static void main(String[] args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://api.openai.com/v1/engines/davinci-codex/completions");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer <YOUR_API_KEY>");

            String requestBody = "{\"prompt\": \"Hello, ChatGPT!\", \"max_tokens\": 5}";
            StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println(responseString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}