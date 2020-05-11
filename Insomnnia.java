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
    HashMap<String, Request> save;
    private JLabel status;
    private JLabel time;
    private JTabbedPane tab = new JTabbedPane();

    public Insomnnia(String name) {
        super(name);
        setLayout(new BorderLayout());
        save = new HashMap<>();
        part1 = new JPanel();
        part2 = new JPanel();
        part3 = new JPanel();

        allPart();
        resize();
        url = new JTextField("https://api.myproduct.com/v1/users");
        allRequests = new JTextArea();
        headerP1();

        //
        showRequests();
        headerP2();
        bodyP2();
        headerP3();
        bodyP3();
        menu();
    }

    public void resize() {
        part1.setVisible(true);
        part1.setLayout(null);
        part1.setBackground(Color.cyan);
        part2.setBackground(Color.BLACK);
        part3.setBackground(Color.ORANGE);
        part2.setVisible(true);
        part3.setVisible(true);
        part3.setLayout(null);
//        part1.setLocation(0, 0);
//        part1.setSize(400, 1000);
        part1.setPreferredSize(new Dimension(400,1000));
        part3.setPreferredSize(new Dimension(500,1000));
//        part2.setLocation(400, 0);
//        part2.setSize(500, 1000);
//        part3.setLocation(1100, 0);
//        part3.setSize(500, 1000);
//        part3.setBounds(1100,440,400,400);
//
    }

    public void headerP1() {
        JPanel text = new JPanel();
        part1.add(text, BorderLayout.NORTH);
        text.setLocation(0, 0);
        text.setBackground(new Color(102, 0, 153));
        JLabel textArea = new JLabel("Insomnia");
        textArea.setFont(new Font("Arial", 14, 50));
        textArea.setBackground(new Color(102, 0, 153));

        text.setBounds(0, 0, 400, 75);
        text.add(textArea);

    }

    public void showRequests() {
        JPanel saveList = new JPanel();
        for (Map.Entry<String, Request> entry : save.entrySet()) {
            JTextArea textArea = new JTextArea();
            textArea.setFont(Font.getFont("Arial"));
//            textArea.setBackground(new Color(102, 0, 153);
            textArea.setFocusable(true);
            textArea.setOpaque(true);
            textArea.setEditable(false);
            textArea.setText(entry.getValue().getType() + "    " + entry.getKey());
            saveList.add(textArea);
        }
        part1.add(saveList);
        saveList.setBounds(0, 0, 400, 900);
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
        String[] allRequest = {"GET", "PUT", "DELETE", "OPTION", "POST", "PATCH", "HEAD"};
        //
        JComboBox itemJComboBox = new JComboBox(allRequest);
        //
        request.add(itemJComboBox);
        //
        JButton send = new JButton("SEND");
        send.setSize(50, 50);
        JButton save = new JButton("SAVE");
        save.setSize(50, 50);
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
        JTextArea message = new JTextArea("body message ");
        message.setFont(Font.getFont("Arial"));
//            message.setBackground(new Color(102, 0, 153);
        message.setFocusable(true);
        message.setOpaque(true);
        message.setEditable(true);
        body.add(message);
        //
        JTextField key = new JTextField("key");
        key.setEditable(true);
        JTextField value = new JTextField("value");
        value.setEditable(true);
        JCheckBox checkBox = new JCheckBox();
        JButton delete = new JButton("\uD83D\uDDD1");
        JButton add = new JButton("new field");
        header.add(key);
        header.add(value);
        header.add(checkBox);
        header.add(delete);
        header.add(add);
        tab.add(body, "Body");
        tab.add(header, "Header");
//        tab.setVisible(true);
        part2.add(tab);
    }

    public void headerP3() {
        status = new JLabel("OK 600");
        time = new JLabel("1.612 s");
        status.setFont(new Font("Arial",14,25));
        status.setForeground(Color.green);
        time.setForeground(Color.RED);
        time.setFont(new Font("Arial",14,25));
        JPanel show = new JPanel();
        show.setLayout(new FlowLayout());
        show.add(status);
        show.add(time);
        //
        status.setBounds(25,20,150,25);
        time.setBounds(50,20,150,25);
        show.setBounds(0,0,500,50);
        show.setBackground(Color.BLUE);
        part3.add(show);
    }
    public void bodyP3() {
        JPanel body = new JPanel();
//        JPanel bodyP=new JPanel();
        JPanel headaer = new JPanel();
        //
        JTextField textField=new JTextField();
        textField.setPreferredSize(new Dimension(400,700));
        body.add(textField);
//        JPopupMenu body1=new JPopupMenu();
//        body1.setBackground(Color.MAGENTA);
//        body1.setSize(100,100);
//        JMenuItem rawDate=new JMenuItem();
//        rawDate.add(body);
//        JMenuItem preview=new JMenuItem();
//        preview.add(bodyP);
        //
//        body1.add(rawDate);
//        body1.add(preview);
        //
//        body1.setVisible(true);
//        JPanel test=new JPanel();
//        test.add(body1);
        tab.add(body, "body");
        tab.setBounds(5,50,500,900);
//        body1.setLocation(100,100);
        //
        JLabel name = new JLabel("name");
        name.setFont(new Font("Arial",14,25));
        JLabel value = new JLabel("value");
        value.setFont(new Font("Arial",14,25));
        //
//        name.setPreferredSize(new Dimension(100,100));
        name.setBounds(0,50,100,50);
        value.setBounds(0,50,100,50);
        //
        JTextField name1 = new JTextField("key");
        name1.setEditable(false);
        name1.setBounds(50,50,100,100);
        //
        JTextField value1 = new JTextField("value");
        value1.setEditable(false);
        //
        headaer.add(name);
        headaer.add(value);
        headaer.add(name1);
        headaer.add(value1);
        //
        JButton copy = new JButton("Copy to Clipboard");
        headaer.add(copy);
        //
        tab.add(headaer, "Header");
        part3.add(tab);
    }

    public void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu application = new JMenu("Application");
        JMenu help = new JMenu("Help");
        JMenu view = new JMenu("View");
        //
        JMenuItem option = new JMenuItem("Option");
        JMenuItem exit = new JMenuItem("EXIT");
        application.add(option);
        application.add(exit);
        //
        JMenuItem full = new JMenuItem("Toggle Full Screen");
        JMenuItem side = new JMenuItem("Toggle Sidebar");
        view.add(full);
        view.add(side);
        //
        JMenuItem about = new JMenuItem("About");
        JMenuItem help1 = new JMenuItem("Help");
        help.add(about);
        help.add(help1);
        //
        menuBar.add(application);
        menuBar.add(view);
        menuBar.add(help);
        //
        setJMenuBar(menuBar);
    }

    public void allPart() {
//        GridBagConstraints constraints=new GridBagConstraints();
//        constraints.fill=GridBagConstraints.HORIZONTAL;
//        constraints.gridx=0;
//        constraints.gridy=0;
        add(part1,BorderLayout.WEST);
//        constraints.gridx=1;
        add(part2,BorderLayout.CENTER);
//        constraints.gridx=2;
//        constraints.weightx=0.5;
        add(part3,BorderLayout.EAST);
        pack();
    }


}