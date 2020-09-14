package com.company;

public class Functions {

    // funções gerais

    // imprime o nome do diretório que contém a chave se houver, caso contrário retorna 0
    public static int printFolder(String key, Struct folder) {

        int i = 0;

        if (folder.getName().contains(key)) {
            System.out.println(folder.getPath());
            i += 1;
        }

        return i;
    }

    // imprime *folder -> next -> next ate chegar em null
    public static void printFolders(Struct folder) {

        while (folder != null) {
            System.out.print(folder.getName() + ":" + folder.getType() + ":" + folder.getPath() + " ");
            folder = folder.getNext();
        }
        System.out.println("");
    }

    // inicio das funçoes de "ls"

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

    public static void search(String key, Struct current) {

        current = current.getChild();
        int res = 0;
        while (current != null) {
            res += printFolder(key, current);
            current = current.getNext();
        }
        if (res == 0) System.out.println("Nada encontrado");
    }

    public static void searchR(String name, Struct folder) {}

    public static void searchPath(String name, String path, Struct current) {

        current = current.findFolderByPath(path, current);
        search(name, current);
    }

    public static void searchRPath(String name, Struct current) {}


}
