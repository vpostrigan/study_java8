package tutorial_v45.chapter1_fundamentals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

        // [1.1.4. HTTP entity]
        {
            // an entity for a outgoing message
            StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
            System.out.println(myEntity.getContentType());
            System.out.println(myEntity.getContentLength());
            System.out.println(EntityUtils.toString(myEntity));
            System.out.println(EntityUtils.toByteArray(myEntity).length);
            // Content-Type: text/plain; charset=UTF-8
            // 17
            // important message
            // 17
        }

        // [1.1.5. Ensuring release of low level resources]
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/");
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {
                        // do something useful
                    } finally {
                        instream.close();
                        // keep the underlying connection alive by consuming the entity content
                    }
                }
            } finally {
                response.close();
                // immediately shuts down and discards the connection
            }

            // when only a small portion of the entire response content needs to be retrieved
            // The connection will not be reused, but all level resources held by it will be correctly deallocated.

            // CloseableHttpClient httpclient = HttpClients.createDefault();
            // HttpGet httpget = new HttpGet("http://localhost/");
            // CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    int byteOne = instream.read();
                    int byteTwo = instream.read();
                    // Do not need the rest
                }
            } finally {
                response.close();
            }
        }

        // [1.1.6. Consuming entity content]
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/");
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    long len = entity.getContentLength();
                    if (len != -1 && len < 2048) {
                        System.out.println(EntityUtils.toString(entity));
                    } else {
                        // Stream content out
                    }
                }
            } finally {
                response.close();
            }

            // read into a in-memory buffer (can be reused)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity = new BufferedHttpEntity(entity);
            }
        }

        // [1.1.7. Producing entity content]
        {
            File file = new File("somefile.txt");
            FileEntity entity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
            HttpPost httppost = new HttpPost("http://localhost/action.do");
            httppost.setEntity(entity);

            // HTML forms
            List<NameValuePair> formparams = new ArrayList<>();
            formparams.add(new BasicNameValuePair("param1", "value1"));
            formparams.add(new BasicNameValuePair("param2", "value2"));
            // param1=value1&param2=value2
            UrlEncodedFormEntity entity2 = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            HttpPost httppost2 = new HttpPost("http://localhost/handler.do");
            httppost2.setEntity(entity2);

            // Content chunking
            StringEntity entity3 = new StringEntity("important message", ContentType.create("plain/text", Consts.UTF_8));
            entity3.setChunked(true);
            HttpPost httppost3 = new HttpPost("http://localhost/acrtion.do");
            httppost3.setEntity(entity3);
        }

        // [1.1.8. Response handlers]
        {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost/json");
            ResponseHandler<MyJsonObject> rh = new ResponseHandler<MyJsonObject>() {
                @Override
                public MyJsonObject handleResponse(final HttpResponse response) throws IOException {
                    StatusLine statusLine = response.getStatusLine();
                    HttpEntity entity = response.getEntity();
                    if (statusLine.getStatusCode() >= 300) {
                        throw new HttpResponseException(
                                statusLine.getStatusCode(),
                                statusLine.getReasonPhrase());
                    }
                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    Gson gson = new GsonBuilder().create();
                    ContentType contentType = ContentType.getOrDefault(entity);
                    Charset charset = contentType.getCharset();
                    Reader reader = new InputStreamReader(entity.getContent(), charset);
                    return gson.fromJson(reader, MyJsonObject.class);
                }
            };
            MyJsonObject myjson = client.execute(httpget, rh);
        }
    }

    private static class MyJsonObject {
    }

}
