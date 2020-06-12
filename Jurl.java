import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;

public class Jurl  extends SwingWorker<Response,Object> {
     private int counter = 1;
     private Request request = null;
      private Response response=null;
    private String path;
public  Jurl(String p){
    path=p;

}

    public   void write(Request request) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("M:/uni/secondsummester/AP/Midterm/phase3/" + nameMaker(true)));
            outputStream.writeObject(request);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception cannot output file ");
        }

    }

    public   ArrayList<Request> read() throws FileNotFoundException {
        ArrayList<Request> test = new ArrayList<>();
        Path path = Paths.get("M:/uni/secondsummester/AP/Midterm/phase3");
        File directory = new File("M:/uni/secondsummester/AP/Midterm/phase3");
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                File[] directoryList = directory.listFiles();
                for (File s : directoryList) {
                    if (s.getName().contains(".txt") && s.getName().contains("request")) {
                        try {
                            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(s));
                            test.add((Request) inputStream.readObject());
                            inputStream.close();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();

                            System.out.println("can not read object or class not found ");
                        }
                    }
                }
            }
        } else {
            throw new FileNotFoundException("path is unavailable");
        }
        return test;
    }

    public   void showRequests(ArrayList<Request> list) {
        int i = 1;
        for (Request r : list
        ) {
            System.out.print(i + ") url: " + r.getUrl().toString() + "| ");
            System.out.print("method: " + r.getMethod().toString() + "| ");
            try {

                for (Map.Entry<String, String> entry : r.getHeaders().entrySet()) {
                    System.out.print("headers: ");
                    System.out.print(entry.getKey() + " : " + entry.getValue());
                }
            } catch (NullPointerException e) {

            }
            try {
                System.out.print("{");
                for (Map.Entry<String, String> entry : r.getFormdate().entrySet()) {

                    System.out.print(entry.getKey() + "=" + entry.getValue());
                    System.out.print("&");
                }
                System.out.print("}");
            } catch (NullPointerException e) {
                System.out.println();

            }
            System.out.println();
            i++;
        }


    }

    public   String nameMaker(boolean requestType) {
        String name;
        if (requestType)
            name = "request_" + System.currentTimeMillis() + ".txt";
        else
            name = "respond_" + System.currentTimeMillis() + ".txt";
        return name;
    }

    public   void help() {
        System.out.println("-M or --method : for input method");
        System.out.println("-S or -- save : for save ");
        System.out.println("-d or --date : for writing form date or body message ");
        System.out.println("-H or --header : for input header and use : for key and value set ");
        System.out.println("-O or --output : for use to show save response or not and enter\n file name with .html");
        System.out.println("-i  for show response headers or not");
        System.out.println("-f for follow redirect");
        System.out.println(" exit :for exit");
        System.out.println(" use list : show list of request ");
    }
