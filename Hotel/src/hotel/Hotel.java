package hotel;

import gosti.Musterija;

public class Hotel implements Runnable {
    Musterija[] gosti;
    private int brojnoStanje;
    private boolean radi = true;
    private double cenaB;

    public Hotel(int kapacitet, double cenaB) {
        gosti = new Musterija[kapacitet];
        brojnoStanje = 0;
        this.cenaB = cenaB;
    }


    public synchronized void smesti(Musterija m) {
        boolean rez = false;
        while (brojnoStanje == gosti.length) {
            try {
                wait(); // po pozivu metode nit se pauzira sve do pozivanja metode notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < gosti.length; i++) {
            if (gosti[i] == null) {
                gosti[i] = m;
                rez = true;
                brojnoStanje++;
                System.out.println(m + " smesten u niz na indeksu:" + i);
                break;
            }
        }
        if (!rez) {
            System.out.println("Nema slobodnih mesta u hotelu!!");
        }

    }

    private synchronized void smanjiVB() {
        for (int i = 0; i < gosti.length; i++) {
            if (gosti[i] != null) {
                if (gosti[i].getTime() > 0) {
                    System.out.println("Prosao je dan za mesteriju" + gosti[i]);
                    gosti[i].smanjiDan();
                } else {
                    System.out.println(gosti[i] + " napustio je hotel. Stanje " + brojnoStanje + "/" + gosti.length);
                    gosti[i] = null;
                    brojnoStanje--;
                    notify();//obavestavanje niti koja je pauzirana de je promenjeno neko stanje tj da je oslobodjeno neko mesto u hotelu
                }
            }
        }

    }

    public double getCenaB() {
        return cenaB;
    }

    public void ugasi() {
        radi = false;
    }

    @Override
    public void run() {
        System.out.println("Pokrenut rad hotela. Kapacitet:" + gosti.length + " Cena boravka po danu:" + cenaB);
        while (radi) {
            smanjiVB();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
