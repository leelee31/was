package http;

public enum HttpExpression {
    SPACE("\\s"),
    COLON(":");

    private String ex;

    HttpExpression(String ex) {
        this.ex = ex;
    }
}
