/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;


import java.net.URI;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriBuilder;


/**
 * Jersey REST client generated for REST resource:DrzavljanstvoFacadeREST
 * [domen.drzavljanstvo]<br>
 * USAGE:
 * <pre>
 *        DrzavljanstvoService client = new DrzavljanstvoService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Sanja
 */
public class DrzavljanstvoService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HotelWebServices/webresources";

    public DrzavljanstvoService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        URI uri = UriBuilder.fromUri(BASE_URI).port(8080).build();
        webTarget = client.target(uri).path("domen.drzavljanstvo");
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T findAll_XML(GenericType<T> list) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(list);
    }

    public <T> T findAll_JSON(GenericType<T> list) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(list);
    }

    public void close() {
        client.close();
    }
    
}
