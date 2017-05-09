/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.ws.klijenti;

/**
 *
 * @author ljakopov. klasa služi za pozivanje metoda iz MeteoWS
 */
public class MeteoWSKlijent {

    public static String dajSveUredjaje() {
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajSveUredjaje();
    }

    public static Boolean dodajUredjaj(java.lang.String naziv, java.lang.String adresa) {
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dodajUredjaj(naziv, adresa);
    }

    public static java.util.List<org.foi.nwtis.ljakopov.ws.serveri.MeteoPodaci> dajSveMeteoPodatkeZaUredjaj(int id, long from, long to) {
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajSveMeteoPodatkeZaUredjaj(id, from, to);
    }
    
    

}
