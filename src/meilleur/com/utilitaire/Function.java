package meilleur.com.utilitaire;

import java.util.Scanner;

public class Function {

    public boolean whoPlays = true;
    public void mainMenu() {
        System.out.println("\t\tTP - Dame\n\n");
        System.out.println("Ce jeu a été créer par Yohan MOREN et Alexis VELLEINE.");
        System.out.println("C'est un jeu de Dame, il peut se jouer seul ou a 2.");
        System.out.println("\nLe jeu se joue de la sorte");
        System.out.println("Lorsque c'est votre tour vous devez renseigner la position de votre pion '11' suivis d'un espace");
        System.out.println("apres il faut un nombre de coups (1 pour un pion/1-9 pour la dame) suivi de soit");
        System.out.println("'TL' pour top left, 'TR' pour top right, 'BL' pour bottom left, 'BR' pour bottom right");
        System.out.println();
    }

    public boolean choix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nBonjour, voulez-vous jouer au Dame ? (yes|oui/no)\n");
        String reponse = scanner.nextLine();
        return reponse.equals("yes") || reponse.equals("y") || reponse.equals("oui") || reponse.equals("o") || (reponse.equals("no") ? false : false);
    }
    public boolean playerSwitch() {
        String player1 = "Joueur 1";
        String player2 = "Joueur 2";
        if (whoPlays) {
            System.out.println("Joueur 2 c'est a vous !");
            //Function to check if the game ended to add here
            whoPlays = false;
            return false;
        } else {
            System.out.println("Joueur 1 c'est a vous !");
            //Function to check if the game ended to add here
            whoPlays = true;
            return true;
        }
    }
}
