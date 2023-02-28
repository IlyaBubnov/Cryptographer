package Methods;

import static Methods.CheckMethods.*;

public class Encrypt {

    //Метод encryptTxtFile ШИФРУЕТ исходные символы шифром Цезаря с заданным смещением.

/*Внимание! Остаток от деления (32) и значения (1040, 1072, 32 - номера в кодировке Unicode) в формулах применимы только
для прописанного набора символв (русский алфавит). При изменении критерия (набора) формулы переписать в соответствии
с кол-ом символов и кодами кодировки Unicode. Символы, подлежащие кодировке, прописываем в классе CheckMethods.
Код первого символа 32 (ПРОБЕЛ). Его и используем в формуле для кодировки.*/
    public static char[] encryptTxtFile (char [] arrayInput, int key) {

        for (int i = 0; i < arrayInput.length; i++) {

            if (checkRussianSymbolUpper(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + Math.abs(key)) - 1040) % 32 + 1040);
            }

            if (checkRussianSymbolLower(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + Math.abs(key)) - 1072) % 32 + 1072);
            }

            if (checkSignSymbol(arrayInput[i])) {
                arrayInput[i] = (char) (((arrayInput[i] + Math.abs(key)) - 32) % 32 + 32);
            }
        }
        return arrayInput;
    }
}
