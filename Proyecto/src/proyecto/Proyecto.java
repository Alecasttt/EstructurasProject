/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author yulia
 */
public class Proyecto {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo; // MANIPULACION AL ARCHIVO
        FileWriter escribir; //ESCRIBIR ARCHIVO
        PrintWriter linea ; // ESCRIBIR EN EL ARCHIVO
        String nombreBanco=""; 
        String cajas;
        archivo = new File("usuario.txt");
         if (!archivo.exists()){
            try {
                archivo.createNewFile();
                nombreBanco = JOptionPane.showInputDialog("Ingresa el nombre del banco ");
                cajas = JOptionPane.showInputDialog("Cantidad de cajas:  "); 
                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                
                //ESCRIBIR EN EL ARCHIVO
                linea.print(nombreBanco);
               linea.print(cajas);
                linea.close();
                escribir.close();


            } catch (IOException ex) {
                Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
             
         }else{
             try{
          
                nombreBanco = JOptionPane.showInputDialog("Ingresa el nombre del banco ");
                cajas = JOptionPane.showInputDialog("Cantidad de cajas:  "); 

                escribir = new FileWriter(archivo,true);
                linea = new PrintWriter(escribir);
                
                //ESCRIBIR EN EL ARCHIVO
                linea.println(nombreBanco);
                linea.println(cajas);

                linea.close();
                escribir.close();  
         
             } catch (IOException ex) {
                Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
}
///////smndjsnajdknsb
///Jose
//yuli