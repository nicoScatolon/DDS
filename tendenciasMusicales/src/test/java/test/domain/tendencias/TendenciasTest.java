package test.domain.tendencias;

import domain.entities.catalogo.Album;
import domain.entities.catalogo.Artista;
import domain.entities.catalogo.Cancion;
import domain.entities.email.EmailSender;
import domain.entities.rankings.RankingService;
import domain.entities.tendencias.EnAuge;
import domain.entities.tendencias.EnTendencia;
import domain.entities.tendencias.Normal;
import domain.entities.tendencias.Popularidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TendenciasTest {
    private Popularidad normal = new Normal();
    private Popularidad enauge = new EnAuge();
    private Popularidad entendencia = new EnTendencia();


    private Artista artista;
    private Album album;
    private Cancion cancion;

    private EmailSender emailSenderTrucho;
    private RankingService rankingServiceTrucho;

    @BeforeEach
    public void Init(){
        this.artista = new Artista();
        this.artista.setNombre("ColdPlay");
        this.artista.setEmail("coldplay@gmail.com");

        this.album = new Album();
        this.album.setNombre("A rush of Blood to the head");
        this.album.setAnio(2002);
        this.album.setArtista(artista);
        this.cancion = new Cancion("The scientist", this.album,2002);



        EnTendencia.setCantHrsSinEscucharParaBajarPopularidad(2);
        Normal.setCantReproduccionesMinEN_AUGE(3);
        EnAuge.setCantLikesMinParaPasarATendencia(3);
        EnAuge.setCantReproMinParaPasarATendencia(3);
        EnAuge.setCantLikesMaxDislikesSoportados(5);
        EnTendencia.setCantHrsSinEscucharParaBajarPopularidad(24);

        this.emailSenderTrucho = Mockito.mock(EmailSender.class);
        this.rankingServiceTrucho = Mockito.mock(RankingService.class);

        this.cancion.setEmailSender(emailSenderTrucho);
        this.cancion.setRankingService(rankingServiceTrucho);

        Mockito.doReturn(10).when(this.rankingServiceTrucho).
                obtenerRankingTendencia(this.cancion);

        Mockito.doNothing().when(this.emailSenderTrucho)
                .enviarMail(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
    }

    @Test
    @DisplayName("Test 1 - “The Scientist” recién se lanza (tiene popularidad normal).")
    public void cancionMuestraDetalleEnNormal(){

        String detalle = this.cancion.serReproducida();

        Assertions.assertEquals(1,this.cancion.getCantReproducciones());
        Assertions.assertTrue(detalle.contains(normal.detalle(cancion)));
        Assertions.assertFalse(detalle.contains(enauge.detalle(cancion)));
        Assertions.assertFalse(detalle.contains(entendencia.detalle(cancion)));
    }

    @Test
    @DisplayName("“The Scientist” está en auge por superar el mínimo de reproducciones esperadas.")
    public void cancionMuestraDetalleEnAuge(){
        this.cancion.serReproducida();
        this.cancion.serReproducida();

        String detalle = this.cancion.serReproducida();

        Assertions.assertEquals(3, this.cancion.getCantReproducciones());
        Assertions.assertTrue(detalle.contains(enauge.detalle(cancion)));
    }
    @Test
    @DisplayName("“The Scientist” es tendencia por récord de reproducciones y cantidad de personas que le gusta el tema.")
    public void cancionMuestraDetalleEnTendencia(){
        this.cancion.serReproducida();
        this.cancion.serReproducida();
        this.cancion.serReproducida();

        Assertions.assertInstanceOf(EnAuge.class, this.cancion.getPopularidad());

        this.cancion.recibirLike();
        this.cancion.recibirLike();
        this.cancion.recibirLike();
        this.cancion.recibirLike();

        this.cancion.serReproducida();
        this.cancion.serReproducida();
        this.cancion.serReproducida();
        this.cancion.serReproducida();

        String detalle = this.cancion.serReproducida();
        Assertions.assertTrue(detalle.contains(entendencia.detalle(cancion)));
    }

    @Test
    @DisplayName("“The Scientist” baja del auge por tener muchos dislikes.")
    public void cancionPasaDeEnAugeANormal(){
        this.cancion.serReproducida();
        this.cancion.serReproducida();
        this.cancion.serReproducida();

        Assertions.assertInstanceOf(EnAuge.class, this.cancion.getPopularidad());

        for (int i = 0; i < EnAuge.getCantLikesMaxDislikesSoportados(); i++) {
            this.cancion.recibirDisLike();
        }
        this.cancion.recibirDisLike();
        this.cancion.serReproducida();

        Assertions.assertInstanceOf(Normal.class, this.cancion.getPopularidad());
        Assertions.assertEquals(10, this.cancion.getRankingService().obtenerRankingTendencia(this.cancion));

        String cuerpoMail = "Felicidades "
                + cancion.getAlbum().getArtista().getNombre()
                + " tu canción "
                + cancion.getNombre()
                +"está en Tendencia. \n  Está en el puesto"
                + 10
                +"Del Ranking Global!!";


        Mockito.verify(this.emailSenderTrucho,Mockito.times(1))
                .enviarMail("youtube@google.com"
                        , cancion.getAlbum().getArtista().getEmail()
                        ,"Tu canción es tendencia"
                        ,cuerpoMail);
    }

    @Test
    @DisplayName("“The Scientist” era tendencia pero vuelve a ser normal por no ser escuchada en las últimas horas.")
    public void cancionPasarDeTendenciaANormal(){
    }
}