package gosti;

public class Musterija {
    private int time;
    private static int idG = 0;
    private int id;
    private double funds;

    public Musterija(int vremeB, double funds) {
        this.time = vremeB;
        this.id = ++idG;
        this.funds = funds;
    }

    public int getTime() {
        return time;
    }

    public void settime(int time) {
        this.time = time;
    }

    public double getFunds() {
        return funds;
    }

    public void smanjiDan() {
        time--;
    }

    public String toString() {
        return "[Customer " + id + " / b:" + time + "]";
    }
}

