import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UID;
import java.util.HashMap;

public class Request implements Serializable {
    private URL url;
    private Methods method;
    private HashMap<String, String> headers;
    private boolean showResponse;
    private String boundry;
    private HashMap<String, String> formdate;
    public Request(String url,String method,boolean response) {
        try {
            this.url=new URL(url);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        this.method=Methods.valueOf(method);
        headers=new HashMap<>();
        formdate=new HashMap<>();
        boundry="*$"+System.currentTimeMillis()+"";
        this.showResponse=response;
    }
    public  void  addHeader(String key ,String value){
        headers.put(key, value);
    }
    public void addForm(String key,String value){
        formdate.put(key, value);
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public void setFormdate(HashMap<String, String> formdate) {
        this.formdate = formdate;
    }

    public void setResponse(boolean response) {
        this.showResponse = response;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public Methods getMethod() {
        return method;
    }

    public HashMap<String, String> getFormdate() {
        return formdate;
    }

    public URL getUrl() {
        return url;
    }

    public String getBoundry() {
        return boundry;
    }
}
