package study_vertx;

import io.vertx.junit5.VertxExtension;

import org.junit.jupiter.api.extension.ExtendWith;


import io.vertx.core.Vertx;


@ExtendWith(VertxExtension.class)
public class MyFirstVerticleTest {

    private Vertx vertx;
/*
    @BeforeAll
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    void http_server_check_response(Vertx vertx, VertxTestContext testContext) {
        vertx.deployVerticle(new HttpServerVerticle(), testContext.succeeding(id -> {
            HttpClient client = vertx.createHttpClient();
            client.request(HttpMethod.GET, 8080, "localhost", "/")
                    .compose(req -> req.send().compose(HttpClientResponse::body))
                    .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
                        assertThat(buffer.toString()).isEqualTo("Plop");
                        testContext.completeNow();
                    })));
        }));
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8081, "localhost", "/", response -> {
            response.handler(body -> {
                context.assertTrue(body.toString().contains("Hello"));
                async.complete();
            });
        });
    }
*/
}
