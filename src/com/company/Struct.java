package com.company;

public class Struct {
    private String name;
    private String path;
    private String type;
    private Struct child;
    private Struct next;

    public Struct() {
        this.child = null;
        this.next = null;
    }

    public void createRoot() {
        this.name = "root";
        this.path = "/";
        this.type = "folder";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Struct getChild() {
        return child;
    }

    public void setChild(Struct child) {
        this.child = child;
    }

    public Struct getNext() {
        return next;
    }

    public void setNext(Struct next) {
        this.next = next;
    }
}
