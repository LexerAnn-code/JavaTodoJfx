package sample.Model;

public class Task {
    public String dateCreated;
    public String description;
    public String task;
    public int userid;
    public int taskid;

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Task(String dateCreated, String description, String task, int userid) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.task = task;
        this.userid = userid;
    }

    public Task() {

    }
}