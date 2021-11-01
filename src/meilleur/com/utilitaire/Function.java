package meilleur.com.utilitaire;

import meilleur.com.model.Board;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Function {
    public boolean started = false;
    public boolean whoPlays = true;
    Board board = new Board();

    public void mainMenu() {
        System.out.println("\t\tTP - Dame\n\n");
        System.out.println("Ce jeu a été créer par Yohan MOREN et Alexis VELLEINE.\nC'est un jeu de Dame, il peut se jouer seul ou a 2.");
        System.out.println("\n\t\tINSTRUCTION\nLe jeu se joue de la sorte");
        System.out.println("Lorsque c'est votre tour vous devez renseigner la position de votre pion '11' suivi d'un espace\napres il faut un nombre de coups (1 pour un pion/1-9 pour la dame) suivi de soit");
        System.out.println("'TL' pour top left, 'TR' pour top right, 'BL' pour bottom left, 'BR' pour bottom right\nPar exemple on écrit '11 1TL' pour bouger le pion a la position 1-1 du jeu de 1 sur la diagonale gauche haut");
        System.out.println("Le joueur 1 joue les 'p' et le joueur 2 joue les 'P'");
    }

    public boolean choix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nBonjour, voulez-vous jouer au Dame ? (yes|oui/no)\n");
        String reponse = scanner.nextLine();
        return reponse.equals("yes") || reponse.equals("y") || reponse.equals("oui") || reponse.equals("o") || (reponse.equals("no") ? false : false);
    }

    public boolean playerSwitch() {
        String player1 = "Joueur 1", player2 = "Joueur 2";
        if (whoPlays) {
            //Function to check if the game ended to add here
            whoPlays = false;
            return false;
        } else {
            //Function to check if the game ended to add here
            whoPlays = true;
            return true;
        }
    }

    public void FileCreateReadWrite(String data) {
        try {
            if (data == null) {
                data = "Voici les déplacements réalisés lors de la partie : \n";
            }
            File f1 = new File("Moves.txt");
            if(started == false)
            if (!f1.exists()) {
                f1.createNewFile();
                started = true;
            } else {
                f1.delete();
                f1.createNewFile();
                started = true;
            }
            FileWriter fileWritter = new FileWriter(f1.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write("- " + data + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

