package com.company;

public class Functions {

    public static Struct position(String name, Struct currentFolder) {
        // a -> current:b -> new:c -> next:d -> null
        while (currentFolder.getNext() != null && currentFolder.getNext().getName().compareTo(name) < 0) {
            currentFolder = currentFolder.getNext();
        }
        return currentFolder;
    }

    public static void createData(String name, String opt, Struct rootFolder) {
        Struct newData = new Struct();
        newData.setName(name);
        newData.setPath(rootFolder.getPath() + name);
        if (opt.equals("mkdir")) {
            newData.setType("folder");
        } else {
            newData.setType("file");
        }

        Struct current;

        if (rootFolder.getChild() == null) {
            rootFolder.setChild(newData);
        } else {
            current = rootFolder.getChild();
            if (current.getNext() == null) {
                if (current.getName().compareTo(name) > 0) {
                    // root -> *a -> b -> null
                    rootFolder.setChild(newData);
                    newData.setNext(current);
                } else {
                    // root -> a -> *b -> null
                    current.setNext(newData);
                }
            } else {
                current = position(name, current);
                // a -> b *c -> d -> null
                newData.setNext(current.getNext());
                current.setNext(newData);
            }
        }
    }

    public static void search(String name, Struct current) {
        if (current.getChild() != null) {
            current = current.getChild();
            while (current != null) {
                if (current.getName().equals(name)) {
                    System.out.println(current.getPath());
                }
                current = current.getNext();
            }
        } else {
            System.out.println("Nada encontrado.");
        }
    }

    public static void ls(Struct current) {
        if (current.getChild() != null) {
            current = current.getChild();
            while (current != null) {
                System.out.print(current.getName() + ":" + current.getType() + " ");
                current = current.getNext();
            }
            System.out.println("");
        } else {
            System.out.print("");
        }
    }
}
