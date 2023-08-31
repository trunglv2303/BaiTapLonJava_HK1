package Models;

public class Notification<T> {
    private Boolean check;
    private String string;
    private T data;
    public Notification() {
    }

    public Notification(Boolean check, String string, T data) {
        this.check = check;
        this.string = string;
        this.data = data;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "check=" + check +
                ", string='" + string + '\'' +
                ", data=" + data +
                '}';
    }
}
