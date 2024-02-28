package co.vinni.soapproyectobase.controladores;


import co.vinni.soapproyectobase.entidades.Cuenta;
import co.vinni.soapproyectobase.servicios.ServicioCuentas;
public class ControladorCuentas {

//Realizar tranzaciòn

    public boolean transferencia (Cuenta cuenta1, Cuenta cuenta2, int valor){

        ServicioCuentas servicio = new ServicioCuentas();

        cuenta1.getCuenta().setSaldo(cuenta1.getCuenta().getSaldo() - valor);

        //Persistencia LLAMADO DEL CONTROLADOR
        //servicio.modificar(cuenta1);


        //cuenta2.setSaldo(cuenta2.getSaldo() + valor);

        //servicio.modificar(cuenta2);

        return true ;
    }

}
