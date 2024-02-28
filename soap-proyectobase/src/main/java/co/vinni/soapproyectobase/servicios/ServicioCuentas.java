package co.vinni.soapproyectobase.servicios;
import co.vinni.soapproyectobase.entidades.Cuenta;
import co.vinni.soapproyectobase.repositorios.RepositorioCuenta;
import co.vinni.soapproyectobase.utilidades.utilizadadCuentas;
import org.springframework.beans.factory.annotation.Autowired;
import co.vinni.soapproyectobase.utilidades.utilizadadCuentas;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ServicioCuentas implements RepositorioCuenta, Serializable{


    utilizadadCuentas utilidades = new utilizadadCuentas();

    @Override
    public boolean registrarCuenta(Cuenta cuenta) {
        try {

            if(utilidades.cuentasExistente(cuenta)){
                System.out.println("La cuenta ya existe : "+cuenta.getCliente().getNombre());
                return false;
            }else{
                utilizadadCuentas.guardar(  "/home/sergio/Documentos/proyectos/arquitectura/soap-proyectobase/src/cuentas/"+cuenta.getCliente().getCedula()+".txt",cuenta.toString());
                System.out.println("Archivo creado con exito");
                return true;
            }
        }catch (Exception e){
            System.err.println("Error al registrar la cuenta: " + e.getMessage());
            return false;
        }

    }

    //Verificar còmo el usuario puede entrar la cuenta desde la consola
    @Override
    public boolean enviarDinero(Cuenta cuenta_1,Cuenta cuenta_2,int id_1,int id_2,int valor) {




        //Las cuenats existen
        if((utilidades.cuentasExistente(cuenta_1) && utilidades.cuentasExistente(cuenta_2)) && (utilidades.existeCuentaidBoolean(id_1 ,cuenta_1) && utilidades.existeCuentaidBoolean(id_2 ,cuenta_2) ) ){
          if(utilidades.existeCuentaId(id_1,cuenta_1).getSaldo() >= 0 &&  utilidades.existeCuentaId(id_1,cuenta_1).getSaldo() <= valor){

               System.out.println("El usuario envio envio la cantidad de a la cuenta con id " +utilidades.existeCuentaId(id_2,cuenta_2).getId_cuenta()+ " Saldo restante es de : "+(utilidades.existeCuentaId(id_1,cuenta_1).getSaldo() - valor));
               //Restaerle valores al usuario 1
               utilidades.diferenciaEnvio(valor,id_1,cuenta_1);
               //Sumarle dinero al usuario 2
              utilidades.recibirDinero(valor,id_2,cuenta_2);
            }else{
                System.out.println("EL usuario no tiene saldo para realizar el envio de dinero a la cuenta : "+utilidades.existeCuentaId(id_1,cuenta_1).getId_cuenta());
            }

        }else{

            System.out.println("Algunas de las dos cuentas no existe en el sistema");
            return false;
        }

        return true;

    }

}
