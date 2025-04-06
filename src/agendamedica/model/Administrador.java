/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.model;

/**
 *
 * @author anton
 */
public class Administrador extends Usuario{
    protected int codAdm;

    public Administrador(int codAdm, int id, String nome, String senha, String cpf, String dtNascimento, String sexo) {
        super(id, nome, senha, cpf, dtNascimento, sexo);
        this.codAdm = codAdm;
    }

    public int getCodAdm() {
        return codAdm;
    }

    public void setCodAdm(int codAdm) {
        this.codAdm = codAdm;
    }
    
    
}
