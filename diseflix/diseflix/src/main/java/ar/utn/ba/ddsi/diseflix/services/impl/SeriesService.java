package ar.utn.ba.ddsi.diseflix.services;

import ar.utn.ba.ddsi.diseflix.models.dtos.output.SerieOutputDTO;
import ar.utn.ba.ddsi.diseflix.models.entities.Serie;
import ar.utn.ba.ddsi.diseflix.models.repositories.ISeriesRepository;

import java.util.List;

public class SeriesService {
    private ISeriesRepository seriesRepository;

    public List<SerieOutputDTO> buscarTodas(){
     return this.seriesRepository
             .findAll()
             .stream()
             .map(this::serieOutPutDTO) // es lo mismo que: .map(s -> this.serieOutPutDTO(s))
             .toList();
    }

    //Esto se podría hacer con un Builder
    private SerieOutputDTO serieOutPutDTO(Serie serie){
        SerieOutputDTO dto = new SerieOutputDTO();
        dto.setId(serie.getId());
        dto.setNombre(serie.getNombre());
        dto.setResenia(serie.getResenia());
        dto.setCartelera(serie.getCartelera());
        dto.setHorasVisualizacion(serie.horasVisualizaciones());
        dto.setIdGenero(serie.getGenero().getId());
        return dto;
    }

}
