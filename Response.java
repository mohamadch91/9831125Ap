import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response implements Serializable {
    private static final long serialVersionUID = 3L;
     private byte[] all;
    private int code;
    private String message;
    private long time;
    private Map<String, List<String>> respondheader=new HashMap<>();
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

    public void setTime(long time) {
        this.time = time;
    }

    public Map<String, List<String>> getRespondheader() {
        return respondheader;
    }

    public void setRespondheader(Map<String, List<String>> respondheader) {
        this.respondheader = respondheader;
    }


    public long getTime() {
        return time;
    }
}
