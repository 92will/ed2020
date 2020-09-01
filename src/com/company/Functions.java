package com.company;

public class Functions {

    // encontra a posiçao no mesmo nivel
    public static Struct positionSub(String name, Struct folder) {

        // a -> current:b -> new:c -> next:d -> null
        while (folder.getNext() != null && folder.getNext().getName().compareTo(name) < 0) {
            folder = folder.getNext();
        }

        return folder;
    }

    // encontra a posicao do path
    public static Struct positionPath(String path, Struct folder) {

        boolean loop = true;

        while (loop == true) {
            if (path.equals(folder.getPath())) { // path:/a/b == folder.path:/a/b
                loop = false;
            } else {
                if (path.contains(folder.getPath())) { // path:/a/b contem folder.path:/a
                    if (folder.getChild() != null) {
                        folder = folder.getChild();
                    }
                } else {
                    if (folder.getNext() != null) {
                        folder = folder.getNext();
                    } else {
                        folder = null;
                        loop = false;
                    }
                }
            }
        }

        return folder;
    }

    // add alguns atributos e direciona os ponteiros


    public static void createData(String type, String path, String name, Struct rootFolder) {

        Struct newData = new Struct();

        if (type.equals("mkdir")) {
            newData.setType("folder");
        } else {
            newData.setType("file");
        }

        if (!path.equals("noPath")) {
            rootFolder = positionPath(path, rootFolder);
            if (rootFolder != null && rootFolder.getType().equals("folder")) {
                newData.addAtt(name, rootFolder, newData);
            } else {
                System.out.println("Path esta errado ou nao e uma pasta.");
            }
        } else {
            newData.addAtt(name, rootFolder, newData);
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
                System.out.print(current.getName() + ":" + current.getType() + ":" + current.getPath() + " ");
                current = current.getNext();
            }
            System.out.println("");
        } else {
            System.out.print("");
        }
    }
}
