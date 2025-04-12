/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.model;

/**
 *
 * @author anton
 */
public class Medico extends Usuario{
    protected String crm;
    protected String especializacao;

    public Medico(String crm, String especializacao, int id, String nome, String senha, String cpf, String dtNascimento, String sexo) {
        super(id, nome, senha, cpf, dtNascimento, sexo);
        this.crm = crm;
        this.especializacao = especializacao;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}
