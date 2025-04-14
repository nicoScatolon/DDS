package domain.entities.catalogo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album {
    private String nombre;
    private Integer anio;
    private Artista artista;
}
