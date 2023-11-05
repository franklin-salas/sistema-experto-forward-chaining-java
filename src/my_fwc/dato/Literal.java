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
public abstract class Literal implements Itype{
    protected Field h;
    protected String op;
    protected boolean negacion;
    // true  ¬P
    //false P

    public Literal() {
    }

    public Literal(String nombre) {
        this.h = new Field();
        this.h.setNombre(nombre);
        this.negacion = false;
    }

    public Literal(Field h, String op) {
        this.h = h;
        this.op = op;
        this.negacion = false;
    }

    public Field getH() {
        return h;
    }

    public void setH(Field h) {
        this.h = h;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public boolean isNegacion() {
        return negacion;
    }
    
    public void setNegacion(boolean negacion) {
        this.negacion = negacion;
    }
    public String getNombre() {
        return this.h.getNombre();
    }

    public void setNombre(String nombre) {
        this.h.setNombre(nombre);
    }
    
    public String getValor() {       
        return this.h.getValor();
    }

    public void setValor(String valor) {
        this.h.setValor(valor);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.h);
        hash = 37 * hash + Objects.hashCode(this.op);
        hash = 37 * hash + (this.negacion ? 1 : 0);
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
        final Literal other = (Literal) obj;
        if (!Objects.equals(this.h, other.h)) {
            return false;
        }
       
        return true;
    }

  

    @Override
    public String toString() {
        return h.getNombre() +" "+op +" "+h.getValor();
    }

    @Override
    public String getScript() {
    return h.getNombre() +" "+op +" "+h.getValor();
    }

    
}
