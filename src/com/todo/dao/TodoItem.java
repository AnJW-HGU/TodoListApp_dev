package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String category;
    private String title;
    private String desc;
    private String due_date;
    private String current_date;
    private int is_completed;


    public TodoItem(String cate, String title, String desc, String due_date){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date=simpleDateFormat.format(new Date());
    }
    
    public TodoItem(String cate, String title, String desc, String due_date, String current_date, int is_completed){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
        this.is_completed = is_completed;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public int getIsCompleted() {
    	return is_completed;
    }
    
    public void setIsCompleted(int isCompleted) {
    	this.is_completed = isCompleted;
    }
    
    public String toString() {
    	if (is_completed == 1)
    		return id + ". [" + category + "] " + title + "[V] - " + desc + " - " + due_date + " - " + current_date;
    	else
    		return id + ". [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    }
    
    public String toSaveString() {
    	return category + "##" + title + "##" + is_completed + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
}
