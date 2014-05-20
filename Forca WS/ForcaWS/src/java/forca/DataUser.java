package forca;

public class DataUser {

    private String userName;
    private String word;
    private DataThrow dThrow;
    private String id;
    private boolean started;

    public DataUser(String name, String id, String word) {
        this.userName = name;
        this.id = id;
        this.word = word;
        this.started = false;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setdThrow(DataThrow dt) {
        this.dThrow = dt;
    }

    public DataThrow getdThrow() {
        return dThrow;
    }

    public String getUserName() {
        return this.userName;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    
}