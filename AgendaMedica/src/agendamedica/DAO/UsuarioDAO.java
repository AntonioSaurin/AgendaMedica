/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Usuario;
import agendamedica.util.ConnectionFactory;

import java.sql.*;
public class UsuarioDAO {
    
    public Usuario login(String nome, String senha) {
        String sql = "{CALL sp_loginUsuario(?, ?)}";
        Usuario usuario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    // Outros dados se necessário
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
