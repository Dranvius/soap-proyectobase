package co.vinni.soapproyectobase.repositorios;

import co.vinni.soapproyectobase.entidades.Cuenta;

public interface RepositorioCuenta {

    public boolean registrarCuenta(Cuenta cuenta);

    public boolean enviarDinero(Cuenta cuenta_1,Cuenta cuenta_2,int id_1,int id_2,int valor);

}