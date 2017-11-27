package blackjack;

public class ManoBlackjack extends Mano {

    public int getBlackjackValor() {
        int valor;
        boolean as;
        ;
        int cartas;
        valor = 0;
        as = false;
        cartas = getCartasCantidad();
        for (int i = 0; i < cartas; i++) {
            Carta carta;
            int cartaValor;
            carta = getCarta(i);
            cartaValor = carta.getValor();
            if (cartaValor > 10) {
                cartaValor = 10;
            }
            if (cartaValor == 1) {
                as = true;
            }
            valor = valor + cartaValor;
        }
        if (as == true && valor + 10 <= 21)
            valor = valor + 10;
        return valor;
    }

}
