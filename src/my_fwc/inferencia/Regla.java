/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.inferencia;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import my_fwc.dato.BaseHecho;
import my_fwc.dato.Field;
import my_fwc.dato.Itype;
import my_fwc.dato.LEscalar;
import my_fwc.dato.LNumerico;
import my_fwc.dato.Literal;

/**
 *
 * @author windows8.1
 */
public class Regla implements Itype{
    private List<Literal> premisa;
    private Field conclucion;
    public boolean MARCADO;

    public Regla() {
        this.premisa = new LinkedList<>();
        this.MARCADO = false;
    }
    public void add(Literal literal){
        this.premisa.add(literal);
    }
    public void remove(Literal literal){
        this.premisa.remove(literal);
    }
    public List<Literal> getPremisa() {
        return premisa;
    }

    public void setPremisa(List<Literal> premisa) {
        this.premisa = premisa;
    }

    public Field getConclucion() {
        return conclucion;
    }

    public void setConclucion(Field conclucion) {
        this.conclucion = conclucion;
    }
    public Field Dispara(){
        BaseHecho bh = BaseHecho.getInstance();
        for (Literal literal : this.premisa) {
            if (!bh.getBaseHechos().contains(literal.getH()) || !cumpleCondicion(literal, bh)) {
                return null;
            }
        }      
        return this.conclucion;
    }
    
    private boolean cumpleCondicion(Literal l ,BaseHecho bh){
         BaseConocimiento bc = BaseConocimiento.getInstance();
         LinkedList<Literal> listVariables = bc.getListVariables();
         int i = listVariables.indexOf(l);
         if (i<0) 
            return false;
         
         Literal li = listVariables.get(i);
         
        if (null != li.getType()) switch (li.getType()) {
            case "NUMERICO":
            {
                LNumerico n = (LNumerico) l;
                int indexOf = bh.getBaseHechos().indexOf(n.getH());
                int num = Integer.parseInt(bh.getBaseHechos().get(indexOf).getValor());
                switch(l.getOp()){
                    case "=":
                        return n.valor() == num;
                    case "!=":
                        return n.valor() != num;
                    case "<=":
                        return num <= n.valor();
                    case ">=":
                        return num >= n.valor();
                    case "<":
                        return num < n.valor();
                    case ">":
                        return num > n.valor();
                        
                }
                
            }
            case "ESCALAR":
            {
                LEscalar n =  (LEscalar) l;
//                BaseConocimiento bc = BaseConocimiento.getInstance();
//                LinkedList<Literal> listVariables = bc.getListVariables();
//                int i = listVariables.indexOf(l);
                LEscalar le= (LEscalar) li;
                if (!le.isDomain(n.valor())) {
                    return false;
                }
                //n.getValor()
                int indexOf = bh.getBaseHechos().indexOf(n.getH());
                String valor = bh.getBaseHechos().get(indexOf).getValor();
                switch(l.getOp()){
                    case "=":
                        return n.valor().equals(valor);
                    case "!=":
                        return !n.valor().equals(valor);
                  
          }
            
        }
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.premisa);
        hash = 53 * hash + Objects.hashCode(this.conclucion);
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
        final Regla other = (Regla) obj;
        if (!Objects.equals(this.premisa, other.premisa)) {
            return false;
        }
        if (!Objects.equals(this.conclucion, other.conclucion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String minters = "";
        int size = premisa.size();
        for (int i = 0; i < premisa.size(); i++) {
            minters += premisa.get(i).getNombre()+ " "+premisa.get(i).getOp()+" "+premisa.get(i).getValor();
            if(i<size-1){
              minters += " && ";
            }
        }
        return "IF "+ minters + " THEN " + conclucion ;
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getScript() {
       String minters = "";
        int size = premisa.size();
        for (int i = 0; i < premisa.size(); i++) {
            minters += premisa.get(i).getNombre()+ " "+premisa.get(i).getOp()+" "+premisa.get(i).getValor();
            if(i<size-1){
              minters += " && ";
            }
        }
        return "IF "+ minters + "\r\n"
                + "        THEN \r\n"
                + "         " + conclucion ;
  
    
    }

 
    
}
