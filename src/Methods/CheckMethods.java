package Methods;

public class CheckMethods {

    //Метод задает перечень символов (заглавных букв) для шифрования.
    public static boolean checkRussianSymbolUpper (char symbol) {
        return  (symbol >= 'А' && symbol <= 'Я');
    }

    //Метод задает перечень символов (строчных букв) для шифрования.
    public static boolean checkRussianSymbolLower (char symbol) {
        return  (symbol >= 'а' && symbol <= 'я');
    }

    //Метод задает перечень символов для шифрования.
    public static boolean checkSignSymbol (char symbol) {
        return (symbol >= ' ' && symbol <= '?');
    }

}
