package database;

public class Task {
    private String username;
    private String time;
    private String backgroundColor;
    private String clockType;
    private String title;

    public Task(String username, String time, String backgroundColor, String clockType, String title) {
        this.username = username;
        this.time = time;
        this.backgroundColor = backgroundColor;
        this.clockType = clockType;
        this.title = title;
    }
    public Task(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getClockType() {
        return clockType;
    }

    public void setClockType(String clockType) {
        this.clockType = clockType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", clockType='" + clockType + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
