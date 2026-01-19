/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Barbeiro;

/**
 *
 * @author 2004s Classe DAO de Barbeiro
 */
public class BarbeiroDAO {

    public List<Object[]> listar() {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT 
            b.idBarbeiro,
            c.nome,
            ROUND(AVG(av.notaAvaliacao), 1) AS mediaAvaliacao,
            COUNT(av.idAvaliacao) AS totalAvaliacoes
        FROM barbeiro b
        JOIN cliente c ON c.idCliente = b.idCliente
        LEFT JOIN agendamento ag ON ag.idBarbeiro = b.idBarbeiro
        LEFT JOIN avaliacao av ON av.idAgendamento = ag.idAgendamentos
        GROUP BY b.idBarbeiro, c.nome
        ORDER BY c.nome
    """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("idBarbeiro"),
                    rs.getString("nome"),
                    rs.getDouble("mediaAvaliacao"),
                    rs.getInt("totalAvaliacoes")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean cadastrar(int idCliente, String telefone, String cpf) {

        String sql = """
            INSERT INTO barbeiro (idCliente, telefone, cpf)
            VALUES (?, ?, ?)
        """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, telefone);
            ps.setString(3, cpf);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Barbeiro buscarPorCliente(int idCliente) {

        String sql = """
        SELECT idBarbeiro
        FROM barbeiro
        WHERE idCliente = ?
    """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Barbeiro(
                        rs.getInt("idBarbeiro"),
                        null,
                        0,
                        0
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
