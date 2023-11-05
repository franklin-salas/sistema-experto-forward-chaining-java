/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my_fwc.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author windows8.1
 */
public class GestionArchivo {
    public static String path = "se/my_se.txt";

public  static String leerContenido() {
        FileReader fr = null;
        BufferedReader br = null;
        String datos = "";
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
            String linea;
            String str = "";
            while ((linea = br.readLine()) != null) {                
                str += linea + "\n";
            }
            datos = str;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != fr){
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return datos;
        }
        
    }

    public static void guardarArchivo(String dato){
        System.out.println(dato);
                FileWriter save ;
                BufferedWriter bw ;
        try {
            save = new FileWriter(path);
            bw = new BufferedWriter(save);
            bw.write(dato);
            JOptionPane.showMessageDialog(null, "El archivo se ha guardado exitosamente.","Informacion",JOptionPane.INFORMATION_MESSAGE);
            bw.close();
        } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado", "Advertencia", JOptionPane.WARNING_MESSAGE);
    
        } 
           
            }
  
}
