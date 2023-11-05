/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.dato;

/**
 *
 * @author windows8.1
 */
public class LNumerico extends Literal{

    public LNumerico(Field h, String op) {
        super(h, op);
    }

    public LNumerico() {
        super();
    }

    public LNumerico(String nombre) {
        super(nombre);
    }

  
    public int valor() {       
        return Integer.parseInt(this.h.getValor());
    }

    public void setValor(int valor) {
        this.h.setValor(String.valueOf(valor));
    }

      @Override
    public String toString() {
        return this.getNombre() +" = {Numerico}";
    }

    @Override
    public String getType() {
    return "NUMERICO";
    }

    @Override
    public String getScript() {
        return this.getNombre() ;
    }

    
}
