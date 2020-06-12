import javax.swing.*;
import java.awt.*;

/**
 * run insomnia app.
 * @author Mohammad
 * @version 1.00
 * @since 1398-2-23
 */
public class Main {

    public static void main(String[] args) {
        Insomnnia insomnnia=new Insomnnia("Insomnia");
//            try {
//                // Set cross-platform Java L&F (also called "Metal")
//                UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
//                for (UIManager.LookAndFeelInfo info : plafs) {
//                    System.out.println(info.getClassName());
//
//                }
////                UIManager.setLookAndFeel();
//
//            }
//            catch (UnsupportedLookAndFeelException e) {
//                // handle exception
//                System.out.println("1");
//            }
//            catch (ClassNotFoundException e) {
//                System.out.println("2");
//                // handle exception
//            }
//            catch (InstantiationException e) {
//                // handle exception
//                System.out.println("3");
//            }
//            catch (IllegalAccessException e) {
//                // handle exception
//                System.out.println("4");
//            }
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins",
                new Insets(50, 50, 50, 50));
        insomnnia.setVisible(true);
        insomnnia.pack();
        insomnnia.setResizable(true);
        insomnnia.setDefaultCloseOperation(3);
    }
}
