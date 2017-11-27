package blackjack;

import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> mano;
    /**
     * Crea una mano que está inicialmente vacía.
     **/
    public Mano() {
        mano = new ArrayList<Carta>();
    }

    public void limpiaMano() {
        mano.clear();
    }

    public void agregaCarta(Carta c) {
        if (c == null)
            throw new NullPointerException("No se puede agregar una carta nula a una mano.");
        mano.add(c);
    }

    public void retiraCarta(Carta c) {
        mano.remove(c);
    }

    public void retiraCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size())
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        mano.remove(posicion);
    }

    public int getCartasCantidad() {
        return mano.size();
    }

    public Carta getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size())
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        return mano.get(posicion);
    }

    public void sortBySuit() {
        ArrayList<Carta> nuevaMano = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;
            Carta c = mano.get(0);
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getTraje() < c.getTraje() || (c1.getTraje() == c.getTraje() && c1.getValor() < c.getValor())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    public void clasePorValor() {
        ArrayList<Carta> nuevaMano = new ArrayList<Carta>();
        while (mano.size() > 0) {
            int pos = 0;
            Carta c = mano.get(0);
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getValor() < c.getValor() || (c1.getValor() == c.getValor() && c1.getTraje() < c.getTraje())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    public void Mano() {
    }

    public void untitledMethod() {
    }
}
