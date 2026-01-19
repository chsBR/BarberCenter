/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Servico;

/**
 *
 * @author 2004s Classe DAO de Serviço
 */
public class ServicoDAO {

    /**
     * lista todos serviços
     * @return lista de serviços
     */
    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();

        String sql = """
            SELECT idServico, nome_Servico, valor_Servico, tempoEstimado
            FROM servico
            ORDER BY nome_Servico
        """;

        try (
                 Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                double valor = rs.getBigDecimal("valor_Servico").doubleValue();
                Servico s = new Servico(
                        rs.getInt("idServico"),
                        rs.getString("nome_Servico"),
                        valor,
                        rs.getString("tempoEstimado")
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * busca valor do serviço atraves do id
     * @param idServico
     * @return valor do serviço
     */
    public double buscarValorPorId(int idServico) {
        String sql = "SELECT valor_Servico FROM servico WHERE idServico = ?";
        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idServico);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("valor_Servico");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
