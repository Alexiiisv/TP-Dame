package meilleur.com.utilitaire;

import meilleur.com.model.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Function {
    public boolean whoPlays = true;
    private String dataToWrite = "";

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

    public void appendDataToResult(String data, Player player1, Player player2) {
        if (data == null)
            data = "{\n\t\"Players\": [\n\t\t\"" + player1.getName() + "\",\n\t\t\"" + player2.getName() + "\"\n\t],\n\t\"Moves\": [\n";
        appendDataToResult(data);
    }

    public void appendDataToResult(String data) {
        if (!data.equals("\t\t\"p\",\n") && !data.equals("\t\t\"P\",\n")) dataToWrite += data;
    }

    public void writeFile() {
        FileWriter fileWritter;
        final String fileName = "src/meilleur/com/save/" + millisToDate() + ".json";

        try {
            fileWritter = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            char[] replastCharArr = dataToWrite.toCharArray();

            for (int i = replastCharArr.length - 1; i >= 0; i--) {
                if (replastCharArr[i] == ',') {
                    replastCharArr[i] = ' ';
                    break;
                }
            }
            bw.write(replastCharArr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String millisToDate() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("dd-MM-yyyy 'a' kk-mm-ss");
        Date date = new Date();
        return SDFormat.format(date);
    }
}

