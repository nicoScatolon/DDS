package domain.entities.catalogo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artista {
    private String nombre;
    private String email;
}

/*
----------PROPUESTA 1----------
    ARTISTA COMO UN STRING
Canción 1
nombre: "The scientist"
año: 2002
nombre Artista: "Coldplay"
nombre del Album: "..."

Canción 2
nombre: "Clocks"
año: 2010
nombre Artista: "coldPlay"
nombre del Album: "..."

Canción 3
nombre: "Fix You"
año: 2011
nombre Artista: "COLDPLAY"
nombre del Album: "..."

PROBLEMA DE CONSISTENCIA DE DATOS:
Cuando una variable tiene el mismo valor con distinto nombre,
ej "Coldplay, COLDPLAY y coldplay"
----------PROPUESTA 2----------
    ARTISTA COMO ENUMERADO

enum ARTISTA{
    COLDPLAY,
    ...
    ...
    ...
}

Canción 1
nombre: "The scientist"
año: 2002
nombre Artista: ARTISTA.COLDPLAY
nombre del Album: "..."

Canción 2
nombre: "Clocks"
año: 2010
nombre Artista: ARTISTA.COLDPLAY
nombre del Album: "..."

Canción 3
nombre: "Fix You"
año: 2011
nombre Artista: ARTISTA.COLDPLAY
nombre del Album: "..."


PROBLEMA DE HARDCODEO (No extensible), DISPONIBILIDAD y MANTENIBILIDAD;
Los valores de los enums son fijos y finitos, es decir si quiero
agregar un nuevo artista, debo agregarlo por código, los enums se escriben,
compilan y luego se pueden utilizar.

Además, los enumerados no tienen atributos, por lo que si se requiere
asociar información al artista en sí, no se podrá. Solo se pueden usar
como valores.

----------PROPUESTA 3----------
    ARTISTA COMO CLASE

-Artista 1:
    -Nombre "Colplay"
    -...
    -...


Canción 1
nombre: "The scientist"
año: 2002
nombre Artista: Artista 1
nombre del Album: "..."

Canción 2
nombre: "Clocks"
año: 2010
nombre Artista: Artista 1
nombre del Album: "..."

Canción 3
nombre: "Fix You"
año: 2011
nombre Artista: Artista 1
nombre del Album: "..."
 */
