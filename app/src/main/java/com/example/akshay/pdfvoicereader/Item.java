package com.example.akshay.pdfvoicereader;

/**
 * Created by akshay on 9/4/17.
 */

public class Item implements Comparable<Item>{
    private String name;
    private String path;
    private String image;

    public Item(String n, String p, String img)
    {
        name = n;
        path = p;
        image = img;

    }
    public String getName()
    {
        return name;
    }
    public String getPath()
    {
        return path;
    }
    public String getImage() {
        return image;
    }

    public int compareTo(Item o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}
