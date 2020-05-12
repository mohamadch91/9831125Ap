import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    JFrame optionPanel;
    boolean tray;

    public Insomnnia(String name) {
        super(name);
        optionPanel = new JFrame("OPTION PANEL");
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
        headerP2();
        bodyP2();
        headerP3();
        bodyP3();
        menu();
//        theme();
    }

    public void resize() {
        part1.setVisible(true);
        part1.setLayout(null);
        part1.setBackground(Color.cyan);
        part2.setBackground(Color.BLACK);
        part3.setBackground(Color.ORANGE);
        part2.setVisible(true);
        part2.setLayout(new BoxLayout(part2, BoxLayout.Y_AXIS));
//        part2.repaint();
        part3.setVisible(true);
        part3.setLayout(null);
//        part1.setLocation(0, 0);
//        part1.setSize(400, 1000);
        part1.setPreferredSize(new Dimension(400, 1000));
        part3.setPreferredSize(new Dimension(500, 1000));
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
        saveList.setLayout(new BoxLayout(saveList,BoxLayout.Y_AXIS));
        for (Map.Entry<String, Request> entry : save.entrySet()) {
            JLabel textArea = new JLabel(" ");
            System.out.println("mamad");
//            textArea.setLineWrap(true);
            textArea.setFont(Font.getFont("Arial"));
//            textArea.setBackground(new Color(102, 0, 153);
            textArea.setFocusable(true);
            textArea.setOpaque(true);
//            textArea.setEditable(true);
            System.out.println(entry.getValue().getType() + "    " + entry.getKey());;
            textArea.setText(entry.getValue().getType() + "    " + entry.getKey());
            saveList.add(textArea);
        }
        part1.add(saveList);
        saveList.setBackground(Color.BLUE);
        saveList.setBounds(0,75, 400, 900);
    }

    public void headerP2() {
        url.setEditable(true);
        url.setSize(200, 50);
        url.setFont(Font.getFont("Arial"));
        //
//        JPanel request = new JPanel();
//        request.setFocusable(true);
//        request.setOpaque(true);
//        request.setSize(50, 50);
        //
        String[] allRequest = {Requests.GET.name(), "PUT", "DELETE", "OPTION", "POST", "PATCH", "HEAD"};
        //
        JComboBox itemJComboBox = new JComboBox(allRequest);
        //
//        request.add(itemJComboBox);
        //
        JButton send = new JButton("SEND");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Naming");
                String name = JOptionPane.showInputDialog(frame, "Please enter Request Name.");
//                frame.setVisible(true);
//                frame.setLocation(500, 500);
                Request test = new Request(Requests.valueOf(itemJComboBox.getSelectedItem().toString()), name);
                test.setUrl(url.getText());
                //
                JTabbedPane tabbedPane = (JTabbedPane) part2.getComponent(1);
                JPanel panel = (JPanel) tabbedPane.getComponent(0);
                JTextArea textArea = (JTextArea) panel.getComponent(0);
                test.setBody(textArea.getText());
                //
                JPanel panel1 = (JPanel) tabbedPane.getComponent(1);
                Component[] all = panel1.getComponents();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
                    if (all[i] instanceof JTextField) {
                        JTextField field = (JTextField) all[i];
                        JTextField field1 = (JTextField) all[i + 1];
                        if (field.getText().equals("key") || field1.getText().equals("value"))
                            test.addField("", "");
                        else
                            test.addField(field.getText(), field1.getText());
                        i+=4;
                    }
                }
                test.setTime(1.612);
                //
                save.put(name, test);
                showRequests();
                //
                Double time1=test.getTime();
               time.setText(time1.toString());
               //for status

                //
                JPanel body=(JPanel)tab.getComponent(0);
                JPanel header=(JPanel)tab.getComponent(1);
                //empty space for save


                //


            }
        });
        //
        send.setSize(50, 50);
        JButton save = new JButton("SAVE");
        save.setSize(50, 50);
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setSize(350, 50);
        //
        header.add(itemJComboBox);
        header.add(url);
        header.add(send);
        header.add(save);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setAlignmentY(Component.CENTER_ALIGNMENT);
//        header.setMinimumSize(new Dimension(500,50));

        header.setMaximumSize(new Dimension(1000, 50));
