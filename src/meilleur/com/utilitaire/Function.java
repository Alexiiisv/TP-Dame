package meilleur.com.utilitaire;

import meilleur.com.model.Pions;
import meilleur.com.model.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Function {
    public boolean whoPlays = true;
    private String dataToWrite = "";

    /**
     * Affichage du menu principale avec instruction du jeu
     */
    public void mainMenu() {
        System.out.println("\t\tTP - Dame\n\n");
        System.out.println("Ce jeu a été créer par Yohan MOREN et Alexis VELLEINE.\nC'est un jeu de Dame, il peut se jouer seul ou a 2.");
        System.out.println("\n\t\tINSTRUCTION\nLe jeu se joue de la sorte");
        System.out.println("Lorsque c'est votre tour vous devez renseigner la position de votre pion '11' suivi d'un espace\napres il faut un nombre de coups (1 pour un pion/1-9 pour la dame) suivi de soit");
        System.out.println("'TL' pour top left, 'TR' pour top right, 'BL' pour bottom left, 'BR' pour bottom right\nPar exemple on écrit '11 1TL' pour bouger le pion a la position 1-1 du jeu de 1 sur la diagonale gauche haut");
        System.out.println("Le joueur 1 joue les 'p' et le joueur 2 joue les 'P'\nTous les déplacements possibles affichés fonctionne pour les Pions pas les Dames");
    }

    /**
     * Affichage pour demander si l'utilisateur veut jouer au jeu
     * Il a le choix entre oui et non.
     */
    public String choix() {
        System.out.println("\n\nBonjour, voulez-vous jouer aux Dames, regarder un replay ou rien faire ? (jouer/replay/quitter)\n");
        return askTerminalString();
    }

    public boolean isBotPlaying() {
        System.out.println("Combien y'aura t'il de joueurs ?\nPar défaut vous serez seul si vous ne remplissez rien");
        int answer = askTerminalInt();
        if (answer == 1) {
            return true;
        } else if (answer == 2) {
            return false;
        } else {
            System.out.println("Vous jouerez donc seul");
            return true;
        }
    }

    public ArrayList<String> botMoves(Player p1, char[][] playboard){
        return p1.checkPossibleMoves(playboard);
    }

    /**
     * retourne un boolean qui correspond a un joueur
     * @return boolean false = player 1
     */
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

    /**
     * a utiliser en debut de partie, elle permet de setup le debut d'un fichier json
     * @param data = information a rajouter au json
     * @param player1 = joueur qui joue
     * @param player2 = joueur adverse
     */
    public void appendDataToResult(String data, Player player1, Player player2) {
        if (data == null)
            data = "{\n\t\"Players\": [\n\t\t\"" + player1.getName() + "\",\n\t\t\"" + player2.getName() + "\"\n\t],\n\t\"Moves\": [\n";
        appendDataToResult(data);
    }

    /**
     * Sert a inscrire le déplacement fait par un joueur dans la partie, elle est appelée a chaque tour
     * @param data = information a rajouter au json
     */
    public void appendDataToResult(String data) {
        if (!data.equals("\t\t\"p\",\n") && !data.equals("\t\t\"P\",\n")) dataToWrite += data;
    }

    public int inverseOrNotInt(String pos) {
        if (pos.equals("T") || pos.equals("L")) return -1;
        return 1;
    }

    public boolean checkIfHisPions(char c1, Player player) {
        for (char c2 : player.getLetter()) { if (c2 == c1) return false; }
        return true;
    }

    /** Écrit dans le fichier a la fin de la partie */
    public void writeFile() {
        FileWriter fileWritter;
        final String fileName = "src/meilleur/com/save/" + millisToDate() + ".json";

        try {
            fileWritter = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fileWritter);

            bw.write(dataToWrite);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLast() {
        char[] replastCharArr = dataToWrite.toCharArray();
        if (countCharInString(replastCharArr) > 2) {
            for (int i = replastCharArr.length - 1; i >= 0; i--) {
                if (replastCharArr[i] == ',') {
                    replastCharArr[i] = ' ';
                    break;
                }
            }
            StringBuilder str = new StringBuilder();
            for (char c: replastCharArr) {
                str.append(c);
            }
            dataToWrite = str.toString();
        }
    }

    /**
     * vérifie dans le dossier des sauvegardes si il en existe.
     * Si des saves existes il les affiches et demande a l'utilisateur quel replay il veut regarder
     * @return Nom du fichier
     */
    public String printAllSave() {
        String[] allName = listAllSave();
        int i = 0, filechoosen;
        for (String s : allName) {
            System.out.println(i + " " + s);
            i++;
        }
        System.out.println("\nQuel save veut-tu lire ?");
        try {
            filechoosen = askTerminalInt();
            if (filechoosen > allName.length - 1) {
                System.out.println("Tu as choisi un fichier qui n'existe pas\nIl faudra relancer le code pour pouvoir visionner un replay");
                System.exit(10);
            }
            return allName[filechoosen];
        } catch (Exception e) {
            System.out.println("Tu as du réaliser une faute d'input\nIl faudra relancer le code pour pouvoir visionner un replay");
            System.exit(10);
        }
        return "";
    }

    /**
     * modifie le nombre de deplacement d'un pion si l'input est mauvais
     * @param str mouvement demandé
     * @param p1 joueur qui joue
     * @param lastX position du pion sur l'axe X
     * @param lastY position du pion sur l'axe Y
     * @return le déplacement modifier
     */
    public String moveUpdate(String str, Player p1, int lastX, int lastY) {
        String[] strings = str.split("");
        Pions pions = p1.getPion(lastY, lastX);
        if (!strings[0].equals("1") && pions.getClass().getSimpleName().equals("Pion")) {
            strings[0] = "1";
            return strings[0] + strings[1] + strings[2];
        }
        return str;
    }

    public String askTerminalString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int askTerminalInt() {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        try {
            result = scanner.nextInt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * compte le nombre de caractère qu'il y a dans une liste de char
     * @param chars texte ou faut compter le caractère
     * @return le nombre de caractère trouvé
     */
    private int countCharInString(char[] chars) {
        int i = 0;
        for (char ch: chars) {
            if (ch == ',') i++;
        }
        return i;
    }

    /**
     * retourne un nom de fichier sous un format de type "dd-MM-yyyy 'a' kk-mm-ss"
     * @return nom du fichier
     */
    private String millisToDate() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("dd-MM-yyyy 'a' kk-mm-ss");
        return SDFormat.format(new Date());
    }

    private String[] listAllSave() {
        File folder = new File("src/meilleur/com/save");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        String[] listOfFilesName = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFilesName[i] = listOfFiles[i].getName();
            }
        }
        return listOfFilesName;
    }


    public void waitTime(int milli, char state) {
        try {
            if (state == 'P') {
                for (int i = 0; i < milli/400; i++) {
                    System.out.print(". ");
                    Thread.sleep(milli/5);
                }
                System.out.println('\n');
            }
            if (state == 'R') Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

