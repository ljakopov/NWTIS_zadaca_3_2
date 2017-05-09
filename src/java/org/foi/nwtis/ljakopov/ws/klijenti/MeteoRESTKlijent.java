/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.ws.klijenti;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author ljakopov, klasa služi za spajanje na REST servis. Konkretno koristi
 * se za dohvaćanje podataka uz pomoć ID uređaja
 */
public class MeteoRESTKlijent {

    /**
     * metoda služi vraća vrijednost podataka 
     * @param id
     * @return 
     */
    public String dajPodatkeZaId(String id) {
        MeteoRESTResource_JerseyClient mrestrjc = new MeteoRESTResource_JerseyClient(id);
        return mrestrjc.getJson();
    }

    static class MeteoRESTResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8084/ljakopov_zadaca_3_1/webresources";

        public MeteoRESTResource_JerseyClient(String id) {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            String resourcePath = java.text.MessageFormat.format("meteoREST/{0}", new Object[]{id});
            webTarget = client.target(BASE_URI).path(resourcePath);
        }

        public void setResourcePath(String id) {
            String resourcePath = java.text.MessageFormat.format("meteoREST/{0}", new Object[]{id});
            webTarget = client.target(BASE_URI).path(resourcePath);
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public void delete() throws ClientErrorException {
            webTarget.request().delete();
        }

        public String getJson() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        }

        public void close() {
            client.close();
        }
    }

}
