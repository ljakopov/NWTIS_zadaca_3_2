/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.web.zrna;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import org.foi.nwtis.ljakopov.ws.klijenti.MeteoRESTKlijent;
import org.foi.nwtis.ljakopov.ws.klijenti.MeteoRESTKlijentPost;
import org.foi.nwtis.ljakopov.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.ljakopov.ws.serveri.Lokacija;
import org.foi.nwtis.ljakopov.ws.serveri.MeteoPodaci;

/**
 *
 * @author ljakopov, klasa služi za dohvaćanje, spremanje, obradu i prikaz svih
 * podataka na korisničkom sučelju
 */
@Named(value = "odabirUredjaja")
@RequestScoped
public class OdabirUredjaja {

    /**
     * Početne vrijable
     */
    private List<Uredjaj> uredjaji;
    private List<String> id;
    private List<MeteoPodaci> listaMeteoPodaci = new ArrayList<>();
    private String naziv;
    private String adresa;
    Date vrijeme_od;
    Date vrijeme_do;

    /**
     * Creates a new instance of OdabirUredjaja
     */
    public OdabirUredjaja() {
    }

    /**
     * radi se o geteru koji vraća sve uređaje. Prima ih u obliku JSON formata,
     * obrađuje i sprema u listu uređaja
     */
    public List<Uredjaj> getUredjaji() {
        JsonReader jsonReader = Json.createReader(new StringReader(MeteoWSKlijent.dajSveUredjaje()));
        JsonArray array = jsonReader.readArray();
        ArrayList<Uredjaj> u = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Lokacija lokacija = new Lokacija();
            u.add(new Uredjaj(array.getJsonObject(i).getInt("uid"), array.getJsonObject(i).getString("naziv"), lokacija));
        }
        uredjaji = u;
        return uredjaji;
    }

    public void setUredjaji(List<Uredjaj> uredjaji) {
        this.uredjaji = uredjaji;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getVrijeme_od() {
        return vrijeme_od;
    }

    public void setVrijeme_od(Date vrijeme_od) {
        this.vrijeme_od = vrijeme_od;
    }

    public Date getVrijeme_do() {
        return vrijeme_do;
    }

    public void setVrijeme_do(Date vrijeme_do) {
        this.vrijeme_do = vrijeme_do;
    }

    public List<MeteoPodaci> getListaMeteoPodaci() {
        return listaMeteoPodaci;
    }

    public void setListaMeteoPodaci(List<MeteoPodaci> listaMeteoPodaci) {
        this.listaMeteoPodaci = listaMeteoPodaci;
    }

    /**
     * metoda služi za spremanje novog uređaja pomoću SOAP klijenta
     */
    public void dodajSoap() {
        MeteoWSKlijent.dodajUredjaj(naziv, adresa);
    }

    /**
     * metoda služi za spremanje novog uređaja pomoću REST klijenta. Ona šalje
     * zahtjev u oblik JSON formata
     */
    public void dodajRest() {
        System.out.println("OVO JE NAZIV: " + naziv);
        System.out.println("OVO JE ADRESA: " + adresa);
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("naziv", naziv);
        jsonObjectBuilder.add("adresa", adresa);
        MeteoRESTKlijentPost meteoRESTKlijentPost = new MeteoRESTKlijentPost();
        meteoRESTKlijentPost.zapisiRest(jsonObjectBuilder.build());
    }

    /**
     * metoda služi za dohvat svih podataka nekog uređaja sa baze podataka
     * uodređenom intervalu. Podatke prikazuje u tablici.
     */
    public void preuzmiPodatkeSoap() {
        if (!id.isEmpty() && vrijeme_do != null && vrijeme_od != null) {
            for (int i = 0; i < id.size(); i++) {
                if (id.size() == 1) {
                    System.out.println("VRIEJEM: " + vrijeme_od.toString() + " " + vrijeme_do.toString());
                    long vrijemeLongDo = (vrijeme_do.getTime()) / 1000;
                    long vrijemeLongOd = (vrijeme_od.getTime()) / 1000;
                    listaMeteoPodaci = MeteoWSKlijent.dajSveMeteoPodatkeZaUredjaj(Integer.parseInt(id.get(i)), vrijemeLongOd, vrijemeLongDo);
                }
            }
        }
    }

    /**
     * metoda služi za dohvaćanje podataka trenutačnog vremena uz pomoć REST
     * servisa. Prima JSON format, obrađuje ga i prikazuje u tablici
     */
    public void preuzmiPodatkeRest() {
        if (id.size() >= 2) {
            for (int i = 0; i < id.size(); i++) {
                MeteoPodaci meteoPodaci = new MeteoPodaci();
                MeteoRESTKlijent meteoRESTKlijent = new MeteoRESTKlijent();
                String a = meteoRESTKlijent.dajPodatkeZaId(id.get(i));
                System.out.println("OVO JE JSON: " + a);
                JsonReader jsonReader = Json.createReader(new StringReader(a));
                JsonObject object = jsonReader.readObject();
                meteoPodaci.setTemperatureValue(Float.parseFloat(object.getString("temp")));
                meteoPodaci.setHumidityValue(Float.parseFloat(object.getString("vlaga")));
                meteoPodaci.setPressureValue(Float.parseFloat(object.getString("tlak")));
                listaMeteoPodaci.add(meteoPodaci);
            }
        }
    }
}
