package dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2004s Classe DAO de Agendamento
 */
public class AgendamentoDAO {

    public List<Object[]> listarPorCliente(int idCliente) {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT a.idagendamentos,
                   c.nome AS barbeiro,
                   a.data,
                   a.status_Agendamento
            FROM agendamento a
            JOIN barbeiro b ON a.idBarbeiro = b.idBarbeiro
            JOIN cliente c ON c.idCliente = b.idCliente
            WHERE a.idCliente = ?
            ORDER BY a.data DESC
        """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Timestamp ts = rs.getTimestamp("data");

                String data = new SimpleDateFormat("dd/MM/yyyy").format(ts);
                String hora = new SimpleDateFormat("HH:mm").format(ts);

                lista.add(new Object[]{
                    rs.getInt("idagendamentos"),
                    rs.getString("barbeiro"),
                    data,
                    hora,
                    rs.getString("status_Agendamento")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Método responsável por cancelar agendamentos.
     *
     * @param idAgendamento
     */
    public void cancelarAgendamento(int idAgendamento) {
        String sql = """
            UPDATE agendamento
            SET status_Agendamento = 'Cancelado'
            WHERE idagendamentos = ?
        """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsável por concluir agendamentos.
     *
     * @param idAgendamento
     */
    public void concluirAgendamento(int idAgendamento) {
        String sql = """
        UPDATE agendamento
        SET status_Agendamento = 'Concluído'
        WHERE idagendamentos = ?
    """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por filtrar dados do histórico
     *
     * @param idCliente
     * @param status
     * @return
     */
    public List<Object[]> listarPorClienteEStatus(int idCliente, String status) {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT a.idagendamentos,
                   c.nome AS barbeiro,
                   DATE(a.data) AS data,
                   TIME(a.data) AS horario,
                   a.status_Agendamento
            FROM agendamento a
            JOIN barbeiro b ON a.idBarbeiro = b.idBarbeiro
            JOIN cliente c ON c.idCliente = b.idCliente
            WHERE a.idCliente = ?
              AND a.status_Agendamento = ?
            ORDER BY a.data DESC
        """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("idagendamentos"),
                    rs.getString("barbeiro"),
                    rs.getDate("data"),
                    rs.getTime("horario"),
                    rs.getString("status_Agendamento")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Metódo responsável por salvar agendamento no banco de dados
     *
     * @param idCliente
     * @param idBarbeiro
     * @param idServico
     * @param dataHora
     * @return
     */
    public boolean salvarAgendamento(int idCliente, int idBarbeiro, int idServico, Timestamp dataHora) {

        String sql = """
        INSERT INTO agendamento (idCliente, idBarbeiro, idServico, data, status_Agendamento)
        VALUES (?, ?, ?, ?, 'Agendado')
    """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setInt(2, idBarbeiro);
            ps.setInt(3, idServico);
            ps.setTimestamp(4, dataHora);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lista os horários ocupados
     *
     * @param idBarbeiro
     * @param data
     * @return
     */
    public List<String> listarHorariosOcupados(int idBarbeiro, Date data) {
        List<String> horarios = new ArrayList<>();

        String sql = """
        SELECT TIME(data) AS horario
        FROM agendamento
        WHERE idBarbeiro = ?
        AND DATE(data) = ?
        AND status_Agendamento <> 'Cancelado'
    """;

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBarbeiro);
            ps.setDate(2, new java.sql.Date(data.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                horarios.add(rs.getString("horario"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return horarios;
    }

    /**
     * Filtra os agendamentos da conta de barbeiro
     *
     * @param idBarbeiro
     * @return
     */
    public List<Object[]> listarPorBarbeiro(int idBarbeiro) {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT a.idagendamentos,
               c.nome AS cliente,
               a.data,
               a.status_Agendamento
        FROM agendamento a
        JOIN cliente c ON a.idCliente = c.idCliente
        WHERE a.idBarbeiro = ?
        ORDER BY a.data DESC
    """;

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBarbeiro);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Timestamp ts = rs.getTimestamp("data");

                String data = new SimpleDateFormat("dd/MM/yyyy").format(ts);
                String hora = new SimpleDateFormat("HH:mm").format(ts);

                lista.add(new Object[]{
                    rs.getInt("idagendamentos"),
                    rs.getString("cliente"),
                    data,
                    hora,
                    rs.getString("status_Agendamento")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Object[]> listarParaGerente(int idBarbeiro) {
        List<Object[]> lista = new ArrayList<>();
        String sql = """
            SELECT a.idagendamentos,
                   c.nome AS cliente,
                   a.data,
                   a.status_Agendamento
            FROM agendamento a
            JOIN cliente c ON a.idCliente = c.idCliente
        """;

        if (idBarbeiro > 0) {
            sql += " WHERE a.idBarbeiro = ? ";
        }

        sql += " ORDER BY a.data DESC ";

        try ( Connection conn = Conexao.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            if (idBarbeiro > 0) {
                ps.setInt(1, idBarbeiro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Timestamp ts = rs.getTimestamp("data");

                String data = new SimpleDateFormat("dd/MM/yyyy").format(ts);
                String hora = new SimpleDateFormat("HH:mm").format(ts);

                lista.add(new Object[]{
                    rs.getInt("idagendamentos"),
                    rs.getString("cliente"),
                    data,
                    hora,
                    rs.getString("status_Agendamento")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Notificação de agendamento
     *
     * @param idCliente
     * @return
     */
    public boolean clienteTemAgendamentoHoje(int idCliente) {
        String sql = """
        SELECT 1
        FROM agendamento
        WHERE idCliente = ?
          AND DATE(data) = CURRENT_DATE
          AND status_Agendamento IN ('Agendado', 'Confirmado')
        LIMIT 1
    """;

        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * busca id do cliente atraves do agendamento selecionado
     *
     * @param idAgendamento
     * @return id do cliente
     */
    public int buscarIdClientePorAgendamento(int idAgendamento) {
        String sql = "SELECT idCliente FROM agendamento WHERE idagendamentos = ?";
        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idCliente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Busca id do serviço para saber o tipo de serviço do agendamento
     * selecionado
     *
     * @param idAgendamento
     * @return id do serviço
     */
    public int buscarIdServicoPorAgendamento(int idAgendamento) {
        String sql = "SELECT idServico FROM agendamento WHERE idagendamentos = ?";
        try ( Connection con = Conexao.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAgendamento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idServico");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
