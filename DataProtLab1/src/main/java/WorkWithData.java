import javax.swing.*;
import java.awt.*;

public abstract class WorkWithData {
    abstract void addActionListeners();
    public Boolean equalsPass(char[] pass1, char[] pass2) {
        if (pass1.length!=pass2.length) return false;
        for (int i = 0; i < pass1.length; i++) {
            if (pass1[i]!=pass2[i]) return false;
        }
        return true;
    }
    public void closingFrame(JButton button) {
        Container frame = button.getParent();
        do
            frame = frame.getParent();
        while (!(frame instanceof JFrame));
        ((JFrame) frame).dispose();
    }

}
