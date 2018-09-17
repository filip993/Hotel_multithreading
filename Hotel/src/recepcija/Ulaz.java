package recepcija;

import gosti.Musterija;
import hotel.Hotel;

public class Ulaz implements Runnable {
    private Hotel h;
    private boolean works = true;

    public Ulaz(Hotel h) {
        this.h = h;
    }

    public void ugasi() {
        works = false;
    }

    public void dodajMusteriju() {
        Musterija m = new Musterija((int) Math.random() * 9 + 7, Math.random() * 1000 + 1000);
        if (h.getCenaB() * m.getTime() <= m.getFunds()) {
            h.smesti(m);
        } else if (h.getCenaB() > m.getFunds()) {
            System.out.println(m + " nema dovoljno sredstava.");
        } else {
            m.settime((int) (m.getFunds() / h.getCenaB())); ;
            System.out.println(m + " ima sredstava samo za " + m.getTime() + " dana boravka.");
            h.smesti(m);
        }
    }

    @Override
    public void run() {
        System.out.println("Pokrenut rad recepcije.");
        while (works) {
            dodajMusteriju();
            try {
                Thread.sleep((int) Math.random() * 500 + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
