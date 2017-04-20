package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Principal {

    public Principal() throws IOException, ClassNotFoundException {
    }

    FileInputStream fis = new FileInputStream("t.tmp");
    ObjectInputStream ois = new ObjectInputStream(fis);
    ArrayList<Paciente> Lista_paciente = (ArrayList<Paciente>) ois.readObject();

    @FXML
    Button btn_verlista;
    @FXML
    Button btn_cancelar;
    @FXML
    Button btn_agregar;


    public void ver_lista(ActionEvent actionEvent) {
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

        Stage stage = (Stage) btn_verlista.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lista.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Aplicacion");
            alerta.setHeaderText("Mira, hubo un error...");
            alerta.showAndWait();
            Platform.exit();
        }
        FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        Lista controller = fxmlLoader.<Lista>getController();
        int index=0;
        for (Paciente p : Lista_paciente) {
            controller.listado.appendText("Nombre: " + p.getNombre1() + "\n" + "Apellido: "+ p.getApellido1() +"\n"+ "Cedula: " + p.getCedula1() + "\n" + "Fecha de Nacimiento: " + p.getNacimiento1() + "\n" + "Edad: " + p.getEdad1() + "\n" + "Numero de telefono: " + p.getTelefono1() + "\n" + "Sintomas: " + p.getSintomas1() + "\n" + "Fecha de agregado: " + p.getFecha1() + "\n" + "Su Posicion en la lista es: " + (index++) + "\n\n");
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void agregar_paciente(ActionEvent actionEvent) {
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

        Stage stage = (Stage) btn_agregar.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Agregar.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Aplicacion");
            alerta.setHeaderText("Mira, hubo un error...");
            alerta.showAndWait();
            Platform.exit();
        }
        FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void cancelar(ActionEvent actionEvent){

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir.");
        alert.setHeaderText("Usted esta saliendo de la aplicacion.");
        alert.setContentText("Esta seguro que desea salir?.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }
}
