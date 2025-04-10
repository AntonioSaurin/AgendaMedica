/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Medico;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MedicoDAO {
    public boolean cadastrarMedico(Medico medico) {
        String sql = "{CALL sp_cadastrarMedico(?, ?, ?, ?, ?, ?, ?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getSenha());
            String dataStr = medico.getDtNascimento();
            stmt.setDate(3, java.sql.Date.valueOf(dataStr));
            stmt.setString(4, medico.getCpf());
            stmt.setString(5, medico.getSexo());
            stmt.setString(6, medico.getCrm());
            stmt.setString(7, medico.getEspecializacao());

            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
