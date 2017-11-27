
package blackjack;

import javax.swing.JOptionPane;

    /**
     * Este programa permite al usuario jugar Blackjack. El ordenador
     * actúa como el distribuidor. El usuario tiene una apuesta de $ 100 y
     * hace una apuesta en cada juego. El usuario puede irse en cualquier momento,
     * o será expulsado cuando pierda todo el dinero.
     * Reglas de la casa: el repartidor golpea a un total de 16 o menos
     * y se encuentra en un total de 17 o más. El distribuidor gana lazos.
     * Se barajean las cartas para cada juego.
     */ //Traje == palo.

public class BlackjackGUI {

    public static void main (String [] args){
       
        int dinero; // Cantidad de dinero que tiene el usuario.
        int apuesta; // Cantidad de apuestas de usuario en un juego.
        boolean usuarioGana; // ¿El usuario ganó el juego?
        
        JOptionPane.showMessageDialog (null, " Bienvenido al juego de Blackjack ");
        //JOptionPane.showMessageDialog(null, "");

        dinero = 100; // El usuario comienza con $ 100.

        while (true) {
            JOptionPane.showMessageDialog(null, "Tienes " + dinero + " dólares");
            do {
               JOptionPane.showMessageDialog(null, "¿Cuántos dólares quieres apostar? (Ingresa 0 para finalizar)");
               apuesta = Integer.parseInt(JOptionPane.showInputDialog(null, "?:"));
               //apuesta = TextIO.getlnInt();
               if (apuesta <0 || apuesta> dinero)
                   JOptionPane.showMessageDialog(null, "Su respuesta debe estar entre 0 y " + dinero + '.');
            } while (apuesta <0 || apuesta> dinero);
            if (apuesta == 0)
               break;
            usuarioGana = jugarBlackjack();
            if (usuarioGana)
               dinero = dinero + apuesta;
            else
               dinero = dinero - apuesta;
                //JOptionPane.showMessageDialog(null, "");
            if (dinero == 0) {
               JOptionPane.showMessageDialog(null, "Parece que te has quedado sin dinero");
               break;
            }
        }

            //JOptionPane.showMessageDialog(null, "");
            JOptionPane.showMessageDialog(null, "Sales con $ " + dinero + '.');

    } // fin main ()

    /* 
    * Deje que el usuario juegue un juego de Blackjack, con la computadora como Cupier. 
    * @return true si el usuario gana el juego, falso si el usuario pierde. 
    */   

    static boolean jugarBlackjack() {

        Baraja baraja; // Una baraja de cartas. Una nueva baraja para cada juego. 
        ManoBlackjack crupierMano; // La mano del crupier. 
        ManoBlackjack usuarioMano; // La mano del usuario. 

        baraja = new Baraja (); 
        crupierMano = new ManoBlackjack (); 
        usuarioMano = new ManoBlackjack (); 

        /* Baraja el mazo, reparte dos cartas a cada jugador. */ 

        baraja.barajar();
        crupierMano.agregaCarta(baraja.reparteCartas ()); 
        crupierMano.agregaCarta (baraja.reparteCartas ()); 
        usuarioMano.agregaCarta (baraja.reparteCartas ()); 
        usuarioMano.agregaCarta (baraja.reparteCartas ()); 

        //JOptionPane.showMessageDialog(null, "");
        //JOptionPane.showMessageDialog(null, "");

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21). 
        El jugador con Blackjack gana el juego. El cupier gana lazos. 
        */ 

        if (crupierMano.getBlackjackValor () == 21) { 
            JOptionPane.showMessageDialog(null, "El usuario tiene " + usuarioMano.getCarta (0) 
                                      + "y la " + usuarioMano.getCarta (1) + "."); 
            //JOptionPane.showMessageDialog(null, "");
            JOptionPane.showMessageDialog(null, "El crupier tiene Blackjack. El crupier gana."); 
            return false; 
        }

        if (usuarioMano.getBlackjackValor () == 21) { 
            JOptionPane.showMessageDialog(null, "El crupier tiene " + crupierMano.getCarta (0) 
                                    + "y la" + crupierMano.getCarta (1) + "."); 
            JOptionPane.showMessageDialog(null, "El usuario tiene " + usuarioMano.getCarta (0) 
                                      + "y la " + usuarioMano.getCarta (1) + "."); 
            //JOptionPane.showMessageDialog(null, ""); 
            JOptionPane.showMessageDialog(null, "Tienes Blackjack. Tú ganas"); 
            return true; 
        } 

        /* Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero, el usuario 
        tiene la oportunidad de robar cartas (es decir, "golpear"). 
        El ciclo while finaliza cuando el usuario elige "Stand". Si el usuario supera los 21,
        el usuario pierde inmediatamente. 
        */ 

