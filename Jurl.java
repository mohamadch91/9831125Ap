import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Jurl {
    static int counter = 1;
    static Request request = null;

    public static void write(Request request) {
        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nameMaker()));
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

    public static ArrayList<Request> read() throws FileNotFoundException {
        ArrayList<Request> test = new ArrayList<>();
        Path path = Paths.get("M:/uni/secondsummester/AP/Midterm/phase2");
        File directory = new File("M:/uni/secondsummester/AP/Midterm/phase2");
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

    public static void showRequests(ArrayList<Request> list) {
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
            }
            catch (NullPointerException e){

            }
            try {
                System.out.print("{");
                for (Map.Entry<String, String> entry : r.getFormdate().entrySet()) {

                    System.out.print(entry.getKey() + "=" + entry.getValue());
                    System.out.print("&");
                }
                System.out.print("}");
            }
            catch (NullPointerException e) {
                System.out.println();

            }
            System.out.println();

        }


    }

    public static String nameMaker() {
        String name = "request_" +System.currentTimeMillis() + ".txt";
        return name;
    }

    public static void help() {
        System.out.println("-M or --method : for input method");
        System.out.println("-S or -- save : for save ");
        System.out.println("-d or --date : for writing form date or body message ");
        System.out.println("-H or --header : for input header and use : for key and value set ");
        System.out.println("-O or --output : for use to show save response or not and enter\n file name with .html");
        System.out.println("-i  for show response headers or not");
        System.out.println(" exit :for exit");
        System.out.println(" use list : show list of request ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            if (request == null) {
                System.out.println(" type Jurl for start and -h or --help for help");

                String allDate = input.nextLine();

                String[] types = allDate.trim().split(" ");
                if (types[1].equals("list")) {
                    try {
                        showRequests(read());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("No request find to show");
                    }
                } else if (types[1].equals("--help") || types[1].equals("-h")) {
                    help();
                } else if (types[1].equals("exit")) {
                    break;
                } else if (types[1].equals("fire")) {
                    for (int j = 2; j < types.length; j++) {
                        try {
                            ArrayList<Request> temp1 = read();
                            request = temp1.get(parseInt(types[j])-1);
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
                        request.setUrl(new URL(types[1]));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    for (int i = 2; i < types.length; i++) {
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
                            i++;
                        } else if (types[i].equals("-d") || types[i].equals("--date")) {
                            String temp = types[i + 1];
                            temp = temp.trim();
                            String[] tempbody = temp.split("&");
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
                            request.setShowResponse(true);
                            if (types[i + 1].contains(".html"))
                                request.setSaveAddress(types[i + 1]);
                            i++;
                        }

                    }
                }
            }
            if (request != null) {
                if (request.isSave())
                    write(request);
                formData();
                System.exit(0);
            }
        }
    }

    public static void formData() {
        HashMap<String, String> fooBody = request.getFormdate();

        try {
            URL url = request.getUrl();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String boundary = request.getBoundry();
            connection.setRequestMethod(request.getMethod().name());
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()
            ) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());

            }
            if(!request.getMethod().toString().equals("GET")) {
            BufferedOutputStream request = new BufferedOutputStream(connection.getOutputStream());
            bufferOutFormData(fooBody, boundary, request);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            System.out.println(new String(bufferedInputStream.readAllBytes()));
            }
            System.out.println(connection.getResponseCode());
            System.out.println(connection.getHeaderFields());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void bufferOutFormData(HashMap<String, String> body, String boundary, BufferedOutputStream bufferedOutputStream) throws IOException {
        for (Map.Entry<String,String> entry:body.entrySet()) {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            if (entry.getValue().contains(".txt")||entry.getValue().contains(".png")) {
            bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + (new File(entry.getValue())).getName() + "\"\r\nContent-Type: Auto\r\n\r\n").getBytes());
            try {
                BufferedInputStream tempBufferedInputStream = new BufferedInputStream(new FileInputStream(new File(entry.getValue())));
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

    public static void setRequest(Request request) {
        Jurl.request = request;
    }
}

