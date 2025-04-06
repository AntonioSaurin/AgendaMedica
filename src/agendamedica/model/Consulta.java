
package agendamedica.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    protected int id;
    protected LocalDate dtConsulta;
    protected LocalTime hrConsulta;
    protected String Status;
    protected String tipoConsulta;

    public Consulta(int id, LocalDate dtConsulta, LocalTime hrConsulta, String Status, String tipoConsulta) {
        this.id = id;
        this.dtConsulta = dtConsulta;
        this.hrConsulta = hrConsulta;
        this.Status = Status;
        this.tipoConsulta = tipoConsulta;
    }
    
    
}
