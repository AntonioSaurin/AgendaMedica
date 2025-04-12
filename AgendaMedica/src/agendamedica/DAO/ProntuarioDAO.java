/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Prontuario;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    
     public static Prontuario buscarProntuarioPorCpf(String cpfBusca) throws SQLException {
          Prontuario prontuario = null;
         String sql = "SELECT p.nomePaciente, p.relatorio, p.id FROM Prontuario p JOIN Usuario u ON p.paciente_id = u.id WHERE u.cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, cpfBusca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nomePaciente");
            String relatorio = rs.getString("relatorio");
            prontuario = new Prontuario(id, nome, relatorio);
            
            }
        }
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar prontuário:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
        return prontuario;
    }
}
