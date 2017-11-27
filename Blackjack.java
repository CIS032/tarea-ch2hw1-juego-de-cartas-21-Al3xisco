
package blackjack;




    /**
     * Este programa permite al usuario jugar Blackjack. El ordenador
     * actúa como el distribuidor. El usuario tiene una apuesta de $ 100 y
     * hace una apuesta en cada juego. El usuario puede irse en cualquier momento,
     * o será expulsado cuando pierda todo el dinero.
     * Reglas de la casa: el repartidor golpea a un total de 16 o menos
     * y se encuentra en un total de 17 o más. El distribuidor gana lazos.
     * Se barajean las cartas para cada juego.
     */ //Traje == palo.

public class Blackjack {

    public static void main (String [] args){
       
        int dinero; // Cantidad de dinero que tiene el usuario.
        int apuesta; // Cantidad de apuestas de usuario en un juego.
        boolean usuarioGana; // ¿El usuario ganó el juego?

        System.out.println (" Bienvenido al juego de Blackjack ");
        System.out.println ();

        dinero = 100; // El usuario comienza con $ 100.

        while (true) {
            System.out.println ("Tienes " + dinero + " dólares");
            do {
               System.out.println ("¿Cuántos dólares quieres apostar? (Ingresa 0 para finalizar)");
               System.out.print ("?:");
               apuesta = TextIO.getlnInt();
               if (apuesta <0 || apuesta> dinero)
                   System.out.println ("Su respuesta debe estar entre 0 y " + dinero + '.');
            } while (apuesta <0 || apuesta> dinero);
            if (apuesta == 0)
               break;
            usuarioGana = jugarBlackjack();
            if (usuarioGana)
               dinero = dinero + apuesta;
            else
               dinero = dinero - apuesta;
            System.out.println ();
            if (dinero == 0) {
               System.out.println ("Parece que te has quedado sin dinero");
               break;
            }
        }

            System.out.println ();
            System.out.println ("Sales con $ " + dinero + '.');

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

        System.out.println (); 
        System.out.println ();

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21). 
        El jugador con Blackjack gana el juego. El cupier gana lazos. 
        */ 

        if (crupierMano.getBlackjackValor () == 21) { 
            System.out.println ("El usuario tiene " + usuarioMano.getCarta (0) 
                                      + "y la " + usuarioMano.getCarta (1) + "."); 
            System.out.println (); 
            System.out.println ("El crupier tiene Blackjack. El crupier gana."); 
            return false; 
        }

        if (usuarioMano.getBlackjackValor () == 21) { 
            System.out.println ("El crupier tiene " + crupierMano.getCarta (0) 
                                    + "y la" + crupierMano.getCarta (1) + "."); 
            System.out.println ("El usuario tiene " + usuarioMano.getCarta (0) 
                                      + "y la " + usuarioMano.getCarta (1) + "."); 
            System.out.println (); 
            System.out.println ("Tienes Blackjack. Tú ganas"); 
            return true; 
        } 

        /* Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero, el usuario 
        tiene la oportunidad de robar cartas (es decir, "golpear"). 
        El ciclo while finaliza cuando el usuario elige "Stand". Si el usuario supera los 21,
        el usuario pierde inmediatamente. 
        */ 

        while (true) { 

         /* Mostrar tarjetas de usuario, y dejar que el usuario decida Seguir o Quedarse.*/ 

            System.out.println(); 
            System.out.println(); 
            System.out.println ("Tus cartas son: "); 
            for (int i = 0; i <usuarioMano.getCartasCantidad(); i ++) 
                 System.out.println (" " + usuarioMano.getCarta (i)); 
                 System.out.println ("Su total es: " + usuarioMano.getBlackjackValor ()); 
                 System.out.println (); 
                 System.out.println ("El crupier muestra  " + crupierMano.getCarta (0)); 
                 System.out.println (); 
                 System.out.print (" Seguir (S) o Quedarse (Q)? ");
            char usuarioAccion; // Respuesta del usuario, 'S' o 'Q'. Cambio de: Hit H -> S de seguir y de Stand S por Q de quedarse..
            do { 
               usuarioAccion = Character.toUpperCase (TextIO.getlnChar() ); 
               if (usuarioAccion != 'S' && usuarioAccion != 'Q') 
                  System.out.print ("Responda S o Q: "); 
            } while (usuarioAccion != 'S' && usuarioAccion != 'Q'); 

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario se para, 
               el ciclo termina (y es el turno del crupier de robar cartas). 
            Cambio de: Hit H -> S de seguir y de Stand S por Q de quedarse
             */ 

            if (usuarioAccion == 'Q') { 
                    // Loop ends; el usuario ha terminado de tomar cartas. 
                break;
            }
            else { // usuarioAccion es 'S'. Dale una tarjeta al usuario.  
                    // Si el usuario supera los 21, el usuario pierde. 
                Carta nuevaCarta = baraja.reparteCartas (); 
                usuarioMano.agregaCarta (nuevaCarta); 
                System.out.println (); 
                System.out.println ("El usuario pide una carta"); 
                System.out.println ("Su carta es la " + nuevaCarta); 
                System.out.println ("Su total es ahora " + usuarioMano.getBlackjackValor ()); 
                if (usuarioMano.getBlackjackValor ()> 21) { 
                    System.out.println (); 
                    System.out.println ("Has fallado al pasar de 21. Has perdido.");
                    System.out.println ("La otra carta del crupier es: " 
                                                       + crupierMano.getCarta (1)); 
                    return false;  
             } 
         } 
            
    } // end while loop 

    /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora, es 
    la oportunidad del crupier para dibujar. El distribuidor roba las cartas hasta que el 
    total del repartidor sea> 16. Si el repartidor supera los 21, el repartidor pierde. 
    */ 

    System.out.println (); 
    System.out.println ("El usuario se planta. Turno del crupier: "); 
    System.out.println ("Las cartas del crupier son: "); 
    System.out.println ("" + crupierMano.getCarta(0));
    System.out.println ("" + crupierMano.getCarta (1)); 
    while (crupierMano.getBlackjackValor() <= 16) { 
        Carta nuevaCarta = baraja.reparteCartas (); 
        System.out.println ("El crupier pide una carta y obtiene: " + nuevaCarta); 
        crupierMano.agregaCarta (nuevaCarta); 
        if (crupierMano.getBlackjackValor() > 21) { 
            System.out.println (); 
            System.out.println ("Crupier pierde al pasar de 21. Usted gana"); 
            return true; 
        } 
    } 
    System.out.println ("El total del crupier es " + crupierMano.getBlackjackValor ()); 

    /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
    puede determinar el ganador comparando los valores de sus manos. */ 

    System.out.println (); 
    if (crupierMano.getBlackjackValor () == usuarioMano.getBlackjackValor ()) { 
        System.out.println ("El crupier gana en un empate. Has perdido."); 
        return false; 
    } 
    else if (crupierMano.getBlackjackValor ()> usuarioMano.getBlackjackValor ()) { 
        System.out.println ("Gana la banca," + crupierMano.getBlackjackValor () 
                    + " puntos a " + usuarioMano.getBlackjackValor () + ".") ; 
        return false; 
    } 
    else { 
        System.out.println ("Usted gana, " + usuarioMano.getBlackjackValor ()
                    + " apunta a " + crupierMano.getBlackjackValor () + "."); 
        return true; 
        }
    
    } // finalizar jugarBlackjack() 
    
    
} // fin de clase Blackjack

