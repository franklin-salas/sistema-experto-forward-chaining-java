/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.inferencia;

import java.util.LinkedList;
import my_fwc.dato.BaseHecho;
import my_fwc.dato.Field;
import my_fwc.dato.UIText;

/**
 *
 * @author windows8.1
 */
public class MotorInferencia {
   private UIText uIText;

    public UIText getuIText() {
        return uIText;
    }

    public void setuIText(UIText uIText) {
        this.uIText = uIText;
    }
    
public void fwc(LinkedList<Regla> reglas,BaseHecho bh,Field meta){
    desmarcar();
    while (true) {        
        int l = bh.getBaseHechos().size();
        for (int i = 0; i < reglas.size(); i++) {
            Field c = reglas.get(i).Dispara();
            if (!reglas.get(i).MARCADO && c!=null) {
               bh.add(c);
                this.uIText.showScript("REGLA : => "+ reglas.get(i),"CONSOLA");
               reglas.get(i).MARCADO = true;
                if (c.equals(meta)) {
                    meta = c;
                    break;
                }
            }
        }
        if (bh.getBaseHechos().contains(meta)) {
            break;
        }
        if (l == bh.getBaseHechos().size()) {
            break;
        }
    }
    
    if (bh.getBaseHechos().contains(meta)) {
        this.uIText.showScript("META :: Encontrada = "+ meta,"CONSOLA");
        this.uIText.showScript("BASE DE HECHO :: "+ bh.baseHechoScript(),"CONSOLA");
    }else{
        this.uIText.showScript("- NO HAY CONCLUSIONES -","CONSOLA");
    }
        
}

    private void desmarcar() {
        BaseConocimiento bc = BaseConocimiento.getInstance();
        LinkedList<Regla> listReglas = bc.getListReglas();
        for (Regla r : listReglas) {
            r.MARCADO = false;
        }
    }
}
