
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(LocalDate dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public LocalTime getHrConsulta() {
        return hrConsulta;
    }

    public void setHrConsulta(LocalTime hrConsulta) {
        this.hrConsulta = hrConsulta;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
    
    
}
