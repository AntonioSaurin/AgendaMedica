/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.DAO;

import agendamedica.model.Administrador;
import agendamedica.util.ConnectionFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AdministradorDAO {

    public boolean cadastrarAdministrador(Administrador admin) {
        String sql = "{CALL sp_cadastrarAdministrador(?, ?, ?, ?, ?, ?)}";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getSenha());
            String dataStr = admin.getDtNascimento();
            stmt.setDate(3, java.sql.Date.valueOf(dataStr));
            stmt.setString(4, admin.getCpf());
            stmt.setString(5, admin.getSexo());
            stmt.setInt(6, admin.getCodAdm());

            sucesso = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}

