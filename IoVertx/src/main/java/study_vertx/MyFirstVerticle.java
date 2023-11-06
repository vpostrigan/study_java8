package study_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        vertx.createHttpServer().requestHandler(r -> {
            r.response().end("<h1>Hello from my first " + "Vert.x 3 application</h1>");
        }).listen(8081, result -> {
            if (result.succeeded()) {
                fut.complete();
            } else {
                fut.fail(result.cause());
            }
        });
    }
}
