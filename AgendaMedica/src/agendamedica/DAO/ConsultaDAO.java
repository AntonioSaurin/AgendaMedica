/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Consulta;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConsultaDAO {
    public boolean marcarConsulta(Consulta consulta, int idMedico, int idPaciente) {
        String sql = "{CALL sp_marcarConsulta(?, ?, ?, ?, ?, ?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            LocalDate dataStr = consulta.getDtConsulta();
            stmt.setDate(1, java.sql.Date.valueOf(dataStr));
            LocalTime timeStr = consulta.getHrConsulta();
            stmt.setTime(2, java.sql.Time.valueOf(timeStr));
            stmt.setString(3, consulta.getStatus());
            stmt.setString(4, consulta.getTipoConsulta());
            stmt.setInt(5, idMedico);
            stmt.setInt(6, idPaciente);

            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public boolean desmarcarConsulta(int idConsulta) {
        String sql = "{CALL sp_desmarcarConsulta(?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idConsulta);
            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
    
     public List<Consulta> buscarConsultasPorMedico(int idMedico) throws SQLException {
        List<Consulta> lista = new ArrayList<>();

        String sql =  "SELECT c.id, c.dtConsulta, c.horaConsulta, c.`status`, c.tipoConsulta, u.nome AS nome_paciente FROM consulta c JOIN usuario u ON c.paciente_id = u.id WHERE c.medico_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idMedico); 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate data = LocalDate.parse(rs.getString("dtConsulta"));
                LocalTime hora = LocalTime.parse(rs.getString("horaConsulta"));
                String status = rs.getString("status");
                String tipo = rs.getString("tipoConsulta");

                Consulta consulta = new Consulta(id, data, hora, status, tipo);
                lista.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar consultas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
     
        public List<Consulta> buscarConsultasPorCpf(String cpf) {
        List<Consulta> lista = new ArrayList<>();

        String sql = "SELECT c.id, c.dtConsulta, c.horaConsulta, c.status, c.tipoConsulta " +
                     "FROM Consulta c " +
                     "JOIN Paciente p ON c.paciente_id = p.usuario_id " +
                     "JOIN Usuario u ON p.usuario_id = u.id " +
                     "WHERE u.cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("id");
                LocalDate data = rs.getDate("dtConsulta").toLocalDate();
                LocalTime hora = rs.getTime("horaConsulta").toLocalTime();
                String status = rs.getString("status");
                String tipo = rs.getString("tipoConsulta");

                Consulta consulta = new Consulta(ID, data, hora, status, tipo);
                lista.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar consultas por CPF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
}
