import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * simulator of insomnia app.
 *
 * @author Mohammad
 * @version 1.00
 * @since 1398-2-23
 */
public class Insomnnia extends JFrame {
    // part of app
    private JPanel part1;
    private JPanel part2;
    private JPanel part3;
    // url field
    private JTextField url;
    // to save request
    private HashMap<String, Request> save;
    // request response status and time
    private JLabel status;
    private JLabel time;
    // tab for show details
    private JTabbedPane tab = new JTabbedPane();
    // option menu tab
    private JFrame optionPanel;
    // stay in tray or not
    boolean tray;

    /**
     * run a simulator
     *
     * @param name name of frame
     */
    public Insomnnia(String name) {
        super(name);
        optionPanel = new JFrame("OPTION PANEL");
        setLayout(new BorderLayout());
        save = new HashMap<>();
        part1 = new JPanel();
        part2 = new JPanel();
        part3 = new JPanel();
        // add all parts to Jframe.
        allPart();
        resize();

        url = new JTextField("https://api.myproduct.com/v1/users");
        // header off app
        headerP1();
        // header of part2
        headerP2();
        // body of part 2
        bodyP2();
        //  header of part 3
        headerP3();
        // body of part 3
        bodyP3();
        // menu bar
        menu();
//        theme();
    }

    /**
     * set size for all parts of app.
     */
    public void resize() {
        // resize parts and add layouts
        part1.setVisible(true);
        part1.setLayout(null);
        // set background colors
        part1.setBackground(Color.GRAY);
        part2.setBackground(Color.BLACK);
        part3.setBackground(Color.GRAY);
        part2.setVisible(true);
        part2.setLayout(new BoxLayout(part2, BoxLayout.Y_AXIS));
        part3.setVisible(true);
        part3.setLayout(null);
        // set size of parts
        part1.setPreferredSize(new Dimension(400, 1000));
        part3.setPreferredSize(new Dimension(500, 1000));
    }

    /**
     * set header of part 1.
     */
    public void headerP1() {
        // new jpanel for header
        JPanel text = new JPanel();
        // add to part1
        part1.add(text, BorderLayout.NORTH);
        text.setLocation(0, 0);
        text.setBackground(new Color(102, 0, 153));
        // label of part 1.
        JLabel textArea = new JLabel("Insomnia");
        textArea.setForeground(Color.WHITE);
        // set font and size for Jlabel.
        textArea.setFont(new Font("Arial", 14, 50));
        textArea.setBackground(new Color(102, 0, 153));
        // size for jlabel.
        text.setBounds(0, 0, 400, 75);
        text.add(textArea);
    }

    /**
     * method to show requests in part1.
     * show type of request.
     * show name of request from user input.
     */
    public void showRequests() {
        // panel for request
        JPanel saveList = new JPanel();
        saveList.setLayout(new BoxLayout(saveList, BoxLayout.Y_AXIS));
        // one panel and label for each request.
        for (Map.Entry<String, Request> entry : save.entrySet()) {
            JLabel textArea = new JLabel(" ");
            JPanel panel = new JPanel();
            panel.add(textArea);
            panel.setBackground(Color.GRAY);
            panel.setMaximumSize(new Dimension(400, 50));
            textArea.setFont(new Font("Arial", 14, 25));
            textArea.setForeground(Color.WHITE);
            textArea.setBackground(Color.GRAY);
            textArea.setPreferredSize(new Dimension(400, 50));
            textArea.setMaximumSize(new Dimension(new Dimension(400, 50)));
            textArea.setBounds(0, 25, 400, 50);
            textArea.setFocusable(true);
            textArea.setOpaque(true);
            // set text request type and name
            textArea.setText(entry.getValue().getType() + "                " + entry.getKey());
            saveList.add(panel);
        }
        // set background
        saveList.setBackground(Color.BLACK);
        // add to part 1
        part1.add(saveList);
        saveList.setBounds(0, 75, 400, 900);
    }

