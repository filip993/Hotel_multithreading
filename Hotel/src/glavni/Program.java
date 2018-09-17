package glavni;
import hotel.Hotel;
import recepcija.Ulaz;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Hotel h = new Hotel(5, 200);
        Ulaz u = new Ulaz(h);
        new Thread(h).start();
        new Thread(u).start();
        Scanner s = new Scanner(System.in);
        s.nextLine();
        u.ugasi();
        System.out.println("Recepcija prestala sa radom.");
        h.ugasi();
        System.out.println("Hotel prestao sa radom");


    }

}
