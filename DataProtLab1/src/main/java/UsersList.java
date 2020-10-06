import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class UsersList {
    private JTextField name;
    private JCheckBox bloking;
    private JCheckBox restriction;
    private JButton next;
    private JButton prev;
    private JButton save;
    private JButton cancel;
    @Getter
    private JPanel mainPanel;
    private MainForm form;
    private List<User> users;
    private int count = 0;
    private WorkWithData work = new WorkWithData() {
        @Override
        void addActionListeners() {
            next.addActionListener(new Action() {
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
                    if (count<users.size()) {
                        //if (count==0) {count++;}
                        count++;
                        if (count==users.size()) {count-=1; next.setEnabled(false); }
                        else {
                            name.setText(users.get(count).getName());
                            bloking.setSelected(users.get(count).getIsBlocked());
                            restriction.setSelected(users.get(count).getRestriction());
                        }
                        //if (count>=users.size()-1) {
                            //if (count==users.size()-1) { next.setEnabled(false); }


                       // }
                    }
                    if (!prev.isEnabled()) { prev.setEnabled(true); }
                }
            });

            prev.addActionListener(new Action() {
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
                    if (count>=0) {
                        count--;
                        if (count==-1) { count+=1; prev.setEnabled(false); }
                        else {
                            //if (count==users.size()-1) {count--;}
                            name.setText(users.get(count).getName());
                            bloking.setSelected(users.get(count).getIsBlocked());
                            restriction.setSelected(users.get(count).getRestriction());
                        }
                        /*count--;
                        if (count<=0) {
                            prev.setEnabled(false);
                            if (count==0) {count+=1;}
                            if (count==-1) {count+=2;}
                            count++;*/


                        }
                        if (!next.isEnabled()) { next.setEnabled(true); }

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

            save.addActionListener(new Action() {
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
                    User us = users.get(count);
                    us.setIsBlocked(bloking.isSelected());
                    us.setRestriction(restriction.isSelected());
                    JOptionPane.showMessageDialog(mainPanel, "Изменения сохранены", "Сохранение",
                            JOptionPane.PLAIN_MESSAGE);
                }
            });

        }
    };

    public UsersList(MainForm form) {
        this.form=form;
        users = form.getUsers();
        name.setText(users.get(0).getName());
        bloking.setSelected(users.get(0).getIsBlocked());
        restriction.setSelected(users.get(0).getRestriction());
        prev.setEnabled(false);
        if (users.size()==1) { next.setEnabled(false); }
        work.addActionListeners();
    }
}
