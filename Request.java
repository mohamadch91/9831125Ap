import java.util.HashMap;

public class Request {
    private String name;
    private Requests type;
    private String url;
    private HashMap<String, String> header;
    private String body;
    private int time;

    public Request(Requests type1, String name) {
        this.name = name;
        type = type1;
    }

    public int getTime() {
        return time;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public String getUrl() {
        return url;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setType(Requests type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Requests getType() {
        return type;
    }
}
