/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.controller;
import agendamedica.model.Prontuario;
import agendamedica.DAO.ProntuarioDAO;
import java.sql.SQLException;
/**
 *
 * @author anton
 */
public class ProntuarioController extends Prontuario{
    
    private ProntuarioDAO dao = new ProntuarioDAO();
    public ProntuarioController(int id, String nomePaciente, String relatorio) {
        super(id, nomePaciente, relatorio);
    }
    
    public void buscarProntuarioPorCPF( String cpfBusca) throws SQLException {
        ProntuarioDAO.buscarProntuarioPorCpf(cpfBusca);
    }
}
