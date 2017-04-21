package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.*;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.Optional;

public class Lista {

    // Lista
    FileInputStream fis = new FileInputStream("t.tmp");
    ObjectInputStream ois = new ObjectInputStream(fis);
    ArrayList<Paciente> Lista_paciente = (ArrayList<Paciente>) ois.readObject();

    public Lista() throws IOException, ClassNotFoundException {
    }

    public void setListado(TextArea listado) {
        this.listado = listado;
    }
    public TextArea getListado() {
        return listado;
    }

    @FXML
    Button btn_editar;
    @FXML
    Button btn_eliminar;
    @FXML
    Button btn_volver;
    @FXML
    Button btn_agregar;
    @FXML
    TextArea listado;

    public void volver(ActionEvent actionEvent) {
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

        Stage stage = (Stage) btn_volver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Principal.fxml"));

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

    public void agregar_paciente(ActionEvent actionEvent) {

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

    public void eliminar(ActionEvent actionEvent) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar.");
        dialog.setHeaderText("Ingrese la pocision del paciente que desea eliminar.");
        Optional<String> k = dialog.showAndWait();
        String s = k.get();
       try {
           int ss = Integer.parseInt(s);
           Lista_paciente.remove(ss);
       } catch (Exception e) {
           Alert alerta = new Alert(Alert.AlertType.ERROR);
           alerta.setTitle("Error de Aplicacion");
           alerta.setHeaderText("El paciente no existe.");
           alerta.showAndWait();
       }

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

        Stage stage = (Stage) btn_volver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lista.fxml"));

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


        Lista controller = fxmlLoader.<Lista>getController();
        int index=0;
        for (Paciente p : Lista_paciente) {
            controller.listado.appendText("Nombre: " + p.getNombre1() + "\n" + "Apellido: "+ p.getApellido1() +"\n"+ "Cedula: " + p.getCedula1() + "\n" + "Fecha de Nacimiento: " + p.getNacimiento1() + "\n" + "Edad: " + p.getEdad1() + "\n" + "Numero de telefono: " + p.getTelefono1() + "\n" + "Sintomas: " + p.getSintomas1() + "\n" + "Fecha de agregado: " + p.getFecha1() + "\n" + "Su Posicion en la lista es: " + (index++) + "\n\n");
        }


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void editar(ActionEvent actionEvent) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar.");
        dialog.setHeaderText("Ingrese la pocision del paciente que desea editar.");
        Optional<String> k = dialog.showAndWait();
        String s = k.get();
        try {
            int ss = Integer.parseInt(s);
            Paciente d = Lista_paciente.get(ss);
            Lista_paciente.remove(ss);

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

            Agregar controller = fxmlLoader.<Agregar>getController();
            controller.txt_nombre.appendText(d.getNombre1());
            controller.txt_apellido.appendText(d.getApellido1());
            controller.txt_cedula.appendText(d.getCedula1());
            controller.txt_nacimiento.appendText(d.getNacimiento1());
            controller.txt_edad.appendText(d.getEdad1());
            controller.txt_telefono.appendText(d.getTelefono1());
            controller.txt_sintomas.appendText(d.getSintomas1());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Aplicacion");
            alerta.setHeaderText("El paciente no existe.");
            alerta.showAndWait();
        }

    }
}
