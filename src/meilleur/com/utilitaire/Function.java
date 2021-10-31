package meilleur.com.utilitaire;

import java.util.Scanner;

public class Function {

    public boolean isGameEnded = false;
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
    public void playerSwitch() {
        boolean whoPlays = true;
        String player1 = "Joueur 1";
        String player2 = "Joueur 2";
        while (isGameEnded == false) {
            if (whoPlays) {
                System.out.println("Joueur 1 c'est a vous ! \n Que souhaitez vous faire ?");
                Scanner scanner = new Scanner(System.in);
                System.out.println(scanner.nextLine());
                //Function to check if the game ended to add here
                whoPlays = false;
            } else if (!(whoPlays)) {
                System.out.println("Joueur 2 c'est a vous ! \n Que souhaitez vous faire ?");
                Scanner scanner = new Scanner(System.in);
                System.out.println(scanner.nextLine());
                //Function to check if the game ended to add here
                whoPlays = true;
            }
        }
    }
}
