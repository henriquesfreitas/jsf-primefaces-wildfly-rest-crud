package br.com.jsf_primefaces_wildfly_crud.dao.common;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.jsf_primefaces_wildfly_crud.dao.person.PersonDAOException;
import br.com.jsf_primefaces_wildfly_crud.dto.person.PersonDTO;

@Stateless
public class GenericDAO<E> implements Serializable {
	
	private static final long serialVersionUID = -8867507279541187826L;
	
	protected String getRequest(String endpoint) {
		String URL_Server = System.getProperty("URL_Server");
		
		Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(URL_Server+endpoint);
        Response response = target.request().get();
        String responseJson = response.readEntity(String.class);
        response.close();
        
		return responseJson;
	}
	
	protected Response.Status postRequest(String endpoint, String personJson) {
		String URL_Server = System.getProperty("URL_Server");
		
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(URL_Server+endpoint);
		Response response = target.request().post(Entity.entity(personJson, MediaType.APPLICATION_JSON));
		Response.Status responseStatus = Response.Status.fromStatusCode(response.getStatus());
		response.close();
		
		return responseStatus;
	}
	
	protected Response.Status deleteRequest(String endpoint) {
		String URL_Server = System.getProperty("URL_Server");
		
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(URL_Server+endpoint);
		Response response = target.request().delete();
		Response.Status responseStatus = Response.Status.fromStatusCode(response.getStatus());
		response.close();
		
		return responseStatus;
	}
	
	public <T> void save(String endpoint, T entityDTO, Class<E> clazz) throws PersonDAOException{
        String json = new Gson().toJson(entityDTO, clazz);
        postRequest(endpoint, json);
	}
	
	public void remove(String endpoint) {
		deleteRequest(endpoint);
	}

}
