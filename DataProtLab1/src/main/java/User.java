import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter
    private String name;
    @Getter
    private char[] password;
    @Getter @Setter
    private Boolean isBlocked;
    @Getter @Setter
    private Boolean restriction; //ограничения
    @Getter @Setter
    private int tries; //попытки ввода пароля

    public User(String name, char[] password, Boolean isBlocked, Boolean restriction) {
        this.name = name;
        this.password = password;
        this.isBlocked = isBlocked;
        this.restriction = restriction;
        tries = 0;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((User)obj).getName());
    }

    public byte[] fromCharToByteArray() {
        if (password.length ==1 && Character.isSpaceChar(password[0])) { password = new char[]{'!', ' ', '!'}; }
        byte[] b = new byte[password.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) password[i];
        }
        return b;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    /*public String getPassword() {
        return password.toString();
    }*/
}
