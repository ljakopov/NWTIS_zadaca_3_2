/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.web.zrna;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.foi.nwtis.ljakopov.ws.klijenti.MeteoWSKlijent;
import org.foi.nwtis.ljakopov.ws.serveri.Uredjaj;

/**
 *
 * @author ljakopov
 */
@Named(value = "odabirUredjaja")
@RequestScoped
public class OdabirUredjaja {
    
    private List<Uredjaj> uredjaji;
    private String id;

    /**
     * Creates a new instance of OdabirUredjaja
     */
    public OdabirUredjaja() {
    }

    public List<Uredjaj> getUredjaji() {
        uredjaji = MeteoWSKlijent.dajSveUredjaje();
        return uredjaji;
    }

    public void setUredjaji(List<Uredjaj> uredjaji) {
        this.uredjaji = uredjaji;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
