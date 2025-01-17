package tutorial_v45.chapter1_fundamentals;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class d_HTTP_protocol_interceptors {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.custom()
                .addInterceptorLast(new HttpRequestInterceptor() {
                    public void process(final HttpRequest request,
                                        final HttpContext context) throws HttpException, IOException {
                        AtomicInteger count = (AtomicInteger) context.getAttribute("count");
                        request.addHeader("Count", Integer.toString(count.getAndIncrement()));
                    }
                })
                .build();

        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("count", count);
        HttpGet httpget = new HttpGet("http://localhost/");
        for (int i = 0; i < 10; i++) {
            CloseableHttpResponse response = httpclient.execute(httpget, localContext);
            try {
                HttpEntity entity = response.getEntity();
            } finally {
                response.close();
            }
        }
    }

}