    /**
     * add header of part2.
     * add bottons.
     * url boxes.
     * and send and save bottons.
     */
    public void headerP2() {
        // set url box for input
        url.setEditable(true);
        url.setSize(200, 50);
        url.setFont(Font.getFont("Arial"));
        // item box for request type
        String[] allRequest = {Requests.GET.name(), "PUT", "DELETE", "OPTION", "POST", "PATCH", "HEAD"};
        //add to Jitem box
        JComboBox itemJComboBox = new JComboBox(allRequest);
        // add send button to panel
        // no action now for next phase.
        JButton send = new JButton("SEND");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Naming");
                // get request name
                String name = JOptionPane.showInputDialog(frame, "Please enter Request Name.");
//             make new request
                Request test = new Request(Requests.valueOf(itemJComboBox.getSelectedItem().toString()), name);
                test.setUrl(url.getText());
                //reach components to set request field.
                JTabbedPane tabbedPane = (JTabbedPane) part2.getComponent(1);
                JPanel panel = (JPanel) tabbedPane.getComponent(0);
                JTextArea textArea = (JTextArea) panel.getComponent(0);
                test.setBody(textArea.getText());
                //get all header parts.
                JPanel panel1 = (JPanel) tabbedPane.getComponent(1);
                Component[] all = panel1.getComponents();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
                    if (all[i] instanceof JTextField) {
                        JTextField field = (JTextField) all[i];
                        JTextField field1 = (JTextField) all[i + 1];
                        // check empty or full .
                        if (field.getText().equals("key") || field1.getText().equals("value"))
                            test.addField("", "");
                        else
                            test.addField(field.getText(), field1.getText());
                        // reach next jtext field
                        i += 4;
                    }
                }
                test.setTime(1.612);
                // put request to save hash map.
                save.put(name, test);
                //update panel in part 1
                showRequests();
                //get time
                Double time1 = test.getTime();
                time.setText(time1.toString());
                //for status

                //
                JPanel body = (JPanel) tab.getComponent(0);
                JPanel header = (JPanel) tab.getComponent(1);
                //empty space for save


