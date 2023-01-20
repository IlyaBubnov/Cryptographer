package Cryptographer;

import static Cryptographer.Decrypt.decryptTxtFile;
import static Cryptographer.Encrypt.encryptTxtFile;

public class BruteForce {

    //Метод ПОДБИРАЕТ КЛЮЧ и ДЕШИФРУЕТ текст

/*Пояснения
        - Наличие в расшифрованном тексте одновременно ТОЧКИ и ПРОБЕЛА является "сигналом" к успешному подбору ключа.
        - Переменная keyDecrypt служит как искомым ключом шифра, так и условием выхода из цикла for
          (как только мы находим символы пробел и точку, то присваиваем пере-й keyDecrypt значение i-го
          элемента (по сути искомого ключа), после чего прерываем цикл.
        - Возвращаем в метод наш исходный массив arrayInput, применив к нему найденный ключ расшифровки.*/
    public static int keyDecrypt;
    private static final String space = String.valueOf(' ');
    private static final String dot = String.valueOf('.');

    public static char[] decryptionBruteForce (char[] arrayInput) {

        for (int i = 1; true; i++) {
            if (keyDecrypt > 0) {
                break;
            } else {
                decryptTxtFile(arrayInput, i);
                String array = String.valueOf(arrayInput);

                if (array.contains(space) && array.contains(dot)) {
                    keyDecrypt = i;
                    break;
                }
                encryptTxtFile(arrayInput, i);
            }
        }
        return arrayInput;
    }
}
