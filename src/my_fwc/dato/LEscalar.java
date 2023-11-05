/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.dato;

import java.util.LinkedList;

/**
 *
 * @author windows8.1
 */
public class LEscalar extends Literal{
    private LinkedList<String> rangoValores;

    public LEscalar() {
        super();
    }

    public LEscalar(Field h, String op) {
        super(h, op);
        this.rangoValores = new LinkedList<>();
    }
     public LEscalar(String nombre) {
        super(nombre);
        this.rangoValores = new LinkedList<>();
    }

    public String valor() {
       return this.h.getValor();
    }

    @Override
    public void setValor(String valor) {
        this.h.setValor(valor);
    }
    public void add(String valor){
        this.rangoValores.add(valor);
    }
    public void remove(String valor){
        this.rangoValores.remove(valor);
    }
    public boolean isDomain(String valor){
        return this.rangoValores.contains(valor);
    }
    @Override
    public String toString() {
        return this.getNombre() +" = "+ rangoValores ;
    }

    @Override
    public String getType() {
    return "ESCALAR";
    }

    @Override
    public String getScript() {
        String domi = "";
        int size = this.rangoValores.size();
        for (int i = 0; i < this.rangoValores.size(); i++) {
            domi += this.rangoValores.get(i);
            if (i<size-1) {
               domi +=" ,"; 
            }
        }
       return this.getNombre() +" = [ "+domi+" ]" ;
    }
    
    
}
