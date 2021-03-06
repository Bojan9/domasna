import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DN11 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args[0].equals("ceste")){
            System.out.println("Omrezje vsebuje naslednje ceste:");
            CestnoOmrezje.izDatoteke(args[1]);
        }
    }
}

// CLASA VOZLISCE, x i y koordinati i id
class Vozlisce {
    private int id;
    private double x;
    private double y;
    private List<Cesta> ceste;

    Vozlisce(int id, double x, double y) {
        this.setID(id);;
        this.setX(x);
        this.setY(y);
        this.ceste = new ArrayList<Cesta>();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    // DODAJ ULICA KOJA SE POVRZUVA SO ISTITE TOCKI
    Cesta dodajCesto(Cesta cesta) {
        ceste.add(cesta);
        return (Cesta) ceste;
    }
}

// CLASA KRAJ, x i y koordinati i id + ime na grad
class Kraj extends Vozlisce {
    private String kraj;

    Kraj(int id, double x, double y, String kraj) {
        super(id, x, y);
        this.kraj = kraj;
    }
}

// CLASA CRPALKA, x i y koordinati i id + cena na benzin i dizel
class Crpalka extends Vozlisce {
    private double cena_b;
    private double cena_d;

    Crpalka(int id, double x, double y, double cena_b, double cena_d) {
        super(id, x, y);
        this.cena_b = cena_b;
        this.cena_d = cena_d;
    }
}

// CLASA CESTA, pocetna tocka i krajna tocka (VOZLISCE), maksimalna brzina i lista na ulici koi se povrzuvaat so nea odnosno so tockite
class Cesta {
    private Vozlisce a;
    private Vozlisce b;
    private int max_brzina;

    Cesta(Vozlisce a, Vozlisce b, int max_brzina) {
        this.a = a;
        this.b = b;
        this.max_brzina = max_brzina;
    }
    
    // ZEMI DOLZINA NA ULICATA, RASTOJANIE OD TOCKA1 DO TOCKA2
    double getDolzina() {
        double x1 = a.getX();
        double x2 = b.getX();
        double y1 = a.getY();
        double y2 = b.getY();
        return Math.sqrt(Math.pow(((x1-x2)* 111.12),2) + Math.pow(((y1-y2)* 77.4),2));
    }

    // ISPECATI -> Cesta (0,1): dolzina=17.70 km, omejitev=130 km/h
    public String toString(){
        return "Cesta " + "(" + a.getID() + "," + b.getID() + "): dolzina=" + String.format("%.2f", getDolzina()) + " km, omejitev=" + max_brzina + " km/h";
    }
}

// KLASA CESTNOOMREZJE, LISTA NA VOZLISCE(TOCKI) I CESTI(ULICI)
class CestnoOmrezje {
    private Vozlisce[] vozlisca;
    private Cesta[] ceste;

    CestnoOmrezje(Vozlisce[] vozlisca, Cesta[] ceste) {
        this.vozlisca = vozlisca;
        this.ceste = ceste;
    }

    void printCes(){
        for(int i = 0; i < ceste.length; i++){
            System.out.println(ceste[i]);
        }
    }

    // PROCITAJ OD TEXT FILE I NAPOLNI GI LISTITE, RETURN OBJEKT OD TIP CESTNOOMREZJE
    static CestnoOmrezje izDatoteke(String imeDatoteke) throws FileNotFoundException {
        File file = new File(imeDatoteke);
        Scanner scan = new Scanner(file);

        String prv = scan.nextLine();
        String[] del = prv.split(" ");
        
        int br_voz = Integer.parseInt(del[0]);
        int br_pat = Integer.parseInt(del[1]);

        Vozlisce[] voz = new Vozlisce[br_voz];
        Cesta[] cest = new Cesta[br_pat];

        for (int i = 0; i < br_voz; i++){
            String vrstica = scan.nextLine();
            String[] d = vrstica.split(" ");
            
            double kor_x = Double.parseDouble(d[1]);
            double kor_y = Double.parseDouble(d[2]);

            if (d[0].equals("vozlisce")) {
                Vozlisce vozlisce = new Vozlisce(i, kor_x, kor_y);
                voz[i] = vozlisce;
            } else if (d[0].equals("kraj")) {
                String ime_kraj = d[3];
                if (d.length > 4){
                    ime_kraj = ime_kraj + " " + d[4];
                }
                Kraj kraj = new Kraj(i, kor_x, kor_y, ime_kraj);
                voz[i] = kraj;
            } else if (d[0].equals("crpalka")) {
                double cena_b = Double.parseDouble(d[3]);
                double cena_d = Double.parseDouble(d[4]);
                Crpalka crpalka = new Crpalka(i, kor_x, kor_y, cena_b, cena_d);
                voz[i] = crpalka;
            }
        }

        for (int i = 0; i < br_pat; i++) {
            String vrstica = scan.nextLine();
            String[] d = vrstica.split(" ");

            int voz_c_1 = Integer.parseInt(d[0]);
            int voz_c_2 = Integer.parseInt(d[1]);
            int brzina = Integer.parseInt(d[2]);

            Cesta cesta = new Cesta(voz[voz_c_1], voz[voz_c_2], brzina);

            // voz[voz_c_1].dodajCesto(cesta);
            // voz[voz_c_2].dodajCesto(cesta);
            
            cest[i] = cesta;
        }
          
        CestnoOmrezje cest_omr = new CestnoOmrezje(voz, cest);
        cest_omr.printCes();
        return cest_omr;
    }
}
