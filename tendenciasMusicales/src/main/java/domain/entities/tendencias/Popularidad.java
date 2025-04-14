package domain.entities.tendencias;

import domain.entities.catalogo.Cancion;

public abstract class Popularidad {

    public abstract String leyenda(Cancion cancion);
    public abstract void reproducir(Cancion cancion);

    protected abstract String icono();

    public String detalle(Cancion cancion){
        return this.icono() + " - " + this.leyenda(cancion);
    }
}
