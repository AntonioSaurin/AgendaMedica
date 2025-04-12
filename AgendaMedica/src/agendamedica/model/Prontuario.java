/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.model;

/**
 *
 * @author anton
 */
public class Prontuario {
    protected int id;
    protected String nomePaciente;
    protected String relatorio;

    public Prontuario(int id, String nomePaciente, String relatorio) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.relatorio = relatorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
    
    
}
