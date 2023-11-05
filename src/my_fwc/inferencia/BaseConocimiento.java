/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_fwc.inferencia;

import java.util.LinkedList;
import my_fwc.dato.Itype;
import my_fwc.dato.Literal;

/**
 *
 * @author Franklin
 */
public class BaseConocimiento implements Itype{
 private static  BaseConocimiento baseConocimiento;
 private LinkedList<Regla> listReglas;
 private LinkedList<Literal> listVariables;
 
    private BaseConocimiento(){
     this.listReglas = new LinkedList<>();
     this.listVariables = new LinkedList<>();
    }

    public static BaseConocimiento getInstance(){
        if(baseConocimiento == null){
            baseConocimiento = new BaseConocimiento();
        }
        return baseConocimiento;
    }
 
    public LinkedList<Regla> getListReglas() {
        return listReglas;
    }

    public void setListReglas(LinkedList<Regla> listReglas) {
        this.listReglas = listReglas;
    }
    public void addLiteral(Literal literal){
        this.listVariables.add(literal);
    }
    
    public void removeLiteral(Literal literal){
        this.listVariables.remove(literal);
    }
    
    public void addRegla(Regla regla){
        this.listReglas.add(regla);
    }
    public void removeRegla(Regla regla){
        this.listReglas.remove(regla);
    }

    public LinkedList<Literal> getListVariables() {
        return listVariables;
    }

    public void setListVariables(LinkedList<Literal> listVariables) {
        this.listVariables = listVariables;
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getScript() {
       String varN = "NUMERICO \r\n";
       String varE = "ESCALAR \r\n";
       String reglas = "REGLA \r\n";
       varN = getLiterales("NUMERICO", varN);
       varE = getLiterales("ESCALAR", varE);
       
        for (Regla r : listReglas) {
          reglas+=r.getScript()+"\n";  
        }
    //   reglas = listReglas.stream().map((r) -> r.getScript()).reduce(reglas, String::concat);

    return "\r\n" +varN
            + "\r\n\r\n"+varE
            + "\r\n\r\n"+reglas+"\r\n";
    }
    
    public String variableScript() {
       String varN = "NUMERICO \n";
       String varE = "ESCALAR \n";
       varN = getLiterales("NUMERICO", varN);
       varE = getLiterales("ESCALAR", varE);
       
    return varN
            + "\n"+varE;
    }
    public String reglasScript(){
        String reglas = "";
        for (Regla r : listReglas) {
          reglas+=r.getScript()+"\r\n";  
        }
        return reglas;
    }
    private String getLiterales(String type,String var){
        LinkedList<Literal> l = new LinkedList<>();
        for (int i = 0; i <this.listVariables.size(); i++) {
            Itype itype = this.listVariables.get(i);
            if (itype.getType().equals(type)) {
                l.add(this.listVariables.get(i));
            }   
        }
        int size = l.size();
        for (int i = 0; i < l.size(); i++) {
            var+=l.get(i).getScript();
            if (i< size-1) {
                var+= " ,";
            }
        }
        return var;
    }
    public void vaciar(){
        this.listReglas.clear();
        this.listVariables.clear();
    }
 
}
