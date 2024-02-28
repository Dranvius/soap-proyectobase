package co.vinni.soapproyectobase.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//No hay relaciòn de herencia
//Aosicaciòn o extencion

public class Cuenta implements Serializable  {
    private int id;
    private tipo_cuenta_inf cuenta;
    private Persona cliente;
}

