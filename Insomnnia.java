import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Insomnnia extends JFrame {
    private JPanel part1;
    private JPanel part2;
    private JPanel part3;
    private JTextField url;
    private JTextArea allRequests;
    HashMap<Requests, String> save;
    ;

    public Insomnnia() {
        save = new HashMap<>();
        part1 = new JPanel();
        part2 = new JPanel();
        part3 = new JPanel();
        url = new JTextField("https://api.myproduct.com/v1/users");
        allRequests = new JTextArea();
    }

    public void headerP1() {
        JPanel text = new JPanel();
        text.setBackground(new Color(102, 0, 153));
        text.setSize(100, 50);
        JTextArea textArea = new JTextArea("Insomnia");
        textArea.setFont(Font.getFont("Arial"));
        textArea.setBackground(new Color(102, 0, 153));
        textArea.setFocusable(true);
        textArea.setOpaque(true);
        textArea.setEditable(false);
        JComboBox  jComboBox = new JComboBox();
        jComboBox.setSize(100, 50);
        jComboBox.setFocusable(true);
        jComboBox.setOpaque(true);
        jComboBox.setBackground(new Color(102, 0, 153));
        text.add(textArea);
        text.add(jComboBox);
        part1.add(text);
    }

    public void showRequests() {
        JPanel saveList = new JPanel();
        for (Map.Entry<Requests, String> entry : save.entrySet()) {
            JTextArea textArea = new JTextArea();
            textArea.setFont(Font.getFont("Arial"));
//            textArea.setBackground(new Color(102, 0, 153);
            textArea.setFocusable(true);
            textArea.setOpaque(true);
            textArea.setEditable(false);
            textArea.setText(entry.getKey() + "    " + entry.getValue());
            saveList.add(textArea);
        }
        part1.add(saveList);
    }

    public void headerP2() {
        url.setEditable(true);
        url.setSize(200, 50);
        url.setFont(Font.getFont("Arial"));
        //
        JPanel request = new JPanel();
        request.setFocusable(true);
        request.setOpaque(true);
        request.setSize(50, 50);
        //
        String[] allRequest={"GET","PUT","DELETE","OPTION","POST","PATCH","HEAD"};
        //
        JComboBox itemJComboBox = new JComboBox(allRequest);
        //
        request.add(itemJComboBox);
        //
        JButton send = new JButton("SEND");
        send.setSize(50, 50);
        JButton save=new JButton("SAVE");
        save.setSize(50,50);
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setSize(350, 50);
        //
        header.add(request);
        header.add(url);
        header.add(send);
        header.add(save);
        part2.add(header);
        header.setLocation(0, 0);
    }

    public void bodyP2() {
        //
        JTabbedPane tab = new JTabbedPane();
        //
        JPanel body = new JPanel();
        JPanel header = new JPanel();
        //
        JComboBox bodyItem = new JComboBox();
        //
      bodyItem.addItem("FORM DATE");
//        JMenuItem json=new JMenuItem("JSON");
//        bodyItem.add(messageBody);
//        bodyItem.add(json);
        //
        JTextArea message=new JTextArea();
        message.setFont(Font.getFont("Arial"));
//            message.setBackground(new Color(102, 0, 153);
        message.setFocusable(true);
        message.setOpaque(true);
        message.setEditable(true);
        body.add(message);
        //


    }


}
