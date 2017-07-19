package com.example.administrator.sharedprefe;

/**
 * Created by Administrator on 8/2/2016.
 */
public class Actors {
    private String id;
    private String url;
    private String authortitle;
    private String authorcontent;
    private String authorimage;


    public void setIsRowSelected(boolean isRowSelected) {
        this.isRowSelected = isRowSelected;
    }

    public boolean getIsRowSelected() {
        return isRowSelected;
    }

    private String name;
    private String description;
    private String image;
    private boolean isRowSelected;

    public Actors() {
        // TODO Auto-generated constructor stub
    }

    public Actors(String name, String description, String image) {
        super();
        this.name = name;
        this.description=description;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getid() {
        return id;
    }

    public String getUrl() {
        return url;
    }


}
