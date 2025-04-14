package domain.entities.tendencias;

import domain.entities.catalogo.Cancion;
import domain.entities.catalogo.helpers.Icono;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EnTendencia extends Popularidad {
    @Setter
    private static Integer cantHrsSinEscucharParaBajarPopularidad = 24;

    @Override
    public void reproducir(Cancion cancion) {
        if(this.pasaronMasDe(cantHrsSinEscucharParaBajarPopularidad, cancion.getUltReproduccion())){
            cancion.setPopularidad(new Normal());
        }

    }

    public Boolean pasaronMasDe(Integer horasReq, LocalDateTime fechaInicial){
        return ChronoUnit.HOURS.between(fechaInicial, LocalDateTime.now()) >= horasReq;
    }

    @Override
    protected String icono() {
        return Icono.FIRE.texto();
    }

    @Override
    public String leyenda(Cancion cancion) {
        return cancion.getNombre() + " - "
                + cancion.getAlbum().getArtista().getNombre() + " ( "
                + cancion.getAlbum().getNombre() + " - "
                + cancion.getAlbum().getAnio() + " ) ";
    }
    public String armarDetalle(Cancion cancion){
        return this.icono() + " - " + this.leyenda(cancion);
    }
}
