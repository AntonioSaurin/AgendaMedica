/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Prontuario;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProntuarioDAO {
    public boolean criarProntuario(Prontuario prontuario, int idPaciente, int idMedico) {
        String sql = "{CALL sp_criarProntuario(?, ?, ?, ?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, prontuario.getNomePaciente());
            stmt.setString(2, prontuario.getRelatorio());
            stmt.setInt(3, idPaciente);
            stmt.setInt(4, idMedico);

            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
