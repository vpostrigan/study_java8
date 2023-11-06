package lambda_warburton.bdd;

public final class Expect {
    public BoundExpectation that(Object value) {
        return new BoundExpectation(value);
    }
    // X$   ~
}