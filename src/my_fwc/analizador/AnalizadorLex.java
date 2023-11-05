/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.analizador;


public class AnalizadorLex {

    private Cinta M;
    private Token R = new Token();
    private String AC = "";

    public AnalizadorLex(Cinta cinta) {
        M = cinta;
        Init();
    }

    private void dt() {
        int Estado = 0;
        while (true) {
            char c = M.cc();
            switch (Estado) {
                case 0:
                    if (M.Letra(c)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 3;
                    } else if (c == ",".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 11;
                    } else if (c == "&".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 5;
                    } else if (M.Digito(c)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 7;
                    } else if (c == "=".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 12;
                    } else if (c == "[".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 9;
                    } else if (c == "]".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 10;
                    }else if (c == "<".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 13;
                    }else if (c == ">".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 16;
                    }else if (c == "!".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 19;
                    }else if (c == "-".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 21;
                    }else if (c == ";".charAt(0)) {
                        AC = String.valueOf(c);
                        M.Avanzar();
                        Estado = 22;
                    }
                    else if (M.Espacio(c)) {
                        M.Avanzar();
                    } else if (c == Cinta.EOF) {
                        Estado = 1;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 1:
                    AC = "EOF";
                    R = new Token(Token.FIN, -1, AC);
                    return;
                case 2:
                    R = new Token(Token.ERROR, -1, AC);
                    return;
                case 3:
                    if (M.Letra(c)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                    } else {
                        Estado = 4;
                    }
                    break;
                case 4:
                    Token token = TPC.estaEnTPC(AC);
                    if (token != null) {
                        R = token;
           
                    } else {
                       R = new Token(Token.STRING, -1, AC);
                    }
                    return;
                case 5:
                  if (c == "&".charAt(0)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                        Estado = 6;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 6:
                    R = new Token(Token.AND, -1, AC);
                    return;
                case 7:
                    if (M.Digito(c)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                    } else {
                        Estado = 8;
                    }
                    break;
                case 8:
                    R = new Token(Token.NUM, Integer.parseInt(AC), AC);
                    return;
                case 9:
                    R = new Token(Token.CA, -1, AC);
                    return;
                case 10:
                    R = new Token(Token.CC, -1, AC);
                    return;
                case 11:
                    R = new Token(Token.COMA, -1, AC);
                    return;
                case 12:
                    R = new Token(Token.IGUAL, -1, AC);                  
                    return;
                case 13:
                  if (c == "=".charAt(0)) {
                        AC +=  String.valueOf(c);
                        M.Avanzar();
                        Estado = 15;
                    }else{
                      Estado = 14;
                  }
                    break;
                case 14:
                    R = new Token(Token.MENORQ, -1, AC);  
                    return;
                case 15:
                     R = new Token(Token.MENORIGUALQ, -1, AC);
                    return;
                case 16:
                        if (c == "=".charAt(0)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                        Estado = 17;
                    }else{
                      Estado = 18;
                  }
                    break;
                case 17:
                     R = new Token(Token.MAYORIGUALQ, -1, AC);
                    return;
                case 18:
                     R = new Token(Token.MAYORQ, -1, AC);
                    return;
                case 19:
                  if (c == "=".charAt(0)) {
                        AC += String.valueOf(c);
                        M.Avanzar();
                        Estado = 20;
                    }else{
                      Estado = 2;
                  }
                    break;
                case 20:
                     R = new Token(Token.DIF, -1, AC);
                    return;
                case 21:if (M.Digito(c)) {
                         AC += String.valueOf(c);
                        M.Avanzar();
                        Estado = 7;
                        }else {
                    Estado = 2;
                }
                    break;
                case 22:
                     R = new Token(Token.PCOMA, -1, AC);
                    return;
                default:
                    break;
            }
        }
    }

    public void Init() {
        M.Init();
        dt();
    }

    public Token Preanalisis() {
        return R;
    }

    public String Lexema() {
        return AC;
    }

    public void Avanzar() {
        dt();
    }
}
