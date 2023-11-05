/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.analizador;

/**
 *
 * @author mauriballes
 */
public class Parser {

    private AnalizadorLex analex;
 
    public boolean errorFlag;

    public Parser(AnalizadorLex analex) {
        this.analex = analex;
    }

    public void Expresion() {
        
        this.errorFlag = false;
        while (!errorFlag) {            
        if (analex.Preanalisis().getNombre() == Token.VAR_REGLA) {
            match(Token.VAR_REGLA);
            reglas();
        }else if (analex.Preanalisis().getNombre() == Token.VAR_NUMERICO) {
            match(Token.VAR_NUMERICO);
            varN();
        }else if (analex.Preanalisis().getNombre() == Token.VAR_ESCALAR) {
            match(Token.VAR_ESCALAR);
            varE();
        }else if (analex.Preanalisis().getNombre() == Token.BHECHO) {
            match(Token.BHECHO);
            baseHecho();
        }else if (analex.Preanalisis().getNombre() == Token.FIN){
            break;
            }else{
            this.errorFlag = true;
            System.out.println("Error en la secuencia");
        }
        }

    }

    public void reglas() {
        if (analex.Preanalisis().getNombre() == Token.IF) {
            match(Token.IF);
            minter();
            match(Token.THEN);
            match(Token.STRING);
            match(Token.IGUAL);
            valor();
            reglas();
        } 
    }

    public void minter() {
        literal();
        masliteral();
    }

    public void literal() {
        
      if (analex.Preanalisis().getNombre() == Token.STRING) {
            match(Token.STRING);
            operador();
            valor();
        } 
    }

    public void masliteral() {
        if (analex.Preanalisis().getNombre() == Token.AND) {
            match(Token.AND);
            literal();
            masliteral();
        }
    }

    public void match(int token) {

        if (analex.Preanalisis().getNombre() == token) {
            System.out.println(analex.Preanalisis().getToStr());
            analex.Avanzar();
            
        } else {
            System.out.println("Error en Parser al procesar Token!");
            this.errorFlag = true;
        }
    }

    public void Init() {
        analex.Init();
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

    private void valor() {
            switch (analex.Preanalisis().getNombre()) {
            case Token.NUM:
                match(Token.NUM);
                break;
            case Token.STRING:
                match(Token.STRING);
                break;  
        } 
    }

 

    private void varN() {
      if (analex.Preanalisis().getNombre() == Token.STRING) {
            match(Token.STRING);
            masvarN();
        } 
    }

    private void varE() {
        if (analex.Preanalisis().getNombre() == Token.STRING) {
            match(Token.STRING);
            match(Token.IGUAL);
            match(Token.CA);
            varN();
            match(Token.CC);
            masvarE();
        }  
    }

    private void baseHecho() {
     if (analex.Preanalisis().getNombre() == Token.CA) {
            match(Token.CA);
            vars();
            match(Token.CC);
        }   
    }

    private void masvarN() { 
        if (analex.Preanalisis().getNombre() == Token.COMA) {
            match(Token.COMA);
            match(Token.STRING);
            masvarN();
        }    
    }

    private void vars() {
        if (analex.Preanalisis().getNombre() == Token.STRING) {
            match(Token.STRING);
            match(Token.IGUAL);
            valor();
            masvars();
        }
    }

    private void masvars() {        
        if (analex.Preanalisis().getNombre() == Token.COMA) {
            match(Token.COMA);
            match(Token.STRING);
            match(Token.IGUAL);
            valor();
            masvars();
        }   
    }

    private void masvarE() { 
        if (analex.Preanalisis().getNombre() == Token.COMA) {
            match(Token.COMA);
            match(Token.STRING);
            match(Token.IGUAL);
            match(Token.CA);
            varN();
            match(Token.CC);
            masvarE();
        }  
    }
}
