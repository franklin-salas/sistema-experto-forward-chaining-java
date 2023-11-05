/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.convers;

import my_fwc.analizador.AnalizadorLex;
import my_fwc.analizador.Cinta;
import my_fwc.analizador.Parser;
import my_fwc.analizador.Token;
import my_fwc.dato.BaseHecho;
import my_fwc.dato.Field;
import my_fwc.dato.LEscalar;
import my_fwc.dato.LNumerico;
import my_fwc.dato.Literal;
import my_fwc.dato.UIText;
import my_fwc.inferencia.BaseConocimiento;
import my_fwc.inferencia.Regla;

/**
 *
 * @author Franklin
 */
public class Interprete {
    private Cinta cinta;
    private AnalizadorLex analex;
    private UIText uIText;

    public Interprete(String s) {
           cinta = new Cinta(s);
           analex = new AnalizadorLex(cinta);
    }
    
    private boolean comprobarParser(){
           Parser parser = new Parser(analex);
        //verificar Expresiones
            parser.Expresion();
            
        return parser.errorFlag;
    }
    public UIText getuIText() {
        return uIText;
    }

    public void setuIText(UIText uIText) {
        this.uIText = uIText;
    }
    public void iniciar(){
        uIText.showScript("Iniciar ... ", "CONSOLA");
        if (!comprobarParser()) {
            analex.Init(); 
            leer();
        }else{
           System.out.println("Error en el parser :: Class Interprete");
           uIText.showScript("Error:: Parser -"+Interprete.class.getSimpleName(), "CONSOLA");
        }
       
    }
    
    private void leer(){
        while (true) {
             switch(analex.Preanalisis().getNombre()){
                case Token.VAR_NUMERICO:
                    match(Token.VAR_NUMERICO);
                    var_numerico();
                    break;
                case Token.VAR_ESCALAR:
                    match(Token.VAR_ESCALAR);
                    var_escalar();
                    break;
                case Token.VAR_REGLA:
                    match(Token.VAR_REGLA);
                    reglas();
                    break;
                case Token.BHECHO:
                    match(Token.BHECHO);
                    baseHecho();
                    break;
                    default:
                        return;
             }
        }           
            
        
    }

    private void var_numerico() {
        Token t = analex.Preanalisis();
        match(Token.STRING);
        BaseConocimiento bc = BaseConocimiento.getInstance();
        LNumerico ln = new LNumerico(t.getToStr());
        bc.addLiteral(ln);
        mas_var_numerico();
    }
    
    public boolean match(int token) {

        if (analex.Preanalisis().getNombre() == token) {
            analex.Avanzar();
            return true;
        } else {
            System.out.println("Error en : - "+analex.Preanalisis().getToStr() +"-" + token);

        }
        return false;
    }

    private void mas_var_numerico() {
        if (analex.Preanalisis().getNombre() == Token.COMA) {
            match(Token.COMA);
            Token t = analex.Preanalisis();
            match(Token.STRING);
            BaseConocimiento bc = BaseConocimiento.getInstance();
            LNumerico ln = new LNumerico(t.getToStr());
            bc.addLiteral(ln);
            mas_var_numerico();
        }       
    }

    private void var_escalar() {
        Token t = analex.Preanalisis();
        match(Token.STRING);
        BaseConocimiento bc = BaseConocimiento.getInstance();
        LEscalar ln = new LEscalar(t.getToStr());
        bc.addLiteral(ln);
        match(Token.IGUAL);
        match(Token.CA);
        var_dominio(ln);
        match(Token.CC);
        mas_var_escalar();    
    }
    private void var_dominio(LEscalar ln) {
        Token t = analex.Preanalisis();
        match(Token.STRING);
        ln.add(t.getToStr());
        mas_var_dominio(ln);
    }
    private void mas_var_escalar() {
          if (analex.Preanalisis().getNombre() == Token.COMA) {
                match(Token.COMA);
                Token t = analex.Preanalisis();
                match(Token.STRING);
                BaseConocimiento bc = BaseConocimiento.getInstance();
                LEscalar ln = new LEscalar(t.getToStr());
                bc.addLiteral(ln);
                match(Token.IGUAL);
                match(Token.CA);
                var_dominio(ln);
                match(Token.CC);
                mas_var_escalar();  
        } 
    }
    private void mas_var_dominio(LEscalar ln) {
          if (analex.Preanalisis().getNombre() == Token.COMA) {
              match(Token.COMA);
            Token t = analex.Preanalisis();
            match(Token.STRING);
            ln.add(t.getToStr());
            mas_var_dominio(ln);
        } 
    }

