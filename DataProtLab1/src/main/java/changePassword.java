import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class changePassword {
    private User user;
    @Getter
    private JPanel MainPanel;
    private JButton OK;
    private JPasswordField newPassword;
    private JButton cancel;
    private JPasswordField acceptPassword;
    private WorkWithData work = new WorkWithData() {
        @Override
        void addActionListeners() {
            OK.addActionListener(new Action() {
                @Override
                public Object getValue(String key) {
                    return null;
                }

                @Override
                public void putValue(String key, Object value) {

                }

                @Override
                public void setEnabled(boolean b) {

                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public void addPropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void removePropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (newPassword.getText().equals(acceptPassword.getText())) {
                        char[] newPass = newPassword.getText().toCharArray();
                        if (user.getRestriction()) {
                            if (!checkPassOnRestriction(newPass)) {
                                int res = JOptionPane.showConfirmDialog(MainPanel, "Ваш пароль не соответствует ограничениям." +
                                                "Хотите ввести новый пароль?",
                                        "Ошибка", JOptionPane.YES_NO_OPTION);
                                if (res == JOptionPane.NO_OPTION) { work.closingFrame(OK);}
                            }
                            else {
                                if (newPassword.getText().contains(" ")) {
                                    int result = JOptionPane.showConfirmDialog(MainPanel, "Ваш пароль содержит недопустимый символ: пробел.",
                                            "Ошибка", JOptionPane.YES_NO_OPTION);
                                    if (result == JOptionPane.NO_OPTION) { work.closingFrame(OK);}
                                }
                                else {
                                    user.setPassword(newPass);
                                    JOptionPane.showMessageDialog(MainPanel, "Пароль успешно сменен", "Смена пароля",
                                            JOptionPane.PLAIN_MESSAGE);
                                }
                                work.closingFrame(OK);
                            }
                        }
                        else {
                            user.setPassword(newPass);
                            JOptionPane.showMessageDialog(MainPanel, "Пароль успешно сменен", "Смена пароля",
                                    JOptionPane.PLAIN_MESSAGE);
                            work.closingFrame(OK);
                        }
                    }
                    else{
                            JOptionPane.showMessageDialog(MainPanel, "Введенные пароли не совпадают. Повторите попытку.",
                                    "Смена пароля", JOptionPane.ERROR_MESSAGE);

                        }

                }
            });
            cancel.addActionListener(new Action() {
                @Override
                public Object getValue(String key) {
                    return null;
                }

                @Override
                public void putValue(String key, Object value) {

                }

                @Override
                public void setEnabled(boolean b) {

                }

                @Override
                public boolean isEnabled() {
                    return false;
                }

                @Override
                public void addPropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void removePropertyChangeListener(PropertyChangeListener listener) {

                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    work.closingFrame(cancel);
                }
            });
        }
    };

    public changePassword(User user) {
        this.user=user;
        work.addActionListeners();
    }

    private Boolean checkPassOnRestriction(char[] pass) {
        Boolean hasLetter = false;
        Boolean hasDig = false;
        Boolean hasCom = false;
        char[] prep ={'.',',','-',':',';','?','!'};
        String punct = ".,-:;&!";
        //pass.matches("")
        for (char ch : pass) {
            if (Character.isLetter(ch)) { hasLetter = true; }
            if (Character.isDigit(ch)) { hasDig = true; }
            if (punct.contains(ch+"")) { hasCom = true;}
        }
        return hasCom && hasDig && hasLetter;
    }
}
