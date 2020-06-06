import java.awt.*;
import java.io.*;
import java.nio.file.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class Jurl {
   static int counter=1;
    public static void write(Request request)  {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nameMaker()));
            outputStream.writeObject(request);
            outputStream.flush();
            outputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("IO Exception cannot output file ");
        }

    }
    public static ArrayList<Request> read() throws FileNotFoundException {
        ArrayList<Request> test=new ArrayList<>();
        Path path= Paths.get("M:/uni/secondsummester/AP/Midterm/phase2");
        File directory=new File("M:/uni/secondsummester/AP/Midterm/phase2");
        if(Files.exists(path)){
            if(Files.isDirectory(path)){
                File[] directoryList=directory.listFiles();
                for (File s:directoryList) {
                    if(s.getName().contains(".txt")){
                        try{
                            ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(s));
                            test.add((Request)inputStream.readObject());
                            inputStream.close();
                        }
                        catch (IOException | ClassNotFoundException e){
                           e.printStackTrace();

                            System.out.println("can not read object or class not found ");
                        }
                    }
                }
            }
        }
        else{
            throw new FileNotFoundException("path is unavailable");
        }
        return  test;
    }
    public static void  showRequests(ArrayList<Request> list){

    }
public static String  nameMaker(){
        String name=""+counter+".txt";
        counter++;
        return  "";
}
public static void help(){





}
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        String allDate=input.nextLine();
        Request request=new Request(null,null,false);
        String[] types=allDate.trim().split(" ");
        if (types[1].equals("list")){
            try {
                showRequests(read());
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("No request find to show");
            }
        }
        else if (types[1].equals("--help")||types[1].equals("--h")){
                help();
        }
        else{
            for(int i=1;i<types.length;i++){



            }
        }
    }
}
