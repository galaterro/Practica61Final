package entidades;

import javax.persistence.*;

/**
 * Created by Galaterro
 */
@Entity
@Table(name = "EMPLEADOS", schema = "HR")
public class EmpleadosEntity {
    private long dni;
    private String nombre;
    private long telefono;

    public EmpleadosEntity(long dni, String nombre, long telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public EmpleadosEntity() {
    }

    @Id
    @Column(name = "DNI")
    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "TELEFONO")
    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosEntity that = (EmpleadosEntity) o;

        if (dni != that.dni) return false;
        if (telefono != that.telefono) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (dni ^ (dni >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (int) (telefono ^ (telefono >>> 32));
        return result;
    }
}