public    void setjurl(String[] type) {

    Scanner input = new Scanner(System.in);
    boolean upload = false;
    String path = " ";
    while (true) {
        if (request == null) {
            System.out.println(" type Jurl for start and -h or --help for help");

            String allDate = input.nextLine();

            String[] types = allDate.trim().split(" ");
            if (types[0].equals("list")) {
                try {
                    showRequests(read());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("No request find to show");
                }
            } else if (types[0].equals("--help") || types[0].equals("-h")) {
                help();
            } else if (types[0].equals("exit")) {
                break;
            } else if (types[0].equals("fire")) {
                for (int j = 1; j < types.length; j++) {
                    try {
                        ArrayList<Request> temp1 = read();
                        request = temp1.get(parseInt(types[j]) - 1);
                        if (request != null)
                            formData();
                    } catch (FileNotFoundException | NullPointerException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        System.out.println("please enter a number");
                        e.printStackTrace();
                    }
                }
            } else {
                request = new Request(false);
                try {
                    request.setUrl(new URL(types[0]));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i < types.length; i++) {
                    if (types[i].equals("-H") || types[i].equals("--header")) {
                        String temp = types[i + 1];
                        temp = temp.trim();
                        String[] tempHeader = temp.split(":");
                        if (tempHeader.length == 2) {
                            request.addHeader(tempHeader[0], tempHeader[1]);
                        } else if (tempHeader.length == 0) {
                            System.out.println("No header input");
                        } else if (tempHeader.length == 1) {
                            request.addHeader(tempHeader[0], "");
                        }
                        i++;
                    } else if (types[i].equals("-S") || types[i].equals("--save")) {
                        request.setSave(true);
                    } else if (types[i].equals("-i")) {
                        request.setShowResponse(true);
                    } else if (types[i].equals("-f")) {
                        request.setFollow(true);
                    } else if (types[i].equals("--upload")) {
                        upload = true;
                        path = types[i + 1];
                        i++;

                    } else if (types[i].equals("-d") || types[i].equals("--date")) {
                        String temp = types[i + 1];
                        temp = temp.trim();
                        String[] tempbody = temp.split("#");
                        for (String s : tempbody
                        ) {
                            String[] date = s.split("=");
                            request.addForm(date[0], date[1]);
                        }
                        i++;

                    } else if (types[i].equals("-M") || types[i].equals("--method")) {
                        request.setMethod(Methods.valueOf(types[i + 1]));
                        i++;
                    } else if (types[i].equals("-O") || types[i].equals("--output")) {
                        request.setSaveRespond(true);

                        try {
                            if (types[i + 1].contains(".")) {
                                request.setSaveAddress(types[i + 1]);
                                i++;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            break;
                        }

                    }

                }
            }
        }

        if (request != null) {
            if (request.isSave()) {
                write(request);
            }
            if (upload) {
                uploadBinary(path);
                upload = false;
            } else
                formData();
            request = null;
        }
    }
}

    public static void main (String[]types1) {
        Jurl jurl=new Jurl(null);
        jurl.setjurl(types1);


    }
    @Override
    protected Response doInBackground() throws Exception {
        if (request != null) {
            if (request.isSave()) {
                write(request);
            }
            if (request.isUploadBinary()) {
                uploadBinary(path);
                request. setUploadBinary(false);
            } else
                formData();
            request = null;
        }

        return null;
    }
    public  void   formData() {
        HashMap<String, String> fooBody = request.getFormdate();

        try {
            URL url = request.getUrl();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String boundary = request.getBoundry();
            connection.setRequestMethod(request.getMethod().name());
            connection.setDoOutput(true);
            if (request.isFollow()) {
                connection.setInstanceFollowRedirects(true);
            } else {
                connection.setInstanceFollowRedirects(false);
            }

            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()
            ) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (!request.getMethod().toString().equals("GET")) {
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                BufferedOutputStream request1 = new BufferedOutputStream(connection.getOutputStream());
                bufferOutFormData(fooBody, boundary, request1);
            }
            try {

                BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                byte[] all = bufferedInputStream.readAllBytes();
                System.out.println(new String(all));
                response.setAll(all);
                if (request.isSaveRespond()) {
                    saveRespond(connection, all, request.getSaveAddress());
                }

            } catch (IOException e) {
                System.out.println("type 400,500 error");
              if(connection.getResponseCode()==500){

                  BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                  byte[] all = bufferedInputStream.readAllBytes();
                  System.out.println(new String(all));
                  response.setAll(all);
                  if (request.isSaveRespond()) {
                      saveRespond(connection, all, request.getSaveAddress());
                  }

              }

                e.printStackTrace();
            }
            response.setTime(connection.getExpiration());
            response.setMessage(connection.getResponseMessage());
            response.setCode(connection.getResponseCode());
            response.setRespondheader(connection.getHeaderFields());
            System.out.println(connection.getRequestMethod() + " " + connection.getResponseCode() + " " + connection.getResponseMessage());
            if (request.isShowResponse())
                System.out.println(connection.getHeaderFields());


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public   void bufferOutFormData(HashMap<String, String> body, String boundary, BufferedOutputStream bufferedOutputStream) throws IOException {
        for (Map.Entry<String, String> entry : body.entrySet()) {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            if (entry.getValue().contains(".txt") || entry.getValue().contains(".png")) {
                bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + (new File(entry.getValue())).getName() + "\"\r\nContent-Type: Auto\r\n\r\n").getBytes());
                try {
                    BufferedInputStream tempBufferedInputStream = new BufferedInputStream(new FileInputStream(new File("M:/uni/secondsummester/AP/Midterm/phase3/" + entry.getValue())));
                    byte[] filesBytes = tempBufferedInputStream.readAllBytes();
                    bufferedOutputStream.write(filesBytes);
                    bufferedOutputStream.write("\r\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            } else {
                bufferedOutputStream.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n").getBytes());
                bufferedOutputStream.write((entry.getValue() + "\r\n").getBytes());
            }
        }
        bufferedOutputStream.write(("--" + boundary + "--\r\n").getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public   void setRequest(Request request) {
        this.request = request;
    }

    public   void saveRespond(HttpURLConnection urlConnection, byte[] all, String addrees) {
        String name = "nothing.txt";
        if (addrees.equals(" ")) {
            String type = urlConnection.getContentType();
            if (type == null) {
                System.out.println("No Content");
            } else {
                String[] temp = type.split("/");
                if (temp[0].toLowerCase().equals("text")) {
                    if (temp[1].toLowerCase().contains("html"))
                        temp[0] = "html";
                    else
                        temp[0] = "txt";
                } else
                    temp = temp[1].split("; ");
                name = "respond______" + System.currentTimeMillis() + "." + temp[0];
            }
        } else {
            name = addrees;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("M:/uni/secondsummester/AP/Midterm/phase3/" + name);
            fileOutputStream.write(all);
            fileOutputStream.flush();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("M:/uni/secondsummester/AP/Midterm/phase3/" + nameMaker(false)));
            Response response = new Response();
            response.setAll(all);
            response.setCode(urlConnection.getResponseCode());
            response.setMessage(urlConnection.getResponseMessage());
            outputStream.writeObject(response);
            outputStream.flush();
            outputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public   void uploadBinary(String path) {
        try {
            URL url = request.getUrl();
            File file = new File(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(request.getMethod().name());
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(connection.getOutputStream());
            BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedOutputStream.write(fileInputStream.readAllBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            System.out.println(new String(bufferedInputStream.readAllBytes()));
            System.out.println(connection.getResponseCode());
            System.out.println(connection.getHeaderFields());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Request getRequest() {
        return request;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}


