/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.dato;

import java.util.Objects;

/**
 *
 * @author windows8.1
 */
public class Field implements Itype{
    private String nombre;
    private String valor;

    public Field() {
    }

    public Field(String nombre, String valor) {
        this.nombre = nombre.toUpperCase();
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        
        this.nombre = nombre.toUpperCase();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.valor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return  nombre + "=" + valor ;
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getScript() {
        return  nombre + " = " + valor ;
    }

    public boolean isEqual(Field meta) {
    return this.nombre.equals(meta.nombre) && this.valor.equals(meta.valor);
    }


    
}