        while (true) { 

         /* Mostrar tarjetas de usuario, y dejar que el usuario decida Seguir o Quedarse.*/ 

            //JOptionPane.showMessageDialog(null, ""); 
            //JOptionPane.showMessageDialog(null, ""); 
            //JOptionPane.showMessageDialog(null, "Tus cartas son: "); 
            for (int i = 0; i <usuarioMano.getCartasCantidad(); i ++) 
                 JOptionPane.showMessageDialog(null, " " + usuarioMano.getCarta (i)); 
                 JOptionPane.showMessageDialog(null, "Su total es: " + usuarioMano.getBlackjackValor ()); 
                 //JOptionPane.showMessageDialog(null, ""); 
                 JOptionPane.showMessageDialog(null, "El crupier muestra  " + crupierMano.getCarta (0)); 
                // JOptionPane.showMessageDialog(null, ""); 
                 JOptionPane.showMessageDialog(null, " Seguir (s) o Quedarse (q)? ");
                 String usuarioAccion2;
                while ((usuarioAccion2=JOptionPane.showInputDialog("Ingrese una opción: ")).length() != 1);
                char usuarioAccion = usuarioAccion2.charAt(0);
                 //char usuarioAccion = JOptionPane.showInputDialog("Ingrese una opción: ").charAt(0); // Respuesta del usuario, 'S' o 'Q'. Cambio de: Hit H -> S de seguir y de Stand S por Q de quedarse..
            do { 
               //usuarioAccion = Character.toUpperCase (String usuarioAccion); 
               if (usuarioAccion != 's' && usuarioAccion != 'q') 
                  JOptionPane.showMessageDialog(null, "Responda S o Q: "); 
            } while (usuarioAccion != 's' && usuarioAccion != 'q'); 

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario se para, 
               el ciclo termina (y es el turno del crupier de robar cartas). 
            Cambio de: Hit H -> S de seguir y de Stand S por Q de quedarse
             */ 
            
            if (usuarioAccion == 'q') { 
                    // Loop ends; el usuario ha terminado de tomar cartas. 
                break;
            }
            else { // usuarioAccion es 'S'. Dale una tarjeta al usuario.  
                    // Si el usuario supera los 21, el usuario pierde. 
                Carta nuevaCarta = baraja.reparteCartas (); 
                usuarioMano.agregaCarta (nuevaCarta); 
                //JOptionPane.showMessageDialog(null, ""); 
                JOptionPane.showMessageDialog(null, "El usuario pide una carta"); 
                JOptionPane.showMessageDialog(null, "Su carta es la " + nuevaCarta); 
                JOptionPane.showMessageDialog(null, "Su total es ahora " + usuarioMano.getBlackjackValor ()); 
                if (usuarioMano.getBlackjackValor ()> 21) { 
                    //JOptionPane.showMessageDialog(null, ""); 
                    JOptionPane.showMessageDialog(null, "Has fallado al pasar de 21. Has perdido.");
                    JOptionPane.showMessageDialog(null, "La otra carta del crupier es: " 
                                                       + crupierMano.getCarta (1)); 
                    return false;  
             } 
         } 
            
    } // end while loop 

    /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora, es 
    la oportunidad del crupier para dibujar. El distribuidor roba las cartas hasta que el 
    total del repartidor sea> 16. Si el repartidor supera los 21, el repartidor pierde. 
    */ 

    //JOptionPane.showMessageDialog(null, ""); 
    JOptionPane.showMessageDialog(null, "El usuario se planta. Turno del crupier: "); 
    JOptionPane.showMessageDialog(null, "Las cartas del crupier son: "); 
    JOptionPane.showMessageDialog(null, "" + crupierMano.getCarta(0));
    JOptionPane.showMessageDialog(null, "" + crupierMano.getCarta (1)); 
    while (crupierMano.getBlackjackValor() <= 16) { 
        Carta nuevaCarta = baraja.reparteCartas (); 
        JOptionPane.showMessageDialog(null, "El crupier pide una carta y obtiene: " + nuevaCarta); 
        crupierMano.agregaCarta (nuevaCarta); 
        if (crupierMano.getBlackjackValor() > 21) { 
            //JOptionPane.showMessageDialog(null, ""); 
            JOptionPane.showMessageDialog(null, "Crupier pierde al pasar de 21. Usted gana"); 
            return true; 
        } 
    } 
    JOptionPane.showMessageDialog(null, "El total del crupier es " + crupierMano.getBlackjackValor ()); 

    /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
    puede determinar el ganador comparando los valores de sus manos. */ 

    //JOptionPane.showMessageDialog(null, ""); 
    if (crupierMano.getBlackjackValor () == usuarioMano.getBlackjackValor ()) { 
        JOptionPane.showMessageDialog(null, "El crupier gana en un empate. Has perdido."); 
        return false; 
    } 
    else if (crupierMano.getBlackjackValor ()> usuarioMano.getBlackjackValor ()) { 
        JOptionPane.showMessageDialog(null, "Gana la banca," + crupierMano.getBlackjackValor () 
                    + " puntos a " + usuarioMano.getBlackjackValor () + ".") ; 
        return false; 
    } 
    else { 
        JOptionPane.showMessageDialog(null, "Usted gana, " + usuarioMano.getBlackjackValor ()
                    + " apunta a " + crupierMano.getBlackjackValor () + "."); 
        return true; 
        }
    
    } // finalizar jugarBlackjack() 
    
    
} // fin de clase Blackjack


    

