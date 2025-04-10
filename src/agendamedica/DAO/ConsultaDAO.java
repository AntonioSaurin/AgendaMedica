/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Consulta;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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
}
