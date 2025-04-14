package domain.entities.catalogo;

import domain.entities.email.EmailSender;
import domain.entities.rankings.RankingService;
import domain.entities.tendencias.Normal;
import domain.entities.tendencias.Popularidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Cancion {
    private String nombre;
    private Album album;
    private Integer anioLanzamiento;
    private Integer cantLikes;
    private Integer cantDislikes;
    private Integer cantReproducciones;
    private LocalDateTime ultReproduccion;
    private Popularidad popularidad;

    private EmailSender emailSender = null;
    private RankingService rankingService = null;

    //static son variables que pertenecen a la clase, no a la instancia.
    // Y todas los objetos mirarán el mismo valor

    public Cancion(String nombre, Album album, Integer anioLanzamiento){
        this.cantReproducciones = 0;
        this.cantLikes = 0;
        this.cantDislikes = 0;
        this.nombre = nombre;
        this.album = album;
        this.anioLanzamiento = anioLanzamiento;
        this.popularidad = new Normal();

    }   //Debe recibir los minimos atributos, necesarios y obligatorios.

    public String serReproducida(){
        this.cantReproducciones++;
        this.ultReproduccion = LocalDateTime.now();
        this.popularidad.reproducir(this);
        String descripcion = this.popularidad.detalle(this);
        this.ultReproduccion = LocalDateTime.now();
        return descripcion;
    }

    public void recibirLike(){
        this.cantLikes++;
    }

    public void recibirDisLike(){
        this.cantDislikes++;
    }
}