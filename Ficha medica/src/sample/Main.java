package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        ArrayList<Paciente> Lista_paciente = new ArrayList<Paciente>();
        File f = new File("t.tmp");
        if(f.exists() && !f.isDirectory()) {
            Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
            primaryStage.setTitle("Ficha Medica");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } else {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("t.tmp");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oos.writeObject(Lista_paciente);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
            primaryStage.setTitle("Ficha Medica");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
