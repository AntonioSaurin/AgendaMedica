/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Medico;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    
        public static int buscarIdPorNome(String nome) throws SQLException {
        String sql = "SELECT m.usuario_id FROM Medico m JOIN Usuario u ON m.usuario_id = u.id WHERE u.nome = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("usuario_id");
                return id;
            } else {
                throw new SQLException("Médico não encontrado");
            }
        }
    }
    
       public List<Medico> listarTodos() throws SQLException {
        List<Medico> lista = new ArrayList<>();

        String sql = "SELECT * FROM Medico m JOIN Usuario u ON m.usuario_id = u.id;";

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String crm = rs.getString("crm");
                String especializacao = rs.getString("especialisacao");
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                String dtNascimento = rs.getString("dt_Nascimento");
                String sexo = rs.getString("sexo");
                
                Medico medico = new Medico(crm, especializacao, id, nome, senha, cpf, dtNascimento, sexo);
                lista.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar médicos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
}
