/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.controller;
import agendamedica.model.Paciente;
/**
 *
 * @author anton
 */
public class PacienteController extends Paciente{
    public PacienteController(int codPaciente, int id, String nome, String senha, String cpf, String dtNascimento, String sexo) {
        super(codPaciente, id, nome, senha, cpf, dtNascimento, sexo);
    }
    
    public void cadastrarPaciente() {
        
    }
}
