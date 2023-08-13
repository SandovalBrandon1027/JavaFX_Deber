package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.*;


public class HelloController {

    @FXML
    private Button boton_actualizar;

    @FXML
    private Button boton_borrar;

    @FXML
    private Button boton_crear;

    @FXML
    private Button boton_mostrar;

    @FXML
    private AnchorPane caja;

    @FXML
    private Label titulo;

    /***************************************************************************************************************************************/
    // Variables
    static final String DB_URL="jdbc:mysql://localhost/registro";
    static final String USER="root";
    static final String PASS="12345";
    static String QUERY_SELECT_NOMBRE;
    static String QUERY_INSERT;
    static String QUERY_DELETE;
    static String QUERY_UPDATE;

    /***************************************************************************************************************************************/

    /***************************************************************************************************************************************/
    // Funciones
    // Funcion Crear
    private void Funcion_Insertar(String QUERY_INSERT){
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ) {
            int rowsAffected = stmt.executeUpdate(QUERY_INSERT);
            Texto_Insertar();
        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
    }

    // Funcion Borrar
    private void Funcion_Borrar(String QUERY_DELETE){
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ) {
            int rowsAffected = stmt.executeUpdate(QUERY_DELETE);
            Texto_Borrar();
        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
    }

    // Funcion Actualizar
    private void Funcion_Actualizar(String QUERY_UPDATE){
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ) {
            int rowsAffected = stmt.executeUpdate(QUERY_UPDATE);
            Texto_Actualizar();
        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
    }

    // Funcion Mostrar
    private void Funcion_Mostrar_Nombre(String QUERY_SELECT_NOMBRE){
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY_SELECT_NOMBRE);
        ){
            while(rs.next()){
                Texto_Mostrar(rs);
            }

        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
    }

    /***************************************************************************************************************************************/
    // Funcion Mensaje Insertar
    private void Texto_Insertar(){
        System.out.println("\n*******************");
        System.out.println("Elemento Insertado");
        System.out.println("********************");
    }

    // Funcion Mensaje Borrar
    private void Texto_Borrar(){
        System.out.println("\n*******************");
        System.out.println("Elemento Borrado");
        System.out.println("********************");
    }

    // Funcion Mensaje Actualizar
    private void Texto_Actualizar(){
        System.out.println("\n*******************");
        System.out.println("Elemento Actualizado");
        System.out.println("********************");
    }

    // Funcion Mensaje Mostrar
    private void Texto_Mostrar(ResultSet rs) throws SQLException {
        System.out.println("\n*******************");
        System.out.println("Código:"+rs.getInt("id"));
        System.out.println("Nombre:"+rs.getString("nombre"));
        System.out.println("Fecha:"+rs.getString("fecha"));
        System.out.println("Cédula:"+rs.getInt("cedula"));
        System.out.println("Signo:"+rs.getString("signo"));
        System.out.println("********************");
    }

    /***************************************************************************************************************************************/

    @FXML
    void act_btn_actualizar(ActionEvent event) {
        QUERY_UPDATE= "UPDATE personas SET nombre = 'Paul' WHERE id = 1";
        Funcion_Actualizar(QUERY_UPDATE);
    }

    @FXML
    void act_btn_borrar(ActionEvent event) {
        QUERY_DELETE = "Delete from personas where id = 1";
        Funcion_Borrar(QUERY_DELETE);
    }

    @FXML
    void act_btn_crear(ActionEvent event) {
        QUERY_INSERT = "INSERT INTO personas (id, nombre, fecha, cedula, signo) VALUES\n" +
                "(1, 'Juan', '20 de Abril','1234567890', 'Aries')";
        Funcion_Insertar(QUERY_INSERT);
    }

    @FXML
    void act_btn_mostrar(ActionEvent event) {
        QUERY_SELECT_NOMBRE = "SELECT * FROM personas";
        Funcion_Mostrar_Nombre(QUERY_SELECT_NOMBRE);
    }
}
