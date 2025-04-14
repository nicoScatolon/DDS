package domain.lugares;

import domain.viajes.Vuelo;

import java.time.LocalDate;
import java.util.List;

public class Aeropuerto {
    private String nombre;
    private String codigoInternacional;
    private Ciudad ciudad;
    private List<Vuelo> vuelos;

    public List<Vuelo> vuelosQueLlegaronElDia(LocalDate dia){
        //ToDo
        return null;
    }

    public Integer cantVuelosQueLlegaronElDia(LocalDate unDia){
        //ToDo
        return 0;
    }

    public Integer cantVuelosQuePartieronElDia(LocalDate unDia){
        //ToDo
        return 0;
    }
}
