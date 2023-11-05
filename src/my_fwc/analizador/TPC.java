/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.analizador;

import java.util.Arrays;
import java.util.LinkedList;
import my_fwc.dato.Literal;
import my_fwc.inferencia.BaseConocimiento;

/**
 *
 * @author Franklin
 */
public class TPC {
    


    public static final String IF = "IF"; //IF
    public static final String THEN = "THEN";
    public static final String BHECHO = "BASEHECHO";
    public static final String VAR_ESCALAR = "ESCALAR";
    public static final String VAR_NUMERICO = "NUMERICO";
    public static final String VAR_REGLA = "REGLA";


    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            IF,
            THEN,
            BHECHO,
            VAR_ESCALAR,
            VAR_NUMERICO,
            VAR_REGLA
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.IF, -1, IF),
            new Token(Token.THEN, -1,THEN),
            new Token(Token.BHECHO, -1,BHECHO),
            new Token(Token.VAR_ESCALAR, -1,VAR_ESCALAR),
            new Token(Token.VAR_NUMERICO, -1,VAR_NUMERICO),
            new Token(Token.VAR_REGLA, -1,VAR_REGLA)
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
//        BaseConocimiento bc = BaseConocimiento.getInstance();
//        LinkedList<Literal> listVariables = bc.getListVariables();
//        for (Literal literal : listVariables) {
//            if (literal.getNombre().equals(lexema)) {
//                
//            }
//        }
        return null;
    }
}