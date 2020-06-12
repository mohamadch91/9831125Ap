import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UID;
import java.util.Date;
import java.util.HashMap;

public class Request implements Serializable{
    private static final long serialVersionUID = 4L;
    private String name;
    private URL url;
    private Methods method;
    private HashMap<String, String> headers;
    private boolean showResponse;
    private boolean save;
    private String boundry;
    private String saveAddress=" ";
    private boolean follow;
    private HashMap<String, String> formdate;
    private boolean saveRespond=false;
    private boolean uploadBinary=false;
    public Request(boolean response) {
        url=null;
        method=null;
        headers=new HashMap<>();
        formdate=new HashMap<>();
        boundry=System.currentTimeMillis()+"";
        this.showResponse=response;
        save=false;
        showResponse=false;
        follow=false;
        makeAddress();
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
    public boolean isSave() {
        return save;
    }

    public boolean isShowResponse() {
        return showResponse;
    }

    public void setShowResponse(boolean showResponse) {
        this.showResponse = showResponse;
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

    public void setBoundry(String boundry) {
        this.boundry = boundry;
    }
    public void setSave(boolean save) {
        this.save = save;
    }

    public void setSaveAddress(String saveAddress) {
        this.saveAddress = saveAddress;
    }
    public void makeAddress(){
        if(saveAddress.equals("")){
            Date date=new Date();
            saveAddress="output["+date.toString()+"]";
        }
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public String getSaveAddress() {
        return saveAddress;
    }

    public boolean isSaveRespond() {
        return saveRespond;
    }
    public void setSaveRespond(boolean saveRespond) {
        this.saveRespond = saveRespond;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isUploadBinary() {
        return uploadBinary;
    }

    public void setUploadBinary(boolean uploadBinary) {
        this.uploadBinary = uploadBinary;
    }
}
