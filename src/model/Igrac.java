package model;

public class Igrac {

    private int id;
    private String ime;
    private String prezime;
    private String klub;
    private String pozicija;
    private int dres;
    private float visina;
    private float tezina;
    private String datRodj;
    private String slika;
    private boolean visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKlub() {
        return klub;
    }

    public void setKlub(String klub) {
        this.klub = klub;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public int getDres() {
        return dres;
    }

    public void setDres(int dres) {
        this.dres = dres;
    }

    public float getVisina() {
        return visina;
    }

    public void setVisina(float visina) {
        this.visina = visina;
    }

    public float getTezina() {
        return tezina;
    }

    public void setTezina(float tezina) {
        this.tezina = tezina;
    }

    public String getDatRodj() {
        return datRodj;
    }

    public void setDatRodj(String datRodj) {
        this.datRodj = datRodj;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Igrac(int id, String ime, String prezime, String klub, String pozicija, int dres, float visina, float tezina, String datRodj, String slika, boolean visible) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.klub = klub;
        this.pozicija = pozicija;
        this.dres = dres;
        this.visina = visina;
        this.tezina = tezina;
        this.datRodj = datRodj;
        this.slika = slika;
        this.visible = visible;
    }

}
