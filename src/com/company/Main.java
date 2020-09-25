package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // variaveis de controle

        boolean loop = true;
        String optLine; // entrada passada pelo usuario
        String[] opt;

        // estruturas de teste

        Struct current = new Struct();
        current.createRoot();

        // loop de execuçao

        while (loop) {
            System.out.print(current.getPath() + "$ ");
            optLine = input.nextLine();
            opt = optLine.split(" "); // quebrando a entrada por " "

            // opt[0] sempre e o comando escolhido

            switch (opt[0]) {
                case "mkdir", "touch":
                    switch (opt.length) {
                        case 2:
                            // dirname:1
                            current.createData(opt[0], "noPath", opt[1], current);
                            break;
                        case 3:
                            // [path ]:1 dirname:2
                            current.createData(opt[0], opt[1], opt[2], current);
                            break;
                        default:
                            if (opt[0].equals("mkdir")){
                                System.out.println("Comando incorreto. Tente \"mkdir [path ] dirname\"");
                            } else {
                                System.out.println("Comando incorreto. Tente \"touch [path ] filename\"");
                            }
                    }
                    break;
                case "ls":
                    switch (opt.length) {
                        case 1:
                            // ls
                            Functions.ls(current);
                            break;
                        case 2:
                            // ls [-R ]:1 ou ls [path ]:1
                            if (opt[1].equalsIgnoreCase("-R")) {
                                Functions.lsR(current);
                            } else {
                                Functions.lsPath(opt[1], current);
                            }
                            break;
                        case 3:
                            // ls:0 [-R ]:1 [path ]:2
                            if (opt[1].equalsIgnoreCase("-R")) {
                                Functions.lsRPath(opt[2], current);
                            } else {
                                System.out.println("Comando incorreto. Tente \"ls [-R ] [path ]\"");
                            }
                            break;
                        default:
                            System.out.println("Comando incorreto. Tente \"ls [-R ] [path ]\"");
                    }
                    break;
                case "search":
                    switch (opt.length) {
                        case 2:
                            // search searchkey:1
                            Functions.search(opt[1], current);
                            break;
                        case 3:
                            // search [-R ]:1 searchkey:2 ou search [path ]:1 searchkey:2
                            if (opt[1].equalsIgnoreCase("-R")) {
                                Functions.searchR(opt[2], current);
                            } else {
                                Functions.searchPath(opt[2], opt[1], current);
                            }
                            break;
                        case 4:
                            // search [-R ]:1 [path ]:2 searchkey:3
                            if (opt[1].equalsIgnoreCase("-R")) {
                                Functions.searchRPath(opt[3], opt[2], current);
                            } else {
                                System.out.println("Comando incorreto. Tente \"ls [-R ] [path ]\"");
                            }
                            break;
                        default:
                            System.out.println("Comando incorreto. Tente \"search [-R ] [path ] searchkey\"");
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("Comando invalido. Use \"[mkdir] [touch] [ls] [search] ou [exit]\"");
            }
        }
    }
}
