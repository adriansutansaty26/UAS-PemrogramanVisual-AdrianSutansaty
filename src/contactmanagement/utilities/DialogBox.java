package contactmanagement.utilities;

import javax.swing.JOptionPane;

public class DialogBox {
    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static int showOptionDialog(String title, String message, int optionType, int messageType) {
        return JOptionPane.showOptionDialog(null, message, title, optionType, messageType, null, null, null);
    }
}
