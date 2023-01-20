package Cryptographer;

import static Cryptographer.CheckMethods.*;

public class Decrypt {

    //Метод decryptTxtFile ДЕШИФРУЕТ исходные символы шифром Цезаря с заданным смещением.

/*Расшифровку реализуем аналогично ШИФРОВАНИЮ, но уже с отрицательным смещением (колдуем с формулой (с ключом)).*/

    public static char[] decryptTxtFile (char [] arrayInput, int key) {
        for (int i = 0; i < arrayInput.length; i++) {
            if (checkRussianSymbolUpper(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + 32 - (key%32)) - 1040) % 32 + 1040);
            }
            if (checkRussianSymbolLower(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + 32 - (key%32)) - 1072) % 32 + 1072);
            }
            if (checkSignSymbol(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + 32 - (key%32)) - 32) % 32 + 32);
            }
        }
        return arrayInput;
    }
}
