/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamedica.controller;
import agendamedica.model.Consulta;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author anton
 */
public class ConsultaController extends Consulta{

    public ConsultaController(int id, LocalDate dtConsulta, LocalTime hrConsulta, String Status, String tipoConsulta) {
        super(id, dtConsulta, hrConsulta, Status, tipoConsulta);
    }
    
}
