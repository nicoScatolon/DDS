package domain.entities.rankings;

import domain.entities.catalogo.Cancion;

public interface RankingService {
    Integer obtenerRankingTendencia(Cancion cancion);
}
