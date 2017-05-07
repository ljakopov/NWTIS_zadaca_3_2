/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.web.zrna;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import org.foi.nwtis.ljakopov.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.ljakopov.ws.serveri.Lokacija;

/**
 *
 * @author Lovro
 */
@Named(value = "odabirUredjaja")
@RequestScoped
public class OdabirUredjaja {

    private List<Uredjaj> uredjaji;
    
      
    private int id;

    /**
     * Creates a new instance of OdabirUredjaja
     */
    public OdabirUredjaja() {
    }

    public List<Uredjaj> getUredjaji() {
        JsonReader jsonReader = Json.createReader(new StringReader(MeteoWSKlijent.dajSveUredjaje()));
        JsonArray array = jsonReader.readArray();
        ArrayList<Uredjaj> u = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            Lokacija lokacija= new Lokacija();
            u.add(new Uredjaj(array.getJsonObject(i).getInt("uid"), array.getJsonObject(i).getString("naziv"), lokacija));
        }
        /*
        for (JsonValue aa : array) {
            JsonReader reader = Json.createReader(new StringReader(aa.toString()));
            JsonObject jo = reader.readObject();
            Lokacija lokacija = new Lokacija();
            
            System.out.println("OVO JE JSON NAZIV: " + jo.getString("naziv") + " ID: " + jo.getInt("uid"));
            System.out.println("OVO JE JSON LAT: " + jo.getString("lat") + " LON: " + jo.getString("lon"));
        */
            /*
            Lokacija lokacija = new Lokacija("kppo", "ijjojo");
            Uredjaj uredjaj = new Uredjaj(1, "mmkmkm", lokacija);
             
            u.add(new Uredjaj(jo.getInt("uid"), jo.getString("naziv"), lokacija));
        }
*/
        uredjaji=u;
        return uredjaji;
    }

    public void setUredjaji(List<Uredjaj> uredjaji) {
        this.uredjaji = uredjaji;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
