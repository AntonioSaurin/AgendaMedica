/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.model;

/**
 *
 * @author anton
 */
public class historicoMedico {
    protected int id;
    protected String nomePaciente;
    protected String relatorio;

    public historicoMedico(int id, String nomePaciente, String relatorio) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.relatorio = relatorio;
    }
    
    
}
