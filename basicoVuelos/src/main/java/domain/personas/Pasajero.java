package domain.personas;

import domain.lugares.Ciudad;
import domain.lugares.Pais;
import domain.viajes.Vuelo;

import java.util.List;

public class Pasajero extends Persona {
    private List<Vuelo> vuelos;
    private Integer nroDePasaporte;
    private Pais pais;

    public Integer cantVecesQueVisitaste(Ciudad unaCiudad){
        return (int) this.vuelos
                .stream()
                .filter(v -> v.tuDestinoEs(unaCiudad))
                .count();
    }
}
