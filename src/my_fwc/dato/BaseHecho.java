/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.dato;

import java.util.LinkedList;
import java.util.List;
import my_fwc.analizador.TPC;
import my_fwc.analizador.Token;

/**
 *
 * @author windows8.1
 */
public class BaseHecho implements Itype{
    private List<Field> listaHechos;
    private static BaseHecho baseHecho;

    private BaseHecho() {
        this.listaHechos = new LinkedList<>();
    }
    public final static BaseHecho getInstance(){
        if (baseHecho == null) {
            baseHecho = new BaseHecho();
        }
        return baseHecho;
    }
    public boolean add(Field hecho){
        if (this.listaHechos.contains(hecho)) {
            return false;
        }
        return this.listaHechos.add(hecho);
    }
    
    public boolean deleted(Field hecho){
        return this.listaHechos.remove(hecho);
    }
    
    public List<Field> getBaseHechos() {
        return listaHechos;
    }

    public void setBaseHechos(List<Field> baseHechos) {
        this.listaHechos = baseHechos;
    }

    @Override
    public String getType() {
        return TPC.BHECHO;
    }

    @Override
    public String getScript() {
        String hechos = "";
        int size = this.listaHechos.size();
        for (int i = 0; i < this.listaHechos.size(); i++) {
            hechos += this.listaHechos.get(i).getScript();
            if (i<size-1) {
               hechos +=" ,"; 
            }
        } 
    return "\r\n"+TPC.BHECHO+"\r\n [ "+ hechos+" ]";
    }
    
    public String baseHechoScript(){
         String hechos = "";
        int size = this.listaHechos.size();
        for (int i = 0; i < this.listaHechos.size(); i++) {
            hechos += this.listaHechos.get(i).getScript();
            if (i<size-1) {
               hechos +=" ,"; 
            }
        } 
    return " [ "+ hechos+" ]"; 
    }
    
    public void vaciar(){
        this.listaHechos.clear();
    }
}
