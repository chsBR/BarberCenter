/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author 2004s
 * DAO responsável pelas avaliações dos barbeiros
 */
public class AvaliacaoDAO {

    public boolean existeAvaliacaoParaAgendamento(int idAgendamento) {
        String sql = """
            SELECT 1
            FROM avaliacao
            WHERE idAgendamento = ?
            LIMIT 1
        """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAgendamento);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void salvarAvaliacao(int idAgendamento, int nota, String texto) {
        String sql = """
        INSERT INTO avaliacao (idAgendamento, notaAvaliacao, textoAvaliacao, dataAvaliacao)
        VALUES (?, ?, ?, CURRENT_DATE)
    """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ps.setInt(2, nota);
            ps.setString(3, texto);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
