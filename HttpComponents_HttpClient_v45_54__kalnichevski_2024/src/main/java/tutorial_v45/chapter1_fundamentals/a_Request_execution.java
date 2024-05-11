package tutorial_v45.chapter1_fundamentals;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class a_Request_execution {

    public static void main(String[] args) throws IOException {
        // simple request
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/");
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // <...>
            } finally {
                response.close();
            }
        }

        // [1.1.1. HTTP request]
        {
            
        }
    }

}
