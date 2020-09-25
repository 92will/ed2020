package com.company;

public class Struct {
    private String name;
    private String path;
    private String type;
    private int level;
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

    public int getLevel() {
        return level;
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
        this.level = 0;
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

    public boolean checkValidName(String str) { // verifica se o nome é válido
        return str.length() <= 50 && str.matches("(?i)\\D*");
    }

    public boolean checkEqualName(String name, Struct folder) { // verifica igualdade de nomes

        if (folder.next == null) {
            return folder.name.equals(name);
        } else {
            return folder.name.equals(name) || folder.next.name.equals(name);
        }
    }

    // coloca alguns atributos e direciona os ponteiros
    public void setAtt(String name, Struct root, Struct newData) {

        Struct current;

        if (checkValidName(name) && root.level <= 98) {
            newData.name = name;
            newData.level = root.level + 1;

            if (root.path.equals("/")) { // se for a raiz
                newData.path = root.path + name;
            } else {
                newData.path = root.path + "/" + name;
            }

            if (root.child == null) { // root.subpasta -> null
                root.child = newData;
            } else { // root.subpasta -> *
                current = root.child; // atual = root.subpasta
                if (current.next == null) { // se atual.próximo -> null
                    if (!checkEqualName(name, current)) { // se atual.nome != nome
                        if (current.name.compareTo(name) > 0) {
                            // root -> *a -> b -> null
                            root.child = newData;
                            newData.next = current;
                        } else {
                            // root -> a -> *b -> null
                            current.next = newData;
                        }
                    } else {
                        if (current.getType().equals("folder")) {
                            System.out.println("Não foi possível criar o diretório \"" + current.getName() + "\": arquivo existe");
                        } else {
                            System.out.println("Não foi possível criar o arquivo \"" + current.getName() + "\": arquivo existe");
                        }
                    }
                } else {
                    current = findOrderByName(name, current);
                    if (!checkEqualName(name, current)) {
                        // a -> b *c -> d -> null
                        newData.next = current.next;
                        current.next = newData;
                    } else {
                        if (current.getType().equals("folder")) {
                            System.out.println("Não foi possível criar o diretório \"" + current.getName() + "\": arquivo existe");
                        } else {
                            System.out.println("Não foi possível criar o arquivo \"" + current.getName() + "\": arquivo existe");
                        }
                    }
                }
            }
        } else {
            System.out.println("Nome inválido!");
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