//        header.setPreferredSize(new Dimension(500,50));
        part2.add(header);
        part2.setPreferredSize(header.getPreferredSize());
        header.setLocation(0, 0);
    }

    public void bodyP2() {
        //
        JTabbedPane tab = new JTabbedPane();
        //
        JPanel body = new JPanel();
        JPanel header = new JPanel();
        //
//        JComboBox bodyItem = new JComboBox();
        //
//        bodyItem.addItem("FORM DATE");
//        JMenuItem json=new JMenuItem("JSON");
//        bodyItem.add(messageBody);
//        bodyItem.add(json);
        //
        JTextArea message = new JTextArea("body message ");
        message.setLineWrap(true);
        message.setFont(new Font("Arial", 14, 16));
//            message.setBackground(new Color(102, 0, 153);
        message.setFocusable(true);
        message.setOpaque(true);
        message.setEditable(true);
        message.setPreferredSize(new Dimension(body.getX() + 400, body.getY() + 900));
        body.add(message);
        //
        JPanel part = new JPanel();
        part.setLayout(new FlowLayout());
        JTextField key = new JTextField("key");
        key.setPreferredSize(new Dimension(100, 25));
        key.setEditable(true);
        JTextField value = new JTextField("value");
        value.setPreferredSize(new Dimension(100, 25));
        value.setEditable(true);
        //
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox test = (JCheckBox) e.getSource();
                if (test.isSelected()) {
                    key.setEditable(true);
                    value.setEditable(true);
                } else {
                    key.setEditable(false);
                    value.setEditable(false);
                }
            }
        });
        //
        JButton delete = new JButton("\uD83D\uDDD1");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.remove(part);
            }
        });
        //
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.add(add(header));
            }
        });
        //
        add.setPreferredSize(new Dimension(60, 25));
        delete.setPreferredSize(new Dimension(49, 25));
        part.add(key);
        part.add(value);
        part.add(checkBox);
        part.add(delete);
        part.add(add);
        header.add(part);
        tab.add(body, "Body");
        tab.add(header, "Header");
//        tab.setPreferredSize(part2.getPreferredSize());
        tab.setBounds(0, 0, 500, 700);
//        tab.setVisible(true);
        part2.add(tab);
    }

    public void headerP3() {
        status = new JLabel("OK 600");
        time = new JLabel("1.612 s");
        status.setFont(new Font("Arial", 14, 25));
        status.setForeground(Color.green);
        time.setForeground(Color.RED);
        time.setFont(new Font("Arial", 14, 25));
        JPanel show = new JPanel();
        show.setLayout(new FlowLayout());
        show.add(status);
        show.add(time);
        //
        status.setBounds(25, 20, 150, 25);
        time.setBounds(50, 20, 150, 25);
        show.setBounds(0, 0, 500, 50);
        show.setBackground(Color.BLUE);
        part3.add(show);
    }

    public void bodyP3() {
        JPanel body = new JPanel();
//        body.setLayout(n);
//        JPanel bodyP=new JPanel();
        JPanel headaer = new JPanel();
        headaer.setLayout(new BoxLayout(headaer,BoxLayout.Y_AXIS));
        headaer.setLayout(null);
        //
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 700));
        JScrollPane scrollPane = new JScrollPane(textField);
        body.add(scrollPane);
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
        tab.setBounds(5, 50, 500, 900);
//        body1.setLocation(100,100);
        //
        JPanel head=new JPanel();
        //
        JLabel name = new JLabel("name");
        name.setFont(new Font("Arial", 14, 20));
        JLabel value = new JLabel("value");
        value.setFont(new Font("Arial", 14, 20));
        //
//        name.setPreferredSize(new Dimension(100,100));
        name.setBounds(60, 20, 200, 25);
        value.setBounds(310, 20, 200, 25);
        //
        head.add(name);
        head.add(value);
        //
//        JTextField name1 = new JTextField("key");
//        name1.setFont(new Font("Arial", 14, 20));
//        name1.setEditable(false);
//        name1.setBounds(60, 80, 150,h 25);
//        //
//        JTextField value1 = new JTextField("value");
//        value1.setEditable(false);
//        value1.setFont(new Font("Arial", 14, 20));
//        value1.setBounds(310, 80, 150, 25);
        //yadam bashe add bezaram


        //
        headaer.add(head);
        head.setBounds(0,0,500,50);
