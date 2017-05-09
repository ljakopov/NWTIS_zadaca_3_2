/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ljakopov.ws.klijenti;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author ljakopov, klasa služi za spajanje na REST servis. Konkretno koristi
 * se za spremanje uređaja u tablicu uređja na. šalje se JSON objekt.
 */
public class MeteoRESTKlijentPost {

    public Response zapisiRest(Object jsonObject) {
        MeteoRESTResourceContainer_JerseyClient meteoRESTResourceContainer_JerseyClient = new MeteoRESTResourceContainer_JerseyClient();
        return meteoRESTResourceContainer_JerseyClient.postJson(jsonObject);
    }

    static class MeteoRESTResourceContainer_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8084/ljakopov_zadaca_3_1/webresources";

        public MeteoRESTResourceContainer_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("meteoREST");
        }

        public Response postJson(Object requestEntity) throws ClientErrorException {
            return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
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
