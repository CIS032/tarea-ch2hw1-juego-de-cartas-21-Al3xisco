package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BlackjackLogs {
    /*  Crear una clase ejecutable denominada "BlackjackLogs.java" a partir 
        de "Blackjack" de tal manera que se todos los mensajes de salida los 
        guarde en un archivo de texto denominado logs.txt*/

    public static void main(String[] args) throws FileNotFoundException {
        
        String aux = "";
        aux+= Blackjack.jugarBlackjack();
        
        System.out.println(Blackjack.jugarBlackjack());
        
        File archivoFile = new File("logs.txt"); //archivo, nombre
        FileOutputStream guardaDatos = new FileOutputStream(archivoFile);  // FileOutputStream - recuperar los datos de archivos para mostrarlos
        PrintStream imprimirSalida = new PrintStream(guardaDatos);   // PrintStream - desplegar/mostrar los resultados // Asigna los datos de archivo recuperados a la variable printstream para mostrarlos. 
        System.setOut(imprimirSalida); // Guarda los datos de salida en el archivo
        System.out.println(aux+= Blackjack.jugarBlackjack());


    }
 
}
