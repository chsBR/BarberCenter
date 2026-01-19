/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
/**
 *
 * @author 2004s
 * DAO responsável por salvar forma de pagamento dos serviços
 */
public class HistoricoPagamentoDAO {

    public void salvar(int idAgendamento, String formaPagamento) {
        String sql = """
        INSERT INTO historicopagamento
        (idAgendamento, formaPagamento)
        VALUES (?, ?)
    """;

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ps.setString(2, formaPagamento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
