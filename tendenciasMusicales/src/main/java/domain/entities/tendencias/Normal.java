package domain.entities.tendencias;

import domain.entities.catalogo.Cancion;
import domain.entities.catalogo.helpers.Icono;
import lombok.Setter;

public class Normal extends Popularidad {
    @Setter
    private static Integer cantReproduccionesMinEN_AUGE = 1000;
    private Integer cantRepro = 0;

    @Override
    public void reproducir(Cancion cancion) {
        cantRepro++;
        if(this.cantRepro >= cantReproduccionesMinEN_AUGE){
            cancion.setPopularidad(new EnAuge());
        }
    }

    @Override
    protected String icono() {
        return Icono.MUSICAL_NOTE.texto();
    }

    @Override
    public String leyenda(Cancion cancion) {
        return cancion.getAlbum().getArtista().getNombre() + " - "
                + cancion.getAlbum().getNombre() + " - "
                + cancion.getNombre();
    }

    public String armarDetalle(Cancion cancion){
        return this.icono() + " - " + this.leyenda(cancion);
    }

}
