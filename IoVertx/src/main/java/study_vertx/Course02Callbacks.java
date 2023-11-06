package study_vertx;

import java.util.Optional;
import java.util.regex.Pattern;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class Course02Callbacks {

    public class ChatVerticle extends AbstractVerticle {
        @Override
        public void start() {
            System.out.println("BasicVerticle started");
            vertx.createNetServer().connectHandler(socket -> {
                // container.logger().info("socket connected");
                socket.handler(new User(this));
            }).listen(8080);
            // container.logger().info("ChatVerticle started");
        }
    }

    public class User implements Handler<Buffer> {
        private final Pattern newline = Pattern.compile("\\n");
        //private final Set<String> names;
        private final EventBus eventBus;
        private Optional<String> name;

        public User(Verticle verticle) {
            Vertx vertx = verticle.getVertx();
            //names = vertx.sharedData().getSet("names");
            eventBus = vertx.eventBus();
            name = Optional.empty();
        }

        @Override
        public void handle(Buffer buffer) {
            newline.splitAsStream(buffer.toString()).forEach(line -> {
                if (!name.isPresent()) {
                    setName(line);
                } else {
                    handleMessage(line);
                }
            });
        }

        // [below code was copied from web]

        public void setName(String name) {
            this.name = Optional.of(name);
        }

        public void handleMessage(String line) {
            eventBus.consumer(name.get(), (Message<String> msg) -> {
                sendClient(msg.body());
            });
        }

        public void sendClient(String message) {
            sendMessage(name.get(), message);
        }

        public void sendMessage(String user, String message) {
            eventBus.send(user, name.get() + ">" + message); //
        }

        // //

        public void broadcastMessage(String message) {
            String name = this.name.get();
            eventBus.publish(name + ".followers", name + ">" + message);
        }

        public void followUser(String user) {
            eventBus.consumer(user + ".followers", (Message<String> message) -> {
                sendClient(message.body());
            });
        }
    }

    public static void main(String[] args) {
        Course02Callbacks test = new Course02Callbacks();
    }

}