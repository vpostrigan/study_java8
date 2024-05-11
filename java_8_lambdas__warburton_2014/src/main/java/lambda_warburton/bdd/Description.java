package lambda_warburton.bdd;

public class Description {

    public Description(String name) {
    }

    public static void describe(String name, Suite behavior) {
        Description description = new Description(name);
        behavior.specifySuite(description);
    }

    public void should(String description, Specification specification) {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            //Runner.current.recordSuccess(suite, description);
        } catch (AssertionError cause) {
            //Runner.current.recordFailure(suite, description, cause);
        } catch (Throwable cause) {
            //Runner.current.recordError(suite, description, cause);
        }
    }
}
