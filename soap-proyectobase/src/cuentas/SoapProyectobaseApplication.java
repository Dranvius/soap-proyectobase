package co.vinni.soapproyectobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.vinni.soapproyectobase.utilidades.utilizadadCuentas;

/**
 * author Vinni 2023
 */
@SpringBootApplication
public class SoapProyectobaseApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SoapProyectobaseApplication.class, args);
        System.out.println("proyecto base");

        utilizadadCuentas cuentas = new utilizadadCuentas();

        cuentas.cuentas_existentes(0);

    }

}
