package ar.utn.ba.ddsi.logger;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//USAMOS UN PATRÓN DE DISEÑO PARA QUE SEA MAS FACIL CONFIGURAR ALGUNOS ATRIBUTOS Y DEMAS
//PATRÓN BUILDER, PATRON CREACIONAL, FACILITA LA INSTANCACION DE LA CLASE
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String msg;
    private String stackTrace; //Cadena completa del error(linea, código,clase, etc)
    private LocalDateTime timestamp;

    //ESTO SE LO LLAMA SOBRECARGA DE METODOS:
    //Cuando tengo un mismo nombre de metodo que recibe otra cantidad de parametros

    public static Error of(String msg){
        return Error
                .builder()
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Error of(String msg, String stackTrace) {
        return Error
                .builder()
                .msg(msg)
                .stackTrace(stackTrace)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Error of(String msg, String stackTrace, LocalDateTime timestamp) {
        return Error
                .builder()
                .msg(msg)
                .stackTrace(stackTrace)
                .timestamp(timestamp)
                .build();
    }
}
