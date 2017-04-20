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

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Agregar {


    // Lista
    FileInputStream fis = new FileInputStream("t.tmp");
    ObjectInputStream ois = new ObjectInputStream(fis);
    ArrayList<Paciente> Lista_paciente = (ArrayList<Paciente>) ois.readObject();

    @FXML
    DatePicker date_nacimiento;
    @FXML
    Button btn_verlista;
    @FXML
    TextField txt_nombre;
    @FXML
    TextField txt_apellido;
    @FXML
    TextField txt_cedula;
    @FXML
    TextField txt_nacimiento;
    @FXML
    TextField txt_edad;
    @FXML
    TextField txt_telefono;
    @FXML
    TextArea txt_sintomas;
    @FXML
    DatePicker date_fecha;
    @FXML
    Button btn_volver;
    @FXML
    Button btn_guardar;

    public Agregar() throws IOException, ClassNotFoundException {
    }


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

    public void guardar(ActionEvent actionEvent) {

            String nom = new String(txt_nombre.getText());
            String ape = new String(txt_apellido.getText());
            String cedu = new String(txt_cedula.getText());
            String naci = new String(String.valueOf(date_nacimiento.getValue()));
            String eda = new String(txt_edad.getText());
            String tel = new String(txt_telefono.getText());
            String sin = new String(txt_sintomas.getText());
            String fec = new String (String.valueOf(date_fecha.getValue()));
            Paciente A = new Paciente();
            A.setNombre1(nom);
            A.setApellido1(ape);
            A.setCedula1(cedu);
            A.setNacimiento1(naci);
            A.setEdad1(eda);
            A.setTelefono1(tel);
            A.setSintomas1(sin);
            A.setFecha1 (fec);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Agregar. ");
        alert.setHeaderText("Agregando paciente a la lista. ");
        alert.setContentText("Precione aceptar para agregar. ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           if (nom.isEmpty() || ape.isEmpty() || cedu.isEmpty() || naci.isEmpty() || eda.isEmpty() || tel.isEmpty() || sin.isEmpty() || fec.isEmpty()) {
               Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
               alert1.setTitle("Agregar. ");
               alert1.setHeaderText("Algunos campos estan vacios. ");
               alert1.setContentText("Esta seguro que deea agregar al paciente. ");
               Optional<ButtonType> result1 = alert1.showAndWait();
               if (result1.get() == ButtonType.OK) {
                   Lista_paciente.add(A);
               }
               if (result1.get() == ButtonType.CANCEL) {
                   return;
               }
           }else {
               Lista_paciente.add(A);
           }
        }
        if(result.get()==ButtonType.CANCEL){
            return;
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agregar.fxml"));

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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    public void nombre(ActionEvent actionEvent) {
    }

    public void apellido(ActionEvent actionEvent) {
    }

    public void cedula(ActionEvent actionEvent) {
    }

    public void naciemiento(ActionEvent actionEvent) {
    }

    public void edad(ActionEvent actionEvent) {
    }

    public void telefono(ActionEvent actionEvent) {
    }

    public void fecha(ActionEvent actionEvent) {

    }


}
