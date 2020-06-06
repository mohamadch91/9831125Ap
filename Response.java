import java.io.Serializable;

public class Response implements Serializable {
    String response;

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }


    public Response(String all){
        response=all;
    }
}
