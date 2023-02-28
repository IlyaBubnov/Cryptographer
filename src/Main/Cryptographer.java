package Main;

import javafx.application.Application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static Methods.ActionChoice.actionChoice;
import static Constants.ConsoleConstants.*;

public class Cryptographer {

    public static void main (String[] args) throws IOException {

        Application.launch();//запускаем UI на JavaFX

    /*Пояснения (строки 24-29).
В блоке try with recourses, для автозакрытия объектов с внешними ресурсами, объявляем и инициализируем следующее:
  - объект класса Scanner, в кот. через консоль укажем путь к исходному файлу.
  - объекты класса  RandomAccessFile, в параметры кот. передаю путь к файлу (scanner.nextLine()) и модификатор (rw),
    кот. говорит, что файл откроется для чтения и записи.
  - объекты класса FileChannel как канал для чтения и записи данных в файл.*/
        System.out.println (CHOOSE_LOCATION);
        try (Scanner scanner = new Scanner(System.in);
             RandomAccessFile randomAccessFileIn = new RandomAccessFile (scanner.nextLine(), "rw");
             RandomAccessFile randomAccessFileOut = new RandomAccessFile (RESULT_FILE, "rw");
             FileChannel channelIn = randomAccessFileIn.getChannel();
             FileChannel channelOut = randomAccessFileOut.getChannel()) {

    /*Пояснения (строки 44-53).
Нам нужно передать данные из исходного файла в буфер для дальнейшей работы с этими данными. Для этого создали канал
channel, с помощью кот. будем писать данные из файла в буфер. Создаем буффер размером с канал и записываем в него данные.
Переключаем буффер с режима записи на режим чтения (чтобы читать из буфера, + тем самым, сбрасываем каретку в начало).
Возникли проблемы с отображением русских букв (видимо что-то с кодировкой...). Принято решение использовать класс
StandardCharsets из библтотеки java.nio.charset для решения проблемы с отображением русских букв. Кодируем байты
в юникод (UTF-8) и помещаем их в виде char в новый буфур decodeBuffer, и уже из этого буфера передаем char в массив
чаров для дальнейшей работы.
Изначально, не используя метод limit(), из-за того, что русские символы весят 2 байта, в массив чаров в передавались
пустые ячейки (null),т.е., например, предали три буквы (6 байт), а в массиве получилось 3 символа (буквы) и 3 пустых
байта. Для решения этой проблемы используем метод limit()(определяем реальное кол-во символов) и уже в цикле наполняем
наш arrayChars как нужно (без "пустых" байтов).
*/
            ByteBuffer byteBufferInput = ByteBuffer.allocate((int) channelIn.size());
            channelIn.read(byteBufferInput);
            byteBufferInput.flip();

            CharBuffer decodeBufferInput = StandardCharsets.UTF_8.decode(byteBufferInput);
            int charCount = decodeBufferInput.limit();
            char[] arrayChars = new char[charCount];
                for (int i = 0; i <charCount; i++) {
                    arrayChars [i] = decodeBufferInput.get();
                }
    /*Пояснения (строки 61-63).
Пользователь выбирает действие с файлом (вводит один из трех вариантов). Если не введен любой из трех предлагаемых
вариантов действий, то выбрасывается эксепшн и программа завершается. Далее, в методе actionChoice, уже через
опреаторы if-else, прогоняем наши методы (шифрование, дешифрование, брутфорс), исходя из выбора пользователя.
Для удобства дальнейшего использования вынос в отдельные методы CheckMethods перечень того (т.е. символы), что хотим
шифровать (например, поменяется список символов и тогда будет достаточно внести изменение только в одном методе,
а не в нескольких).*/
            System.out.println (YOUR_CHOICE);
            String userChoice = scanner.nextLine();
            actionChoice (userChoice, arrayChars);


    /*Пояснения (строки 70-73).
Для дальнейшей записи измененного массива символов (arrayChars) в файл используем буфер типа ByteBuffer и канал типа
FileChannel. Опять возникали проблемы с отображением русских букв. Поэтому массив чаров передаем в стрингу, а уже ее
"отправляем" в буфер. Дальше в канал и получаем желаемый измененный файл.*/
            ByteBuffer byteBufferOutput;
            String codedTextStr = String.valueOf(arrayChars);
            byteBufferOutput = ByteBuffer.wrap(codedTextStr.getBytes());
            channelOut.write(byteBufferOutput);
        }
    }
}