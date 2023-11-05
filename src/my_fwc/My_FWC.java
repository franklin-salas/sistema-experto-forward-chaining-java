/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc;

import my_fwc.analizador.AnalizadorLex;
import my_fwc.analizador.Cinta;
import my_fwc.analizador.Parser;
import my_fwc.convers.Interprete;
import my_fwc.dato.BaseHecho;
import my_fwc.dato.LEscalar;
import my_fwc.dato.LNumerico;
import my_fwc.dato.Literal;
import my_fwc.inferencia.BaseConocimiento;
import my_fwc.persistencia.GestionArchivo;
import my_fwc.ui.Sistema_Experto;

/**
 *
 * @author windows8.1
 */
public class My_FWC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        BaseConocimiento bc = BaseConocimiento.getInstance();
//        Literal l = new LNumerico("x");
//        bc.addLiteral(l);
//        LEscalar t = new LEscalar("tiempo");
//        t.add("alta");
//        t.add("baja");
//        t.add("media");
//        bc.addLiteral(t);
//        System.out.println(bc.getListVariables().toString());
        Sistema_Experto d = new Sistema_Experto();
        d.setVisible(true);
        
        // System.out.println(GestionArchivo.leerContenido());
//                Cinta cinta = new Cinta(GestionArchivo.leerContenido());
//        AnalizadorLex analex = new AnalizadorLex(cinta);
//        Parser parser = new Parser(analex);
//        //verificar Expresiones
//        parser.Expresion();
        
//        Interprete i = new Interprete(GestionArchivo.leerContenido());
//        i.iniciar();
//        BaseHecho bh = BaseHecho.getInstance();
//        BaseConocimiento bc = BaseConocimiento.getInstance();
//        System.out.println(bc.getScript());
//        System.out.println(bh.getScript());
        

    }
    
}
