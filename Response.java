import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 3L;
     private byte[] all;
    private int code;
    private String message;

    public byte[] getAll() {
        return all;
    }

    public void setAll(byte[] all) {
        this.all = all;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
