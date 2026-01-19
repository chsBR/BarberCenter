/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author 2004s DAO de Fidelidade
 */
public class FidelidadeDAO {

    /**
     * busca qtd de estrelas do cliente
     *
     * @param idCliente
     * @return qtdEstrelas
     */
    public int getEstrelas(int idCliente) {
        String sql = "SELECT estrelas FROM fidelizacao WHERE idCliente = ?";

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("estrelas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * adiciona estrela
     *
     * @param idCliente
     */
    public void adicionarEstrela(int idCliente) {
        String sql = """
            INSERT INTO fidelizacao (idCliente, estrelas)
            VALUES (?, 1)
            ON DUPLICATE KEY UPDATE estrelas = estrelas + 1
        """;

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * tira estrelas como forma de pagamento
     *
     * @param idCliente
     * @param quantidade
     * @return
     */
    public boolean debitarEstrelas(int idCliente, int quantidade) {
        String sql = """
            UPDATE fidelizacao
            SET estrelas = estrelas - ?
            WHERE idCliente = ? AND estrelas >= ?
        """;

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantidade);
            ps.setInt(2, idCliente);
            ps.setInt(3, quantidade);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int buscarTotalEstrelas(int idCliente) {
        String sql = "SELECT IFNULL(SUM(estrelas), 0) FROM fidelizacao WHERE idCliente = ?";

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
