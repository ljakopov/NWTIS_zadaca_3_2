/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.ws.klijenti;

/**
 *
 * @author Lovro
 */
public class MeteoWSKlijent {

    public static String dajSveUredjaje() {
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service service = new org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS_Service();
        org.foi.nwtis.ljakopov.ws.serveri.GeoMeteoWS port = service.getGeoMeteoWSPort();
        return port.dajSveUredjaje();
    }
    
    
    
}
