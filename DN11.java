import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DN11 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-----");
        CestnoOmrezje eden = CestnoOmrezje.izDatoteke("primer_omrezje.txt");
        System.out.println(CestnoOmrezje.izDatoteke("primer_omrezje.txt"));
    }
}

// CLASA VOZLISCE, x i y koordinati i id
class Vozlisce {
    private int id;
    private double x;
    private double y;

    Vozlisce(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
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
    private List<Cesta> ceste;

    Cesta(Vozlisce a, Vozlisce b, int max_brzina) {
        this.a = a;
        this.b = b;
        this.max_brzina = max_brzina;
        this.ceste = new ArrayList<Cesta>();
    }
    
    // DODAJ ULICA KOJA SE POVRZUVA SO ISTITE TOCKI
    // dodajCesto(Cesta cesta) {
    //     return;
    // }
    
    // ZEMI DOLZINA NA ULICATA, RASTOJANIE OD TOCKA1 DO TOCKA2
    // double getDolzina() {
    //     // koren iz (((x1-x2)x 111.12)^2 +  ((y1-y2) x 77.4)^2)
        
    //     return Math.sqrt(((x1-x2)x 111.12)**2 +  ((y1-y2) x 77.4)**2);
    // }

    // ISPECATI -> Cesta (0,1): dolzina=17.70 km, omejitev=130 km/h
    // toString(){
    // }
}

// KLASA CESTNOOMREZJE, LISTA NA VOZLISCE(TOCKI) I CESTI(ULICI)
class CestnoOmrezje {
    private Vozlisce[] vozlisca;
    private Cesta[] ceste;

    CestnoOmrezje(Vozlisce[] vozlisca, Cesta[] ceste) {
        this.vozlisca = vozlisca;
        this.ceste = ceste;
    }

    // PROCITAJ OD TEXT FILE I NAPOLNI GI LISTITE, RETURN OBJEKT OD TIP CESTNOOMREZJE
    static CestnoOmrezje izDatoteke(String imeDatoteke) throws FileNotFoundException {
        CestnoOmrezje eden;
        File file = new File(imeDatoteke);
        Scanner scan = new Scanner(file);

        String prv = scan.nextLine();
        String[] del = prv.split("");
        
        int br_voz = Integer.parseInt(del[0]);
        int br_pat = Integer.parseInt(del[1]);

        // ArrayList<Vozlisce> voz = new ArrayList<Vozlisce>();
        Vozlisce[] voz = new Vozlisce[br_voz];
        Cesta[] cest = new Cesta[br_pat];

        for (int i = 0; i < br_voz; i++){
            String vrstica = scan.nextLine();
            String[] d = vrstica.split("");
            
            double kor_x = Double.parseDouble(d[1]);
            double kor_y = Double.parseDouble(d[2]);

            if (d[0] == "vozlisce") {
                Vozlisce vozlisce = new Vozlisce(i, kor_x, kor_y);
                voz[i] = vozlisce;
            } else if (d[0] == "kraj") {
                String ime_kraj = d[3];
                Kraj kraj = new Kraj(i, kor_x, kor_y, ime_kraj);
                voz[i] = kraj;
            } else if (d[0] == "crpalka") {
                double cena_b = Double.parseDouble(d[3]);
                double cena_d = Double.parseDouble(d[4]);
                Crpalka crpalka = new Crpalka(i, kor_x, kor_y, cena_b, cena_d);
                voz[i] = crpalka;
            }
        }

        for (int i = 0; i < br_pat; i++) {
            String vrstica = scan.nextLine();
            String[] d = vrstica.split("");

            int voz_c_1 = Integer.parseInt(d[0]);
            int voz_c_2 = Integer.parseInt(d[1]);
            int brzina = Integer.parseInt(d[2]);

            Cesta cesta = new Cesta(voz[voz_c_1], voz[voz_c_2], brzina);
            cest[i] = cesta;
        }
          
          CestnoOmrezje cest_omr = new CestnoOmrezje(voz, cest);
          System.out.println("cest_omr");
          return cest_omr;
    }
}

