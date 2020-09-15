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

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public Struct getChild() {
        return child;
    }

    public Struct getNext() {
        return next;
    }

    // criação do diretório root
    public void createRoot() {

        this.name = "/";
        this.path = "/";
        this.type = "folder";
    }

    // encontra a ordem do elemento -> a < b
    public Struct findOrderByName(String name, Struct folder) {

        // a -> current:b -> new:c -> next:d -> null
        while (folder.next != null && folder.next.name.compareTo(name) < 0) {
            folder = folder.next;
        }

        return folder;
    }

    // encontra a posição pelo path
    public Struct findFolderByPath(String path, Struct folder) {

        boolean loop = true;

        while (loop) {
            if (path.equals(folder.path)) { // path:/a/b == folder.path:/a/b
                loop = false;
            } else {
                if (path.contains(folder.path) && folder.child != null) { // path:/a/b contem folder.path:/a
                    folder = folder.child;
                } else {
                    if (folder.next != null) {
                        folder = folder.next;
                    } else {
                        folder = null;
                        loop = false;
                    }
                }
            }
        }

        return folder;
    }

    public boolean checkEqualName(String name, Struct folder) {
        return folder.next.name.equals(name); // retorna true
    }

    // add alguns atributos e direciona os ponteiros
    public void setAtt(String name, Struct root, Struct newData) {

        Struct current;

        newData.name = name;

        if (root.path.equals("/")) {
            newData.path = root.path + name;
        } else {
            newData.path = root.path + "/" + name;
        }

        if (root.child == null) {
            root.child = newData;
        } else {
            current = root.child;
            if (current.next == null) {
                if (current.name.compareTo(name) > 0) {
                    // root -> *a -> b -> null
                    root.child = newData;
                    newData.next = current;
                } else {
                    // root -> a -> *b -> null
                    current.next = newData;
                }
            } else {
                current = findOrderByName(name, current);
                // a -> b *c -> d -> null
                newData.next = current.next;
                current.next = newData;
            }
        }
    }

    // cria um novo diretório ou arquivo
    public void createData(String type, String path, String name, Struct rootFolder) {

        Struct newData = new Struct();

        if (type.equals("mkdir")) {
            newData.type = "folder";
        } else {
            newData.type = "file";
        }

        if (!path.equals("noPath")) {
            rootFolder = findFolderByPath(path, rootFolder);

            if (rootFolder != null && rootFolder.type.equals("folder")) {
                newData.setAtt(name, rootFolder, newData);
            } else {
                System.out.println("Arquivo não é um diretório.");
            }
        } else {
            newData.setAtt(name, rootFolder, newData);
        }
    }
}
