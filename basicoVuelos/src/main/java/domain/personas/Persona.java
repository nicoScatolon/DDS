package domain.personas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected Integer nroDeDocumento;
    protected TipoDeDocumento tipoDeDocumento;
}
