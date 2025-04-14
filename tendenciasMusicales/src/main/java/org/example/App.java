package org.example;


import domain.entities.catalogo.Album;
import domain.entities.catalogo.Cancion;

public class App {
    public static void main(String[] args) {

        Album album = new Album();
        Cancion unaCancion = new Cancion("", album,2011);

    }
}
