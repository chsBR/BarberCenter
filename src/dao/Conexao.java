/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 2004s
 */
public class Conexao {

    private static final String URL
            = "jdbc:mysql://localhost:3306/barbearia?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo";
    private static final String USER = "uc11_user";
    private static final String PASSWORD = "Uc11@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
