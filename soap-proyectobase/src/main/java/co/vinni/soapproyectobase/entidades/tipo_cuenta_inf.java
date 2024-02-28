package co.vinni.soapproyectobase.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import co.vinni.soapproyectobase.entidades.Persona;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class tipo_cuenta_inf {

    private int id_cuenta;
    private int tipo_cuenta;
    private int saldo;
    private boolean estado;


}
