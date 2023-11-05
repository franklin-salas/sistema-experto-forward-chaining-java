/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.analizador;

/**
 *
 * @author cesarFuentes
 */
public class Token {
    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int VAR_REGLA = 2; // Alguna Funcion
    public static final int PCOMA = 3; //;
    public static final int CA = 4; // Corchete Abierto
    public static final int CC = 5; // Corchete Cerrado
    public static final int COMA = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int AND = 9; // &&
    public static final int NEGA = 10;//¬
    public static final int HELP = 11;
    public static final int EOF = 12;
    public static final int IF = 13; //IF
    public static final int THEN = 14;
    public static final int IGUAL = 15;
    public static final int DIF = 16;
    public static final int MAYORQ = 17;
    public static final int MENORQ = 18;
    public static final int MAYORIGUALQ = 19;
    public static final int MENORIGUALQ = 20;
    public static final int LESCALAR = 21;
    public static final int LNUMERICO = 22;
    public static final int BHECHO = 23;
    public static final int VAR_ESCALAR = 24;
    public static final int VAR_NUMERICO = 25;
   // public static final int HECHO = 26;
    //public static final int VAR_REGLA = 27;
    private int nombre;
    private int atributo;
    private String toStr;

    public Token() {
    }

    public Token(int nombre, int atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;

    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getAtributo() {
        return atributo;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }

    public String getToStr() {
        return toStr;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }

    
}
