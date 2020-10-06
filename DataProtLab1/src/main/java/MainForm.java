import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainForm {
    private final String path = "./users.bin";
    private Map<String, String> information = new HashMap<String, String>();
    @Setter @Getter
    private List<User> users = new ArrayList<>();
    @Getter
    File file = new File(path);
    private final String ADMIN_NAME = "ADMIN";
    private MainForm thisClass = this;
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
                    String us = userName.getText().trim();
                    String pass = password.getText();

                    if (!us.isEmpty()) {
                        User newUser = new User(us, pass.toCharArray(), false, false);
                        if (users.contains(newUser)) {
                            User userInList = users.get(users.indexOf(newUser));
                            if (equalsPass(newUser.getPassword(), userInList.getPassword()) || Character.isSpaceChar(userInList.getPassword()[0])) {
                                //TODO: открываем другое окно
                                if (!us.equals(ADMIN_NAME)) {
                                    if (!userInList.getIsBlocked()) {
                                        createChangePassword(userInList);
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(MainPanel, "Пользователь заблокирован.",
                                                "Вход", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else {
                                    if (Character.isSpaceChar(userInList.getPassword()[0])) {
                                        createChangePassword(userInList);
                                    }
                                    else {
                                        createAdminStart(userInList);
                                    }
                                }
                            }
                            else {
                                int tries = userInList.getTries();
                                if (tries>=3) {
                                    closingFrame(OK);
                                }
                                else {
                                    JOptionPane.showMessageDialog(MainPanel, "Неверный пароль. Попробуйте снова. " +
                                                    "Осталось попыток: " + (2 - tries),
                                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                                    password.setText("");
                                    userInList.setTries(++tries);
                                }
                            }
                        }
                        else {
                            //такого пользователя не существует
                            int result = JOptionPane.showConfirmDialog(MainPanel, "Данного пользователя не существует. Хотите попробовать снова?",
                                    "Ошибка", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                userName.setText("");
                                password.setText("");
                            }
                            else {
                                if (result == JOptionPane.NO_OPTION)
                                {
                                    closingFrame(OK);
                                }
                            }

                        }
                    }
                    else {
                        // поле пароля или пользователя пусто
                        JOptionPane.showMessageDialog(MainPanel, "Введите имя пользователя.",
                                "Внимание", JOptionPane.WARNING_MESSAGE);
                        userName.setText("");
                        password.setText("");
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
                    closingFrame(cancel);
                }
            });

        }
    };
    private JTextField userName;
    private JPasswordField password;
    private JButton OK;
    private JButton cancel;
    private JPanel MainPanel;

    public MainForm() {
        if (file.exists() && !file.isDirectory())
        {
            try {
                String f = file.getAbsolutePath();
                readFromFile(file);
            }
            catch (IOException ex){
                JOptionPane.showMessageDialog(MainPanel, "Ошибка чтения файла. ",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        else {
            try {
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                String space = " ";
                String enter = "\n";
                User admin = new User("ADMIN", new char[]{(char)32}, false, false);
                out.write(admin.getName().getBytes("UTF-8"));
                out.write(space.getBytes("UTF-8"));
                out.write(admin.fromCharToByteArray());
                out.write(space.getBytes("UTF-8"));
                out.write(admin.getIsBlocked().toString().getBytes("UTF-8"));
                out.write(space.getBytes("UTF-8"));
                out.write(admin.getRestriction().toString().getBytes("UTF-8"));
                out.write(enter.getBytes("UTF-8"));
                out.close();
                readFromFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        work.addActionListeners();
    }

    /*private Boolean equalsPass(char[] pass1, char[] pass2) {
        if (pass1.length!=pass2.length) return false;
        for (int i = 0; i < pass1.length; i++) {
            if (pass1[i]!=pass2[i]) return false;
        }
        return true;
    }*/
    /*private void closingFrame(JButton button) {
        Container frame = button.getParent();
        do
            frame = frame.getParent();
        while (!(frame instanceof JFrame));
        ((JFrame) frame).dispose();
    }*/
    private void readFromFile(File file) throws IOException {
        InputStream inpt = new FileInputStream(file);
        byte[] allBytes = new byte[(int) file.length()];
        inpt.read(allBytes);
        String str = new String(allBytes, "UTF-8");
        String[] strPassed= str.split("\\n");
        for (String s : strPassed) {
            String[] ss = s.split(" ");
            if (ss.length==5 && ss[1].equals("!") && ss[2].equals("!")) {
                users.add(new User(ss[0].trim(), new char[]{(char)32}, ss[3].trim().equals("true"), ss[4].trim().equals("true")));
            }
            else {
                //information.put(ss[0], ss[1]);
                users.add(new User(ss[0].trim(), ss[1].toCharArray(), ss[2].trim().equals("true"), ss[3].trim().equals("true")));
            }
        }
        inpt.close();
    }

    private void createChangePassword(User us) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        JPanel change = new changePassword(us).getMainPanel();
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

    private void createAdminStart (User us) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        JPanel admin = new AdminStart(thisClass, us).getMainPanel();
        frame.add(admin);
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
                JOptionPane.showMessageDialog(admin, "Выполнила: Кружкова Мария А-05-17.\n" +
                        "Вариант 9. Ограничения: наличие букв, цифр и знаков препинания.", "О программе", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JTextField getUserName() {
        return userName;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JButton getOK() {
        return OK;
    }

    public JButton getCancel() {
        return cancel;
    }


}
