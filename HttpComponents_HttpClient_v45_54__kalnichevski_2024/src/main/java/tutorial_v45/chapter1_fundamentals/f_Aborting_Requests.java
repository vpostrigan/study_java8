package tutorial_v45.chapter1_fundamentals;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class f_Aborting_Requests {

    public static void main(String[] args) {
        // HTTP requests being executed by HttpClient can be aborted at any stage of execution
        // by invoking HttpUriRequest#abort() method.
        //
        // This method is thread-safe and can be called from any thread.

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/");
            CloseableHttpResponse response = httpclient.execute(httpget);

            httpget.abort();
            try {
                // <...>
            } finally {
                response.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

}
