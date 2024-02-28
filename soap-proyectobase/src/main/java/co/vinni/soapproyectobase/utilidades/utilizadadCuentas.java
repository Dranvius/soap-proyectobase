package co.vinni.soapproyectobase.utilidades;
import co.vinni.soapproyectobase.entidades.Cuenta;
import java.util.*;
import co.vinni.soapproyectobase.entidades.tipo_cuenta_inf;
import co.vinni.soapproyectobase.entidades.Cuenta;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class utilizadadCuentas {

    //Cuando se crea una cuenta viene con una persona asociada
    //La busqueda se realizara por personas


    //COntrolador va retirar - consignar


    //----------------------------------------------------------------------------------------------------------Funciones Auxiliares
    // FUnciòn utilziada para saber si el cliente existe en el sistema

    public boolean cuentasExistente(Cuenta cuenta_usuario){

        //RUta de la carpeta
        String rute_cuentas = "/home/sergio/Documentos/proyectos/arquitectura/soap-proyectobase/src/cuentas";

        //!Instancia de entidad FILE que permite verificar que cuentas estan en la carpeta
        File carpeta = new File(rute_cuentas);
        File[] archivos = carpeta.listFiles();
        String usuario = cuenta_usuario.getCliente().getCedula()+".txt";


        if(archivos != null){
            for(int i = 0; i < archivos.length; i++) {

                System.out.println(archivos[i].getName());
                String archivo_name = archivos[i].getName().toString();


                if(usuario.equals(archivo_name)){

                    System.out.println(cuenta_usuario.getCliente().getNombre()+".txt");
                    System.out.println(archivos[i].getName());

                    return true;
                }

            }

        }else {
            System.out.println("No existen cuentas");
            return false;
        }
        return false;
    }

    // Funciòn utilizada para saber si existe el id de la cuenta del usuario -- DEVUELVE LA CUENTA
    public tipo_cuenta_inf existeCuentaId (int id,Cuenta cuenta){

        if(cuenta.getCuenta().getId_cuenta() == id){

            return cuenta.getCuenta();

        }
        return null;
    }


    // FUNCIÒN UTILIZADA PARA DEVOLVER EL BOOLEANO
    public boolean existeCuentaidBoolean (int id,Cuenta cuenta){

        System.out.println(id);
        System.out.println(cuenta.getCuenta().getId_cuenta() == id);

        return (cuenta.getCuenta().getId_cuenta() == id) ? true:false;

    }

    //FUNCIÒN UTILIZADA PARA QUITAR DINERO EN UNA CUENTA
    public void diferenciaEnvio (int valor, int id,Cuenta cuenta){

            //Definir la cuenta
            tipo_cuenta_inf cuentaDiferencia =  existeCuentaId(id,cuenta);

            //establecer nuevo valor de la cuenta

            cuentaDiferencia.setSaldo(cuentaDiferencia.getSaldo() - valor);

            //Respuesta de la funciòn

            System.out.println("TX realizada con exito, para la cuenta "+cuenta.getCliente().getNombre()+" Su saldo es de : " +cuentaDiferencia.getSaldo());
    }

    //FUNCIÒN UTILIZADA PARA SUMAR DINERO EN UNA CUENTA
    public void recibirDinero (int valor, int id,Cuenta cuenta){

        //Definir la cuenta
        tipo_cuenta_inf cuenta_suma =  existeCuentaId(id,cuenta);

        //establecer nuevo valor de la cuenta

        cuenta_suma.setSaldo(cuenta_suma.getSaldo() + valor);

        //Respuesta de la funciòn

        System.out.println("TX realizada con exito, para la cuenta "+cuenta.getCliente().getNombre()+" Su saldo es de : " +cuenta_suma.getSaldo());
    }

    //FUNCIÒN UTILIZADA PARA REALIZAR UNA BUSQUEDA POR CEDULA
    public File busquedaPorCedula(String cedula){

        //RUta de la carpeta
        String rute_cuentas = "/home/sergio/Documentos/proyectos/arquitectura/soap-proyectobase/src/cuentas";

        //!Instancia de entidad FILE que permite verificar que cuentas estan en la carpeta
        File carpeta = new File(rute_cuentas);
        File[] archivos = carpeta.listFiles();
        cedula = cedula +".txt";

        if(archivos != null){

            for(int i = 0; i < archivos.length; i++) {


                String archivo_name = archivos[i].getName().toString();
                if(cedula.equals(archivo_name)){
                    return archivos[i];
                }

            }

        }else {
            System.out.println("No existen cuentas");
            return null;
        }
        return null;
    }

    //Función utilizada para convertir un String a un objeto


    public static Map<String, Object> conversionHash(String input) {
        Map<String, Object> keyValueMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+)=([^,]+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2).trim();

            if (value.startsWith("Persona") || value.startsWith("tipo_cuenta_inf")) {
                Map<String, Object> innerMap = parseObject(value);
                keyValueMap.put(key, innerMap);
            } else {
                keyValueMap.put(key, value);
            }
        }

        return keyValueMap;
    }

    private static Map<String, Object> parseObject(String objectString) {
        // Check if the objectString contains parentheses
        if (objectString.contains("(") && objectString.contains(")")) {
            Map<String, Object> innerMap = new HashMap<>();

            // Extract object name and properties
            String[] parts = objectString.split("\\(");
            String objectName = parts[0];
            String propertiesString = parts[1].substring(0, parts[1].lastIndexOf(')'));

            // Split properties and add them to the map
            String[] properties = propertiesString.split(", ");
            for (String property : properties) {
                String[] keyValue = property.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                // Check if the value represents another object
                if (value.contains("(") && value.contains(")")) {
                    innerMap.put(key, parseObject(value));
                } else {
                    innerMap.put(key, value);
                }
            }

            // Check if the object is a Persona and process its data accordingly
            if (objectName.equals("Persona")) {
                String[] personaData = propertiesString.split(", ");
                Map<String, String> personaMap = new HashMap<>();
                for (String data : personaData) {
                    String[] keyValue = data.split("=");
                    personaMap.put(keyValue[0], keyValue[1]);
                }
                innerMap.put("persona", personaMap);
            }

            return innerMap;
        } else {
            // If the objectString does not contain parentheses, return it as is
            Map<String, Object> result = new HashMap<>();
            result.put("value", objectString);
            return result;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUnciones de almacenamiento.
    public static boolean guardar(String archivo, Object objeto){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(objeto);
        } catch (IOException e) {
            return false;
        }
        return true;

    }
    public static Object obtener(String archivo){
        Object objetoCOnsultado;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            //System.out.println(ois.readObject());
            objetoCOnsultado = ois.readObject();
            return objetoCOnsultado;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


}