                //


            }
        });
        // set button size.
        send.setSize(50, 50);
        // save button.
        JButton save = new JButton("SAVE");
        save.setSize(50, 50);
        // panel for add al components in one panel  header.
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setSize(350, 50);
        // add all components
        header.add(itemJComboBox);
        header.add(url);
        header.add(send);
        header.add(save);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setAlignmentY(Component.CENTER_ALIGNMENT);
        header.setBackground(Color.BLACK);
        // header size
        header.setMaximumSize(new Dimension(1000, 50));
        // add header to part 2 panel
        part2.add(header);
        part2.setPreferredSize(header.getPreferredSize());
        header.setLocation(0, 0);
    }

    /**
     * add body of part2.
     * add tabbed pane for header part.
     * and body part .
     */
    public void bodyP2() {
        // tabbed pane for body and save
        JTabbedPane tab = new JTabbedPane();
        tab.setBackground(Color.GRAY);
        // panel for body message
        JPanel body = new JPanel();
        body.setBackground(Color.BLACK);
        // panel for header part
        JPanel header = new JPanel();
        header.setBackground(Color.BLACK);
        // adding combo box for extra point
//        JComboBox bodyItem = new JComboBox();
//        bodyItem.addItem("FORM DATE");
//        JMenuItem json=new JMenuItem("JSON");
//        bodyItem.add(messageBody);
//        bodyItem.add(json);
        // add text area for body message
        JTextArea message = new JTextArea("body message ");
        // go next line at the end
        message.setLineWrap(true);
        message.setBackground(Color.GRAY);
        message.setForeground(Color.WHITE);
        message.setFont(new Font("Arial", 14, 16));
        ;
        message.setFocusable(true);
        message.setOpaque(true);
        message.setEditable(true);
        // set size for body part
        message.setPreferredSize(new Dimension(body.getX() + 400, body.getY() + 900));
        // add text area to the body panel.
        body.add(message);
        // add Jpanel for key and value and bUttons
        JPanel part = new JPanel();
        //  set color
        part.setBackground(Color.BLACK);
        part.setLayout(new FlowLayout());
        // text field for key
        JTextField key = new JTextField("key");
        // set text color and back ground color.
        key.setBackground(Color.GRAY);
        key.setForeground(Color.WHITE);
        key.setPreferredSize(new Dimension(100, 25));
        key.setEditable(true);
        // text field for value
        JTextField value = new JTextField("value");
        value.setBackground(Color.GRAY);
        value.setForeground(Color.WHITE);
        value.setPreferredSize(new Dimension(100, 25));
        value.setEditable(true);
        //set check box for  areas
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox test = (JCheckBox) e.getSource();
                if (test.isSelected()) {
                    // if checked can edit text fields.
                    key.setEditable(true);
                    value.setEditable(true);
                }
                // else can not edit text field.
                else {
                    key.setEditable(false);
                    value.setEditable(false);
                }
            }
        });
        //add button delete.
        JButton delete = new JButton("\uD83D\uDDD1");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove all of panel from header panel
                header.remove(part);
            }
        });
        //add Add button
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add new panel to header with add method.
                header.add(add(header));
            }
        });
        //set size of buttons.
        add.setPreferredSize(new Dimension(60, 25));
        delete.setPreferredSize(new Dimension(49, 25));
        //add component to one panel.
        part.add(key);
        part.add(value);
        part.add(checkBox);
        part.add(delete);
        part.add(add);
        // add all components to header panel.
        header.add(part);
        // add all tabs to tabbed pane.
        tab.add(body, "Body");
        tab.add(header, "Header");
        tab.setBounds(0, 0, 500, 700);
        // all tabs to part 2.
        part2.add(tab);
    }

    /**
     * set header of part 3 with labels.
     */
    public void headerP3() {
        // new Jlabel with random text
        status = new JLabel("OK 600");
        time = new JLabel("1.612 s");
        // set fonts and size
        status.setFont(new Font("Arial", 14, 25));
        status.setForeground(Color.WHITE);
        time.setForeground(Color.WHITE);
        time.setFont(new Font("Arial", 14, 25));
        // new panel for labels.
        JPanel show = new JPanel();
        show.setLayout(new FlowLayout());
        show.add(status);
        show.add(time);
        //label size
        status.setBounds(25, 20, 150, 25);
        time.setBounds(50, 20, 150, 25);
        show.setBounds(0, 0, 500, 50);
        show.setBackground(Color.BLACK);
        // add to part3
        part3.add(show);
    }

    /**
     *add tabbed pane to part3
     * add body message field.
     * add header fields.
     */
    public void bodyP3() {
        // for body message
        JPanel body = new JPanel();
        // set color
        body.setBackground(Color.BLACK);
        // for header panel
        JPanel headaer = new JPanel();
        headaer.setBackground(Color.BLACK);
        headaer.setLayout(new BoxLayout(headaer, BoxLayout.Y_AXIS));
        headaer.setLayout(null);
        //body message field
        JTextField textField = new JTextField();
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
        textField.setPreferredSize(new Dimension(450, 700));
        JScrollPane scrollPane = new JScrollPane(textField);
        // add text field to body
        body.add(scrollPane);
        // for add raw in phase 2
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
        // add body to tab
        tab.add(body, "body");
        tab.setBounds(5, 50, 500, 900);
        // set tab color
        tab.setBackground(Color.BLACK);
        tab.setForeground(Color.WHITE);

        //set header panel
        JPanel head = new JPanel();
        head.setBackground(Color.BLACK);
        //for name and value fields
        JLabel name = new JLabel("name");
        name.setBackground(Color.BLACK);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Arial", 14, 20));
        JLabel value = new JLabel("value");
        value.setBackground(Color.BLACK);
        value.setForeground(Color.WHITE);
        value.setFont(new Font("Arial", 14, 20));
        //
//        name.setPreferredSize(new Dimension(100,100));
        name.setBounds(60, 20, 200, 25);
        value.setBounds(310, 20, 200, 25);
        //set value field
        head.add(name);
        head.add(value);
        // for value and key text field phase 2
//        JTextField name1 = new JTextField("key");
//        name1.setFont(new Font("Arial", 14, 20));
//        name1.setEditable(false);
//        name1.setBounds(60, 80, 150,h 25);
//        //
//        JTextField value1 = new JTextField("value");
//        value1.setEditable(false);
//        value1.setFont(new Font("Arial", 14, 20));
//        value1.setBounds(310, 80, 150, 25);
        //reminding to add p3


        //add to header panel
        headaer.add(head);
        head.setBounds(0, 0, 500, 50);
        // phase 2
//        headaer.add(name1);
//        headaer.add(value1);
        //coppy to cliboard button
        JButton copy = new JButton("Copy to Clipboard");
        //add button
        headaer.add(copy);
        //set copy button at the end of panel
        int x = headaer.getComponentCount() / 2;
        copy.setBounds(300, 75 * x, 150, 25);
        //add header to tab
        tab.add(headaer, "Header");
        // add tab to part 3
        part3.add(tab);
    }

    /**
     * method to set menu bar.
     */
    public void menu() {
        // new menu bar
        JMenuBar menuBar = new JMenuBar();
        // set menus
        JMenu application = new JMenu("Application");
        JMenu help = new JMenu("Help");
        JMenu view = new JMenu("View");
        //add option item and action
        JMenuItem option = new JMenuItem("Option");
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option();
            }
        });
        option.setMnemonic('O');
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        // add exit menu and action
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
        // toggle to full screen
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
        // dull screen actions
        full.setMnemonic('F');
        full.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
        //toggle side bar
        JMenuItem side = new JMenuItem("Toggle Sidebar");
        side.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
        side.setMnemonic('T');
        side.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.CTRL_MASK));
        //add to view menu
        view.add(full);
        view.add(side);
        // about panel
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });

        about.setMnemonic('A');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
        // add help menu
        // complete in phase 2
        JMenuItem help1 = new JMenuItem("Help");
        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });
        help1.setMnemonic('H');
        help1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        //add to help menu
        help.add(about);
        help.add(help1);
        //add all components in menu bar.
        menuBar.add(application);
        menuBar.add(view);
        menuBar.add(help);
        // set to jframe
        setJMenuBar(menuBar);
    }

    /**
     * add all part in jframe.
     */
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

    /**
     * add new panel to header in part 2.
     * @param header which panel want to add onn it.
     * @return Jpanel which make to add header panel
     */
    public JPanel add(JPanel header) {
        // make panel add components
        JPanel part = new JPanel();
        part.setBackground(Color.BLACK);
        part.setForeground(Color.WHITE);
        part.setLayout(new FlowLayout());
        JTextField key = new JTextField("key");
        key.setPreferredSize(new Dimension(100, 25));
        key.setEditable(true);
        JTextField value = new JTextField("value");
        value.setPreferredSize(new Dimension(100, 25));
        value.setEditable(true);
        key.setBackground(Color.GRAY);
        key.setForeground(Color.WHITE);
        value.setBackground(Color.GRAY);
        value.setForeground(Color.WHITE);
        // make new check box with action
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
        // add button with it action
        JButton delete = new JButton("\uD83D\uDDD1");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.remove(part);
            }
        });
        // add button want too add new panels
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.add(add(header));
            }
        });
        add.setPreferredSize(new Dimension(60, 25));
        delete.setPreferredSize(new Dimension(49, 25));
        // add all parts to panel want to add header.
        part.add(key);
        part.add(value);
        part.add(checkBox);
        part.add(delete);
        part.add(add);
        // return Jpanel.
        return part;
    }

    /**
     * add two texfield to header in part2
     * @param s text of first field
     * @param s1 text of seconf field.
     * @return JPanel added to header of part3
     */
    public JPanel addP3(String s, String s1) {
        JPanel part = new JPanel();
        part.setLayout(new FlowLayout());
        // name field
        JTextField name1 = new JTextField(s + "          ");
        name1.setFont(new Font("Arial", 14, 20));
        name1.setEditable(false);
        name1.setBounds(60, 80, 150, 25);
        // value field
        JTextField value1 = new JTextField(s1);
        value1.setEditable(false);
        value1.setFont(new Font("Arial", 14, 20));
        value1.setBounds(310, 80, 150, 25);
        // add to jpanel
        part.add(name1);
        part.add(value1);

        return part;
    }

    /**
     * open new panel when click option
     * make new JFrame.
     */
    public void option() {
        // two check box
        JCheckBox direct = new JCheckBox("Follow Redirect");
        JCheckBox close = new JCheckBox("Close on system tray");
        if (tray) {
            close.setSelected(true);
        } else
            close.setSelected(false);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // colse on System tray
                JCheckBox test = (JCheckBox) e.getSource();
                if (test.isSelected()) {
                    tray = true;
                } else {
                    tray = false;
                }
            }
        });
        // Jpanel two add two check box
        JPanel panel = new JPanel();
        panel.add(direct);
        panel.add(close);
        // add all component to option panel.
        optionPanel.add(panel);
        optionPanel.pack();
        // show panel
        optionPanel.setVisible(true);
    }

    /**
     * make new frame.
     * show author information.
     */
    public void about() {
        JFrame info = new JFrame("ABOUT");
        JPanel infoP = new JPanel();
        infoP.setLayout(new BoxLayout(infoP, BoxLayout.Y_AXIS));
        // author info
        JTextField infoField = new JTextField("               Author: Mohammad choupan");
        JTextField email = new JTextField("              E-Mail: mohamadkhoee@yahoo.com");
        JTextField studentNum = new JTextField("              Student NUM : 9831125");
        infoField.setEditable(false);
        email.setEditable(false);
        studentNum.setEditable(false);
        // add to new panel
        infoP.add(infoField, CENTER_ALIGNMENT);
        infoP.add(email, CENTER_ALIGNMENT);
        infoP.add(studentNum, CENTER_ALIGNMENT);
        info.add(infoP);
        // add to JFrame.
        info.pack();
        info.setVisible(true);
    }

    /**
     * help frame.
     * make new frame
     */
    // complete in phase 3
    public void help() {
        JFrame help = new JFrame();
        help.setVisible(true);
    }

}