package Methods;

import java.util.Scanner;

import static Constants.ConsoleConstants.*;
import static Methods.BruteForce.decryptionBruteForce;
import static Methods.BruteForce.keyDecrypt;
import static Methods.Decrypt.decryptTxtFile;
import static Methods.Encrypt.encryptTxtFile;

public class ActionChoice {

    static int keyEncryptDecrypt;

    //Метод выбирает действие на основе команды пользователя (шифровать, дешифровать, взламывать)
    public static void actionChoice (String userChoice, char [] arrayChars) {

        Scanner scanner = new Scanner(System.in);

        if (userChoice.equalsIgnoreCase("Encrypt")) {
            System.out.println (CHOOSE_AN_ACTION);
            keyEncryptDecrypt = scanner.nextInt();
            encryptTxtFile (arrayChars, keyEncryptDecrypt);
            System.out.println ("File encrypted.");

        } else if (userChoice.equalsIgnoreCase("Decrypt")) {
            System.out.println (CHOOSE_AN_ACTION);
            keyEncryptDecrypt = scanner.nextInt();
            decryptTxtFile (arrayChars, keyEncryptDecrypt);
            System.out.println ("File decrypted.");

        } else if (userChoice.equalsIgnoreCase("Hack")) {
            decryptionBruteForce (arrayChars);
            System.out.printf("File hacked. Source key: %s%n", keyDecrypt);

        } else {
            throw new RuntimeException (EXCEPTION);
        }

        scanner.close();
    }
}
