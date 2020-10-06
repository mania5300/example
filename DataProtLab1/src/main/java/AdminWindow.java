import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AdminWindow {
    @Getter
    private JPanel mainPanel;
    private JTextField user;
    private JPanel userName;
    private JCheckBox bloking;
    private JCheckBox restriction;
    private JButton OK;
    private JButton cancel;
    private MainForm form;
    @Getter
    private List<User> users;
    WorkWithData work = new WorkWithData() {
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
                    String name = user.getText();
                    String pass = " ";
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "Имя пользователя пусто",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        User us = new User(name, new char[]{(char)32}, bloking.isSelected(),
                                restriction.isSelected());
                        if (!form.getUsers().contains(us)) {
                            form.getUsers().add(us);
                            JOptionPane.showMessageDialog(mainPanel, "Пользователь добавлен.",
                                    "Ошибка", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(mainPanel, "Такой пользователь уже существует.",
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
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

    public AdminWindow(MainForm form) {
        work.addActionListeners();
        users = form.getUsers();
        this.form = form;
    }


}