    private void baseHecho() {  
 if (analex.Preanalisis().getNombre() == Token.CA) {
        match(Token.CA);
        var_hecho();
        match(Token.CC);
        }
    }

    private void var_hecho() {
        if (analex.Preanalisis().getNombre() == Token.STRING) {
        Token t = analex.Preanalisis();
            match(Token.STRING);
            match(Token.IGUAL);
            Token t1 = analex.Preanalisis();
            valor();
           BaseHecho bh = BaseHecho.getInstance();
           bh.add(new Field(t.getToStr(), t1.getToStr()));
           mas_var_hecho();
        
        }      
    }
    
        private Literal valor() {
            Literal l = null; 
            switch (analex.Preanalisis().getNombre()) {
            case Token.NUM:
               // l = new LNumerico
                match(Token.NUM);
                l = new LNumerico();
                break;
            case Token.STRING:
                match(Token.STRING);
                l = new LEscalar();
                break;  
        } 
        return l;
    }

    private void mas_var_hecho() {
          if (analex.Preanalisis().getNombre() == Token.COMA) {
               match(Token.COMA);
          Token t = analex.Preanalisis();
            match(Token.STRING); 
            match(Token.IGUAL);
            Token t1 = analex.Preanalisis();
            valor();
           BaseHecho bh = BaseHecho.getInstance();
           bh.add(new Field(t.getToStr(), t1.getToStr()));
           mas_var_hecho();
            
        }
    }

    public void reglas() {
        if (analex.Preanalisis().getNombre() == Token.IF) {
            Regla r = new Regla();
            match(Token.IF);
            minter(r);
            match(Token.THEN);
             String var = analex.Preanalisis().getToStr();
            match(Token.STRING);
            match(Token.IGUAL);
            String val = analex.Preanalisis().getToStr();
            valor();
            r.setConclucion(new Field(var, val));
            BaseConocimiento bc = BaseConocimiento.getInstance();
            bc.addRegla(r);
            reglas();
        } 
    }

      public void minter(Regla r) {
        literal(r);
        masliteral(r);
    }

    public void literal(Regla r) {
        
      if (analex.Preanalisis().getNombre() == Token.STRING) {
            String var = analex.Preanalisis().getToStr();
            match(Token.STRING);
            String op = analex.Preanalisis().getToStr();
            operador();
            String val = analex.Preanalisis().getToStr();
            Literal l =  valor();
            l.setH(new Field(var, val));
            l.setOp(op);
            r.add(l);
        } 
    }

    public void masliteral(Regla r) {
        if (analex.Preanalisis().getNombre() == Token.AND) {
            match(Token.AND);
            literal(r);
            masliteral(r);
        }
    }
    
        private void operador() {
        switch (analex.Preanalisis().getNombre()) {
            case Token.IGUAL:
                match(Token.IGUAL);
                break;
            case Token.DIF:
                match(Token.DIF);
                break;
            case Token.MAYORQ:
                match(Token.MAYORQ);
                break;
            case Token.MAYORIGUALQ:
                match(Token.MAYORIGUALQ);
                break;
            case Token.MENORQ:
                match(Token.MENORQ);
                break;
            case Token.MENORIGUALQ:
                match(Token.MENORIGUALQ);
                break;
        }   
    }
    
}
