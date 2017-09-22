package com.example.arsenko.test1;


public class RecyclerItem {

    private String title;

    private String description;

    public RecyclerItem(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
