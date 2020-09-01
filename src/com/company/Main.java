package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // variaveis de controle

        boolean loop = true;
        String optLine; // entrada passada pelo usuario
        String auxOpt = "";
        String[] opt;

        // estruturas de teste

        Struct root = new Struct();
        root.createRoot();

        Struct current = root;

        // loop de execuçao

        while (loop) {
            System.out.print(current.getPath() + "$ ");
            optLine = input.nextLine();
            opt = optLine.split(" "); // quebrando a entrada por " "

            // opt[0] sempre e o comando escolhido

            if(opt[0].equals("mkdir") || opt[0].equals("touch")) {
                auxOpt = opt[0];
                opt[0] = "create";
            }

            switch (opt[0]) {
                case "create":
                    switch (opt.length) {
                        case 2:
                            // dirname:1
                            Functions.createData(auxOpt, "noPath", opt[1], current);
                            break;
                        case 3:
                            // [path ]:1 dirname:2
                            Functions.createData(auxOpt, opt[1], opt[2], current);
                            break;
                        default:
                            if (auxOpt.equals("mkdir")){
                                System.out.println("Comando incorreto. Tente \"mkdir [path ] dirname\"");
                            } else {
                                System.out.println("Comando incorreto. Tente \"touch [path ] filename\"");
                            }
                    }
                    break;
                case "ls":
                    switch (opt.length) {
                        case 1:
                            Functions.ls(current);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Comando incorreto. Tente \"ls [-R ] [path ]\"");
                    }
                    break;
                case "search":
                    switch (opt.length) {
                        case 2:
                            Functions.search(opt[1], current);
                            break;
                        case 3:
                            break;
                        case 4:
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
