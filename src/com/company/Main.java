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

        Struct root = new Struct();
        root.createRoot();

        Struct current = root;

        // loop de execuçao

        while (loop) {
            System.out.print(current.getPath() + "$ ");
            optLine = input.nextLine();
            opt = optLine.split(" "); // quebrando a entrada por " "

            // opt[0] sempre e o comando escolhido

            switch (opt[0]) {
                case "mkdir":
                    switch (opt.length) {
                        case 2:
                            Functions.createData(opt[1], opt[0], current);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Comando incorreto. Tente \"mkdir [path ] dirname\"");
                    }
                    break;
                case "touch":
                    switch (opt.length) {
                        case 2:
                            Functions.createData(opt[1], opt[0], current);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Comando incorreto. Tente \"touch [path ] filename\"");
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
