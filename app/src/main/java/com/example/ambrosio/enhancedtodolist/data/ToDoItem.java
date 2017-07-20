package com.example.ambrosio.enhancedtodolist.data;

/**
 * Created by Ambrosio on 7/18/2017.
 */

public class ToDoItem {
    private String description;
    private String priority;
    private String dueDate;
    private String isdone;

    public ToDoItem(String description, String priority,String isdone,String dueDate) {
        this.description = description;
        this.priority=priority;
        this.isdone=isdone;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {return priority;}

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIsDone() {return isdone;}

    public void setIsDone(String isdone) {
        this.isdone = isdone;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
