package http;

public class HttpResponse {
    private byte[] body;
    private String status;
    private String statusCode;

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public void setStatus(HttpStatus status) {
        this.status = String.valueOf(status);
        setStatusCode(status);
    }

    private void setStatusCode(HttpStatus status) {
        this.statusCode = String.valueOf(status.getValue());
    }

    public String getStatus() {
        return status;
    }

    public String getStatusCode() {
        return statusCode;
    }
}