import controlador.GestorHibernate;
import controlador.SessionHelper;
import entidades.EmpleadosEntity;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Galaterro
 */
class Main {

    public static void main(final String[] args) throws Exception {
        boolean salir = false;
        Session s = SessionHelper.getSessionFactory().openSession();
        GestorHibernate con = new GestorHibernate(s);
        int opcion = 0;
        do{
            System.out.println("****************************");
            System.out.println("* 1 -> Insertar Empleado.  *");
            System.out.println("* 2 -> Modificar Empleado. *");
            System.out.println("* 3 -> Borrar Empleado.    *");
            System.out.println("* 4 -> Buscar Empleado.    *");
            System.out.println("* 5 -> Salir.              *");
            System.out.println("****************************");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            try{
                opcion = Integer.parseInt(br.readLine());
            }catch(IOException ex){
                System.out.println("Error al leer la opción");
            }

            switch(opcion){
                case 1:
                    try{
                        System.out.println("Escribe el dni: ");
                        short dni = Short.parseShort(br.readLine());
                        System.out.println("Escribe el nombre: ");
                        String nombre = br.readLine();
                        System.out.println("Escribe el telefono: ");
                        int telefono = Integer.parseInt(br.readLine());
                        EmpleadosEntity emple = new EmpleadosEntity(dni, nombre, telefono);
                        con.insertarEmpleado(emple);
                    }catch(IOException ex){
                        System.out.println("Error al leer algun dato");
                    }
                    break;
                case 2:
                    try{
                        System.out.println("Escribe el dni del empleado que desea actualizar: ");
                        short dni = Short.parseShort(br.readLine());
                        System.out.println("*******************");
                        System.out.println("* 1 -> Nombre.    *");
                        System.out.println("* 2 -> Teléfono.  *");
                        System.out.println("*******************");
                        String opcion_actualizar = br.readLine();
                        con.actualizarEmpleado(dni, opcion_actualizar);
                    }catch (IOException ex){
                        System.out.println("Error al leer algun dato");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Escribe el dni: ");
                        short dni = Short.parseShort(br.readLine());
                        con.borrarEmpleado(dni);
                    } catch (Exception e) {
                        System.out.println("Error al leer algun dato");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Escribe el dni del usuario o todos para visualizar todos: ");
                        String dni = br.readLine();
                        con.buscarEmpleado(dni);
                    } catch (Exception e) {
                        System.out.println("Error al leer algun dato");
                    }
                    break;
                case 5:
                    salir = true;
                    s.close();
                    break;
                default:
                    System.out.println("Seleccione una de las opciones del menu");
                    break;
            }

        }while(!salir);
    }
}