import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AdminStart {
    @Getter
    private JPanel mainPanel;
    private JButton changePass;
    private JButton addUser;
    private JButton checkUsersList;
    private JButton cancel;
    private List<User> users;
    private User admin;
    private MainForm form;
    WorkWithData work = new WorkWithData() {
        @Override
        void addActionListeners() {
            changePass.addActionListener(new Action() {
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
                    if (!admin.getIsBlocked()) {
                        JFrame frame = new JFrame();
                        frame.setSize(400, 400);
                        JPanel change = new changePassword(admin).getMainPanel();
                        frame.add(change);
                        Font font = new Font("Verdana", Font.PLAIN, 11);
                        JMenuBar menu = new JMenuBar();
                        menu.setFont(font);
                        JMenu spravkaMenu = new JMenu();
                        spravkaMenu.setText("Справка");
                        spravkaMenu.setFont(font);
                        JMenuItem about = new JMenuItem();
                        about.setText("О программе");
                        about.setFont(font);
                        spravkaMenu.add(about);
                        menu.add(spravkaMenu);
                        frame.setJMenuBar(menu);
                        frame.setTitle("Изменение пароля");
                        about.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(change, "Выполнила: Кружкова Мария А-05-17.\n" +
                                        "Вариант 9. Ограничения: наличие букв, цифр и знаков препинания.", "О программе", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });

                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            });
            addUser.addActionListener(new Action() {
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
                    if (!admin.getIsBlocked()) {
                        JFrame frame = new JFrame();
                        frame.setSize(400, 400);
                        JPanel adminWindow = new AdminWindow(form).getMainPanel();
                        frame.add(adminWindow);
                        Font font = new Font("Verdana", Font.PLAIN, 11);
                        JMenuBar menu = new JMenuBar();
                        menu.setFont(font);
                        JMenu spravkaMenu = new JMenu();
                        spravkaMenu.setText("Справка");
                        spravkaMenu.setFont(font);
                        JMenuItem about = new JMenuItem();
                        about.setText("О программе");
                        about.setFont(font);
                        spravkaMenu.add(about);
                        menu.add(spravkaMenu);
                        frame.setJMenuBar(menu);
                        frame.setTitle("Добавление пользователя");
                        about.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(adminWindow, "Выполнила: Кружкова Мария А-05-17.\n" +
                                        "Вариант 9. Ограничения: наличие букв, цифр и знаков препинания.", "О программе", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });

                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            });

            checkUsersList.addActionListener(new Action() {
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
                    if (!admin.getIsBlocked()) {
                        JFrame frame = new JFrame();
                        frame.setSize(400, 400);
                        JPanel usersList = new UsersList(form).getMainPanel();
                        frame.add(usersList);
                        Font font = new Font("Verdana", Font.PLAIN, 11);
                        JMenuBar menu = new JMenuBar();
                        menu.setFont(font);
                        JMenu spravkaMenu = new JMenu();
                        spravkaMenu.setText("Справка");
                        spravkaMenu.setFont(font);
                        JMenuItem about = new JMenuItem();
                        about.setText("О программе");
                        about.setFont(font);
                        spravkaMenu.add(about);
                        menu.add(spravkaMenu);
                        frame.setJMenuBar(menu);
                        frame.setTitle("Список пользователей");
                        about.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(usersList, "Выполнила: Кружкова Мария А-05-17.\n" +
                                        "Вариант 9. Ограничения: наличие букв, цифр и знаков препинания.", "О программе", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });

                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
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

    public AdminStart(MainForm form, User admin) {
        this.users = form.getUsers();
        this.admin = admin;
        this.form=form;
        work.addActionListeners();
    }
}
