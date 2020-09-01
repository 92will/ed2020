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
        this.name = "/";
        this.path = "/";
        this.type = "folder";
    }

    public void addAtt(String name, Struct root, Struct newData) {

        Struct current;

        newData.setName(name);

        if (root.getPath().equals("/")) {
            newData.setPath(root.getPath() + name);
        } else {
            newData.setPath(root.getPath() + "/" + name);
        }

        if (root.getChild() == null) {
            root.setChild(newData);
        } else {
            current = root.getChild();
            if (current.getNext() == null) {
                if (current.getName().compareTo(name) > 0) {
                    // root -> *a -> b -> null
                    root.setChild(newData);
                    newData.setNext(current);
                } else {
                    // root -> a -> *b -> null
                    current.setNext(newData);
                }
            } else {
                current = Functions.positionSub(name, current);
                // a -> b *c -> d -> null
                newData.setNext(current.getNext());
                current.setNext(newData);
            }
        }
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
