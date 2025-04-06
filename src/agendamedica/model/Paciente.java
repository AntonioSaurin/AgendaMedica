/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.model;

/**
 *
 * @author anton
 */
public class Paciente extends Usuario{
    protected int codPaciente;

    public Paciente(int codPaciente, int id, String nome, String senha, String cpf, String dtNascimento, String sexo) {
        super(id, nome, senha, cpf, dtNascimento, sexo);
        this.codPaciente = codPaciente;
    }

    public int getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }

}
