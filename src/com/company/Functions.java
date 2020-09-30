package com.company;

public class Functions {

    // inicio das funçoes de "ls"

    // imprime *folder -> next -> next ate chegar em null
    public static void printFolders(Struct folder) {

        while (folder != null) {
            System.out.print(folder.getName() + "    ");
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

        if (folder.getChild() == null) {
            System.out.print("");
        }
        if (folder.getChild() != null) {
            System.out.println(folder.getPath() + ":");
            ls(folder);
            lsR(folder.getChild());
        }
        if (folder.getNext() != null) {
            lsR(folder.getNext());
        }
    }

    public static void lsRToPath(Struct folder, int level) {

        if (folder.getChild() == null) {
            System.out.print("");
        }
        if (folder.getChild() != null) {
            System.out.println(folder.getPath() + ":");
            ls(folder);
            lsR(folder.getChild());
        }
        if (folder.getNext() != null && folder.getLevel() != level) {
            lsR(folder.getNext());
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
            lsRToPath(folder, folder.getLevel());
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

        if (folder.getChild() == null) {
            System.out.print("");
        }
        if (folder.getChild() != null) { // folder
            search(key, folder); // a -> b -> c
            searchR(key, folder.getChild());
        }
        if (folder.getNext() != null) {
            searchR(key, folder.getNext());
        }
    }

    public static void searchRToPath(String key, Struct folder, int level) {

        if (folder.getChild() == null) {
            System.out.print("");
        }
        if (folder.getChild() != null) { // folder
            search(key, folder); // a -> b -> c
            searchR(key, folder.getChild());
        }
        if (folder.getNext() != null && folder.getLevel() != level) {
            searchR(key, folder.getNext());
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
            searchRToPath(key, folder, folder.getLevel());
        }
    }
}
