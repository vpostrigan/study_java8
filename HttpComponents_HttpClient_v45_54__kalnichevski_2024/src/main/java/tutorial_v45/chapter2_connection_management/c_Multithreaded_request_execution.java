package tutorial_v45.chapter2_connection_management;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class c_Multithreaded_request_execution {

    public static void main(String[] args) throws InterruptedException {
        // PoolingClientConnectionManager
        // If all connections for a given route have already been leased,
        // a request for a connection will block until a connection is released back to the pool

        // the connection manager does not block indefinitely in the connection request operation
        // by setting 'http.conn-manager.timeout' to a positive value

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        // URIs to perform GETs on
        String[] urisToGet = {
                "http://www.domain1.com/",
                "http://www.domain2.com/",
                "http://www.domain3.com/",
                "http://www.domain4.com/"
        };
        // create a thread for each URI
        GetThread[] threads = new GetThread[urisToGet.length];
        for (int i = 0; i < threads.length; i++) {
            HttpGet httpget = new HttpGet(urisToGet[i]);
            threads[i] = new GetThread(httpClient, httpget);
        }
        // start the threads
        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }
        // join the threads
        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }
    }

    static class GetThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;

        public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
            this.httpClient = httpClient;
            // HttpClient instances are thread safe and can be shared between multiple threads of execution,
            // it is highly recommended that each thread maintains its own dedicated instance of HttpContext
            this.context = HttpClientContext.create();
            this.httpget = httpget;
        }

        @Override
        public void run() {
            try (CloseableHttpResponse response = httpClient.execute(httpget, context)) {
                HttpEntity entity = response.getEntity();
            } catch (ClientProtocolException ex) {
                // Handle protocol errors
            } catch (IOException ex) {
                // Handle I/O errors
            }
        }
    }

}
