/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Paciente;
import agendamedica.util.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PacienteDAO {
    public boolean cadastrarPaciente(Paciente paciente) {
        String sql = "{CALL sp_cadastrarPaciente(?, ?, ?, ?, ?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getSenha());
            String dataStr = paciente.getDtNascimento();
            stmt.setDate(3, java.sql.Date.valueOf(dataStr));
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getSexo());

            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
    
    public static int buscarIdPorCpf(String cpf) throws SQLException {
        String sql = "SELECT p.usuario_id FROM Paciente p JOIN Usuario u ON p.usuario_id = u.id WHERE u.cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               int id = rs.getInt("usuario_id");
               return id;
            } else {
                throw new SQLException("Paciente não encontrado");
            }
        }
    }
}
