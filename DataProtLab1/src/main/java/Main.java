import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<String, String> information = new HashMap<String, String>();

    public static void main(String[] args) {

        /*File file = new File("C:/Users/Мария/Desktop/users.bin");
        String str1 = "klose 2536 \nloooop 6358 \nkloklo 00000";
        byte[] all = str1.getBytes();
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(all);
            out.flush();
            out.close();

            readFromFile(file);
            information.forEach( (name, pass) -> {
                System.out.println(name + " " + pass);
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }*/
        creatingGUI();

    }

    private static void readFromFile(File file) throws IOException {
        InputStream inpt = new FileInputStream(file);
        byte[] allBytes = new byte[(int) file.length()];
        inpt.read(allBytes);
        String str = new String(allBytes, "UTF-8");
        String[] strPassed= str.split("\\n");
        for (String s : strPassed) {
            String[] ss = s.split(" ");
            information.put(ss[0], ss[1]);
        }
        inpt.close();
    }

    private static void creatingGUI() {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        MainForm form = new MainForm();
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
        frame.setTitle("Вход в программу");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(form.getMainPanel(), "Выполнила: Кружкова Мария А-05-17.\n" +
                        "Вариант 9. Ограничения: наличие букв, цифр и знаков препинания.", "О программе", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(form.getMainPanel());
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                List<User> users = form.getUsers();
                File file = form.getFile();
                String space = " ";
                String enter = "\n";
                char[] stri;
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    users.forEach(it -> {
                        //String str = it.getName() + " ";
                        try {
                            byte[] name = it.getName().getBytes("UTF-8");
                            out.write(name);
                            out.write(space.getBytes("UTF-8"));
                            out.write(it.fromCharToByteArray());
                            out.write(space.getBytes("UTF-8"));
                            out.write(it.getIsBlocked().toString().getBytes("UTF-8"));
                            out.write(space.getBytes("UTF-8"));
                            out.write(it.getRestriction().toString().getBytes("UTF-8"));
                            out.write(enter.getBytes("UTF-8"));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    out.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                form.getUserName().setText("");
                form.getPassword().setText("");
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
