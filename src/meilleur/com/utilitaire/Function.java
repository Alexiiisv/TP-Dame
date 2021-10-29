package meilleur.com.utilitaire;

import java.util.Scanner;

public class Function {

    public void mainMenu() {
        System.out.println("\t\tTP - Dame\n\n");
        System.out.println("Ce jeu a été créer par Yohan MOREN et Alexis VELLEINE.");
        System.out.println("C'est un jeu de Dame, il peut se jouer seul ou a 2.");
    }

    public boolean choix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bonjour, voulez-vous jouer au Dame ? (yes|oui/no)\n");
        String reponse = scanner.nextLine();
        boolean reponseBool = reponse.equals("yes") || reponse.equals("y") || reponse.equals("oui") || reponse.equals("o") || (reponse.equals("no") ? false : false);
        System.out.println(reponseBool);
        return reponseBool;
    }
}
