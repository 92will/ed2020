package com.company;

public class Functions {

    // inicio das funçoes de "ls"

    // imprime *folder -> next -> next ate chegar em null
    public static void printFolders(Struct folder) {

        while (folder != null) {
            System.out.print(folder.getName() + ":" + folder.getType() + ":" + folder.getPath() + ":" + folder.getLevel() + "    ");
            folder = folder.getNext();
        }
        System.out.println();
    }

    // imprime subpastas de uma pasta
    public static void ls(Struct folder) {

        if (folder.getChild() != null) {
            printFolders(folder.getChild());
        } else {
            System.out.print("");
        }
    }

    // imprime recursivamente subpastas de uma pasta
    public static void lsR(Struct folder) {

        if (folder.getChild() != null) { // folder
            ls(folder); // a -> b -> c
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

        folder = folder.findFolderByPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            ls(folder);
        }
    }

    public static void lsRPath(String path, Struct folder) {

        folder = folder.findFolderByPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            lsR(folder);
        }
    }

    // inicio das funçoes de search

    // imprime o nome do diretório que contém a chave se houver, caso contrário retorna 0
    public static void printFound(String key, Struct folder) {

        boolean res = false;

        while (folder != null) {
            if (folder.getName().contains(key)) {
                System.out.print(folder.getPath() + "  ");
                res = true;
            }
            folder = folder.getNext();
        }
        if (res) System.out.println();
    }

    public static void search(String key, Struct folder) {

        if (folder.getChild() != null) {
            printFound(key, folder.getChild());
        } else {
            System.out.println("Nada encontrado no diretorio " + folder.getName());
        }
    }

    public static void searchR(String key, Struct folder) {

        if (folder.getChild() != null) { // folder
            search(key, folder); // a -> b -> c
            folder = folder.getChild(); // folder = a
            searchR(key, folder);
        } else if (folder.getNext() != null) {
            folder = folder.getNext();
            searchR(key, folder);
        } else {
            System.out.print("");
        }
    }

    public static void searchPath(String key, String path, Struct folder) {

        folder = folder.findFolderByPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            search(key, folder);
        }
    }

    public static void searchRPath(String key, String path, Struct folder) {

        folder = folder.findFolderByPath(path, folder);
        if (folder == null) {
            System.out.println("Path nao encontrado.");
        } else {
            searchR(key, folder);
        }
    }


}
