package domain.lugares;

import domain.viajes.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ciudad {
    private String nombre;
    private Pais pais;
    private List<Aeropuerto> aeropuertos;

    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.aeropuertos = new ArrayList<Aeropuerto>();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public Integer cantDeAeropuertos(){
        return this.aeropuertos.size();
    }

    public void agregarAeropuertos(Aeropuerto ... aeropuertos){
        Collections.addAll(this.aeropuertos, aeropuertos);
    }

    public Integer cantPasajerosQueLlegaronElDia(LocalDate unDia){
        List<Vuelo> vuelosQueLlegaronEseDia = this.aeropuertos.
                stream()
                .flatMap(a -> a.vuelosQueLlegaronElDia(unDia).stream())
                .collect(Collectors.toList());
        return vuelosQueLlegaronEseDia
                .stream()
                .mapToInt(Vuelo::cantPasajeros) //A cada vuelo se le pide la cantDePasajeros, sirve solo cuando no recibe parametros
                //.mapToInt(v -> v.cantPasajeros) //Es equivalente a la superior
                .sum();
    }
}
