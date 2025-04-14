package domain.entities.tendencias;

import domain.entities.catalogo.Cancion;
import domain.entities.catalogo.helpers.Icono;
import lombok.Getter;
import lombok.Setter;

public class EnAuge extends Popularidad {
    @Setter
    @Getter
    private static Integer cantReproMinParaPasarATendencia = 50000;
    @Setter
    @Getter
    private static Integer cantLikesMinParaPasarATendencia = 20000;
    @Setter
    @Getter
    private static Integer cantLikesMaxDislikesSoportados = 5000;
    private Integer cantRepro = 0;

    @Override
    public void reproducir(Cancion cancion) {
        cantRepro++;

        if(cancion.getCantDislikes()>cantLikesMaxDislikesSoportados){

                this.enviarMailEnTendencia(cancion);

            cancion.setPopularidad(new Normal());
        }
        else if (cantRepro >= cantReproMinParaPasarATendencia &&
                cancion.getCantLikes() >= cantLikesMinParaPasarATendencia) {
            cancion.setPopularidad(new EnTendencia());
        }
    }

    private void enviarMailEnTendencia(Cancion cancion){
        String cuerpoMail = "Felicidades "
                + cancion.getAlbum().getArtista().getNombre()
                + " tu canción "
                + cancion.getNombre()
                +"está en Tendencia. \n  Está en el puesto"
                + cancion.getRankingService().obtenerRankingTendencia(cancion)
                +"Del Ranking Global!!";

        cancion.getEmailSender().enviarMail("youtube@google.com"
                ,cancion.getAlbum().getArtista().getEmail()
                ,"Tu canción es tendencia"
                ,cuerpoMail);
    }

    @Override
    protected String icono() {
        return Icono.ROCKET.texto();
    }

    @Override
    public String leyenda(Cancion cancion) {
        return cancion.getAlbum().getArtista().getNombre() + " - "
                + cancion.getNombre() + " ( "
                + cancion.getAlbum().getNombre() + " - "
                + cancion.getAlbum().getAnio() + " ) ";
    }
    public  String armarDetalle(Cancion cancion){
        return this.icono() + " - " + this.leyenda(cancion);
    }
}
