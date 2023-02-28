package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class UI_JavaFX extends Application {

    //Метод start нужен для управления свойствами (функционалом нашего окна).
    @Override
    public void start (Stage stage) throws Exception {
        stage.show();                       //У аргумента stage вызываем метод show, чтобы увидеть запускаемое окно в main.
        stage.setTitle("Cryptographer");    //Задаем название самого окна(stage)
        stage.setWidth(800);                //Задаем размеры окна
        stage.setHeight(600);

    }
}