//        headaer.add(name1);
//        headaer.add(value1);
        //
        JButton copy = new JButton("Copy to Clipboard");
        //
        headaer.add(copy);
        //

        int x = headaer.getComponentCount() / 2;
        copy.setBounds(300, 75 * x, 150, 25);
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
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option();
            }
        });
        option.setMnemonic('O');
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        //
        JMenuItem exit = new JMenuItem("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tray) {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    setVisible(false);
                } else {
                    System.exit(0);
                }
            }
        });
        exit.setMnemonic('E');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        application.add(option);
        application.add(exit);
        //
        JMenuItem full = new JMenuItem("Toggle Full Screen");
        full.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getExtendedState() == MAXIMIZED_BOTH) {
                    pack();
                } else
                    setExtendedState(MAXIMIZED_BOTH);
            }
        });
        //
        full.setMnemonic('F');
        full.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
        //
        JMenuItem side = new JMenuItem("Toggle Sidebar");
        side.setMnemonic('T');
        side.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.CTRL_MASK));
        //
        view.add(full);
        view.add(side);
        //
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });

        about.setMnemonic('A');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        //
        //
        JMenuItem help1 = new JMenuItem("Help");
        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });
        help1.setMnemonic('H');
        help1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        //
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
        add(part1, BorderLayout.WEST);
//        constraints.gridx=1;
        add(part2, BorderLayout.CENTER);
//        constraints.gridx=2;
//        constraints.weightx=0.5;
        add(part3, BorderLayout.EAST);
        pack();
    }

    public JPanel add(JPanel header) {
        JPanel part = new JPanel();
        part.setLayout(new FlowLayout());
        JTextField key = new JTextField("key");
        key.setPreferredSize(new Dimension(100, 25));
        key.setEditable(true);
        JTextField value = new JTextField("value");
        value.setPreferredSize(new Dimension(100, 25));
        value.setEditable(true);
        //
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox test = (JCheckBox) e.getSource();
                if (test.isSelected()) {
                    key.setEditable(true);
                    value.setEditable(true);
                } else {
                    key.setEditable(false);
                    value.setEditable(false);
                }
            }
        });
        //
        JButton delete = new JButton("\uD83D\uDDD1");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.remove(part);
            }
        });
        //
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.add(add(header));
            }
        });
        add.setPreferredSize(new Dimension(60, 25));
        delete.setPreferredSize(new Dimension(49, 25));
        part.add(key);
        part.add(value);
        part.add(checkBox);
        part.add(delete);
        part.add(add);
        return part;
    }

    public JPanel addP3(String s,String s1) {
        JPanel part = new JPanel();
        part.setLayout(new FlowLayout());

        JTextField name1 = new JTextField(s+"          ");
        name1.setFont(new Font("Arial", 14, 20));
        name1.setEditable(false);
        name1.setBounds(60, 80, 150, 25);
        //
        JTextField value1 = new JTextField(s1);
        value1.setEditable(false);
        value1.setFont(new Font("Arial", 14, 20));
        value1.setBounds(310, 80, 150, 25);
//        add.setPreferredSize(new Dimension(60,25));
//        delete.setPreferredSize(new Dimension(49,25));
        part.add(name1);
        part.add(value1);
//        part.add(checkBox);
//        part.add(delete);
//        part.add(add);
        return part;
    }

    public void option() {
        JCheckBox direct = new JCheckBox("Follow Redirect");
        JCheckBox close = new JCheckBox("Close on system tray");
        if (tray) {
            close.setSelected(true);
        } else
            close.setSelected(false);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox test = (JCheckBox) e.getSource();
                if (test.isSelected()) {
                    tray = true;
                } else {
                    tray = false;
                }
            }
        });
        JPanel panel = new JPanel();
        panel.add(direct);
        panel.add(close);
        optionPanel.add(panel);
        optionPanel.pack();
        optionPanel.setVisible(true);
    }

    public void about() {
        JFrame info = new JFrame("ABOUT");
        JPanel infoP = new JPanel();
        infoP.setLayout(new BoxLayout(infoP, BoxLayout.Y_AXIS));
        JTextField infoField = new JTextField("               Author: Mohammad choupan");
        JTextField email = new JTextField("              E-Mail: mohamadkhoee@yahoo.com");
        JTextField studentNum = new JTextField("              Student NUM : 9831125");
        infoField.setEditable(false);
        email.setEditable(false);
        studentNum.setEditable(false);
        infoP.add(infoField, CENTER_ALIGNMENT);
        infoP.add(email, CENTER_ALIGNMENT);
        infoP.add(studentNum, CENTER_ALIGNMENT);
        info.add(infoP);
        info.pack();
        info.setVisible(true);
    }

    public void help() {
        JFrame help = new JFrame();
        help.setVisible(true);
    }

}