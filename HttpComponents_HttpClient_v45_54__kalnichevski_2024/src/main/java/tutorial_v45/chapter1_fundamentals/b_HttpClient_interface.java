package tutorial_v45.chapter1_fundamentals;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class b_HttpClient_interface {

    public static void main(String[] args) {
        // [1.2. HttpClient interface]
        {
            ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    long keepAlive = super.getKeepAliveDuration(response, context);
                    if (keepAlive == -1) {
                        // Keep connections alive 5 seconds if a keep-alive value
                        // has not be explicitly set by the server
                        keepAlive = 5000;
                    }
                    return keepAlive;
                }
            };
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setKeepAliveStrategy(keepAliveStrat)
                    .build();
        }
        // [1.2.1. HttpClient thread safety]
        {
            // HttpClient implementations are expected to be thread safe.
            // It is recommended that the same instance
            // of this class is reused for multiple request executions.
        }
        // [1.2.2. HttpClient resource deallocation]
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                // <...>
            } finally {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
