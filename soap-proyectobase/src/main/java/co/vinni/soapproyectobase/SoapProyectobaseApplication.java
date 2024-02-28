package co.vinni.soapproyectobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import co.vinni.soapproyectobase.servicios.ServicioCuentas;
import co.vinni.soapproyectobase.entidades.Cuenta;
import co.vinni.soapproyectobase.entidades.tipo_cuenta_inf;
import co.vinni.soapproyectobase.entidades.Persona;
import co.vinni.soapproyectobase.utilidades.utilizadadCuentas;
import java.io.*;

/**
 * author
 */
@SpringBootApplication
public class SoapProyectobaseApplication {

    public static void main(String[] args) {

        //SpringApplication.run(SoapProyectobaseApplication.class, args);
        ServicioCuentas servicio = new ServicioCuentas();

        //Instancia de scanner para opcion

        Scanner opcion = new Scanner(System.in);
        Scanner entradas = new Scanner(System.in);

        //Instancia de scanner para opcion

        //Scanner opcion = new Scanner(System.in);


    //Creaciòn de usuario

        Persona PERSONA_1 = new Persona(1023974646,25,"sergio","Linares");
        tipo_cuenta_inf tipo_cuenta_1 = new tipo_cuenta_inf(1,1,2000,true);
        Cuenta cuenta_1 = new Cuenta(1,tipo_cuenta_1,PERSONA_1);

        Persona PERSONA_2 = new Persona(102333333,20,"ruben","dario");
        tipo_cuenta_inf tipo_cuenta_2 = new tipo_cuenta_inf(2,1,4000,true);
        Cuenta cuenta_2 = new Cuenta(2,tipo_cuenta_2,PERSONA_2);

        servicio.registrarCuenta(cuenta_1);
        servicio.registrarCuenta(cuenta_2);

        //System.out.println(utilizadadCuentas.obtener("/home/sergio/Documentos/proyectos/arquitectura/soap-proyectobase/src/cuentas/1023974646.txt"));
        //System.out.println(utilizadadCuentas.obtener("/home/sergio/Documentos/proyectos/arquitectura/soap-proyectobase/src/cuentas/102333333.txt"));

        utilizadadCuentas funciones = new utilizadadCuentas();

        System.out.println(funciones.conversionHash(utilizadadCuentas.obtener(funciones.busquedaPorCedula("1023974646").toString()).toString()));


        //.get("cuenta").

    }

}
