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

    // inicio das funçoes de "ls"

    // imprime *folder -> next -> next ate chegar em null
    public static void printFolders(Struct folder) {
        while (folder != null) {
            System.out.print(folder.getName() + ":" + folder.getType() + ":" + folder.getPath() + " ");
            folder = folder.getNext();
        }
        System.out.println("");
    }

    // imprime subpastas de uma pasta
    public static void ls(Struct folder) {
        if (folder.getChild() != null) {
            Functions.printFolders(folder.getChild());
        } else {
            System.out.print("");
        }
    }

    // imprime recursivamente subpastas de uma pasta
    public static void lsR(Struct folder) {
        if (folder.getChild() != null) { // folder
            Functions.ls(folder); // a -> b -> c
            folder = folder.getChild(); // folder = a
            lsR(folder);
        } else if (folder.getNext() != null) {
            folder = folder.getNext();
            lsR(folder);
        } else {
            System.out.print("");
        }
    }

    public static void lsPath(String path, Struct folder) {
        folder = Functions.positionPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            Functions.ls(folder);
        }
    }

    public static void lsRPath(String path, Struct folder) {
        folder = Functions.positionPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            Functions.lsR(folder);
        }
    }

    // inicio das funçoes de search

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
}
