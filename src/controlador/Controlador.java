/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import entidades.EmpleadosEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Galaterro on 31/01/2017.
 */
public class Controlador {
    private final Session sesion;

    public Controlador(Session sesion) {
        this.sesion = sesion;
    }
    
    public void insertarEmpleados(EmpleadosEntity emple){
        sesion.beginTransaction();
        sesion.save(emple);
        sesion.getTransaction().commit();
        System.out.println("Empleado introducido con éxito");
    }
    
    public void actualizarEmpleado(short dni, String opcion){
        sesion.beginTransaction();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        EmpleadosEntity emple = (EmpleadosEntity)sesion.get(EmpleadosEntity.class, dni);
        switch(opcion){
            case "1":
                if(emple != null){
                    System.out.println("Va a modificar al empleado con el nombre: " + emple.getNombre());
                    System.out.println("Escribe el nuevo nombre del usuario: ");
                    try{
                        String nombre = br.readLine();
                        emple.setNombre(nombre);
                    }catch(IOException ex){
                        System.out.println("Error al escribir el nombre.");
                    }
                    System.out.println("Nombre actualizado con éxito"); 
                }else {
                    System.out.println("El empleado no existe.");
                }    
                break;
            case "2":
                if(emple != null){
                    System.out.println("Va a modificar al empleado con el telefono: " + emple.getTelefono());
                    System.out.println("Escribe el nuevo telefono del usuario");
                    try{
                        int telefono = Integer.parseInt(br.readLine());
                        emple.setTelefono(telefono);      
                    }catch(IOException ex){
                        System.out.println("Error al escribir el telefono");
                    }
                    System.out.println("Telefono actualizado con exito");
                }else {
                    System.out.println("El empleado no existe");
                }
                break;
        }
        sesion.getTransaction().commit();
    }

    public void borrarEmpleado(short dni){
        sesion.beginTransaction();
        EmpleadosEntity emple = (EmpleadosEntity) sesion.get(EmpleadosEntity.class,dni);
        if(emple != null){
            sesion.delete(emple);
            System.out.println("Empleado borrado con éxito");
        }else {
            System.out.println("El empleado no existe");
        }
        sesion.getTransaction().commit();
    }
    
    public void buscarEmpleado(String dni_aux){
        if(!dni_aux.toLowerCase().equals("todos")){
            short dni = Short.parseShort(dni_aux);
            sesion.beginTransaction();
            EmpleadosEntity emple = (EmpleadosEntity)sesion.get(EmpleadosEntity.class, dni);
            sesion.getTransaction().commit();
            if(emple != null){
                System.out.println("DNI: " + emple.getDni() + "\tNombre: " + emple.getNombre() + "\tTelefono: " + emple.getTelefono());
            }else {
                System.out.println("El usuario no existe");
            }
        }else{
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from EmpleadosEntity");
            List<EmpleadosEntity> listaEmpleados = consulta.list();
            ArrayList<EmpleadosEntity> alEmpleados = new ArrayList();
            for (int i = 0; i < listaEmpleados.size(); i++) {
                alEmpleados.add(listaEmpleados.get(i));
                System.out.println("DNI: "+ alEmpleados.get(i).getDni() + "\tNombre: " + alEmpleados.get(i).getNombre() + "\tTelefono: " + alEmpleados.get(i).getTelefono());
                
            }
            sesion.getTransaction().commit();
        }
    }
}
