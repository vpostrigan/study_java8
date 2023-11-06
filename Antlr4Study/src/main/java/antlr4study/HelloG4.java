package antlr4study;

import antlr4.HelloBaseListener;
import antlr4.HelloLexer;
import antlr4.HelloParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * https://habr.com/ru/post/341138/
 * https://github.com/savimar/Antlr4JavaIntellijIdea/blob/master/pom.xml
 */
public class HelloG4 {

    public void parse(String inputText) {
        HelloLexer lexer = new HelloLexer(CharStreams.fromString(inputText));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parse(tokens);
    }

    public void parse(TokenStream inputText) {
        HelloParser parser = new HelloParser(inputText);
        ParseTree tree = parser.r();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new HelloWalker(), tree);
    }

    private static class HelloWalker extends HelloBaseListener {
        public void enterR(HelloParser.RContext ctx) {
            System.out.println("Entering R : " + ctx.ID().getText());
        }

        public void exitR(HelloParser.RContext ctx) {
            System.out.println("Exiting R");
        }
    }

}
