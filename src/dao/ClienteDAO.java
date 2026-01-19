/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.*;

/**
 *
 * @author 2004s Classe DAO de Cliente
 */
public class ClienteDAO {

    public Cliente login(String email, String senha) {

        String sql = """
            SELECT idCliente, nome, email, senha, tipo_usuario
            FROM cliente
            WHERE email = ? AND senha = ?
        """;
        
        
        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TipoUsuario tipo = TipoUsuario.valueOf(rs.getString("tipo_usuario"));
                return new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        tipo
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean cadastrar(Cliente cliente) {

        String sql = """
            INSERT INTO cliente (nome, email, senha)
            VALUES (?, ?, ?)
        """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSenha());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int cadastrarBarbeiro(Cliente cliente) {

        String sql = """
        INSERT INTO cliente (nome, email, senha, tipo_usuario)
        VALUES (?, ?, ?, 'BARBEIRO')
    """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS
        )) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSenha());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
