package tutorial_v45.chapter1_fundamentals;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class a_Request_execution {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // simple request
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/");
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // <...>
            } finally {
                response.close();
            }
        } catch (Exception e) {
            // ignore
        }

        // [1.1.1. HTTP request]
        {
            HttpGet httpget = new HttpGet(
                    "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
        }
        {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("hl", "en")
                    .setParameter("q", "httpclient")
                    .setParameter("btnG", "Google Search")
                    .setParameter("aq", "f")
                    .setParameter("oq", "")
                    .build();
            HttpGet httpget = new HttpGet(uri);
            System.out.println(httpget.getURI());
            // http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=
        }

        // [1.1.2. HTTP response]
        {
            HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
            System.out.println(response.getProtocolVersion());
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getReasonPhrase());
            System.out.println(response.getStatusLine().toString());
            // HTTP/1.1
            // 200
            // OK
            // HTTP/1.1 200 OK
        }

        // [1.1.3. Working with message headers]
        {
            HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
            response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
            response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
            Header h1 = response.getFirstHeader("Set-Cookie");
            Header h2 = response.getLastHeader("Set-Cookie");
            Header[] hs = response.getHeaders("Set-Cookie");

            System.out.println(h1); // Set-Cookie: c1=a; path=/; domain=localhost
            System.out.println(h2); // Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
            System.out.println(hs.length); // 2

            HeaderIterator it = response.headerIterator("Set-Cookie");
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            // Set-Cookie: c1=a; path=/; domain=localhost
            // Set-Cookie: c2=b; path="/", c3=c; domain="localhost"

            HeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
            while (it2.hasNext()) {
                HeaderElement elem = it2.nextElement();
                System.out.println(elem.getName() + " = " + elem.getValue());
                NameValuePair[] params = elem.getParameters();
                for (int i = 0; i < params.length; i++) {
                    System.out.println(" " + params[i]);
                }
            }
            // c1 = a
            //  path=/
            //  domain=localhost
            // c2 = b
            //  path=/
            // c3 = c
            //  domain=localhost
        }
    }

}
