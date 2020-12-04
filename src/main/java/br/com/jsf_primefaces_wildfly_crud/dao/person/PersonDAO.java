package br.com.jsf_primefaces_wildfly_crud.dao.person;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.jsf_primefaces_wildfly_crud.dao.common.GenericDAO;
import br.com.jsf_primefaces_wildfly_crud.dto.common.GenericDTO;
import br.com.jsf_primefaces_wildfly_crud.dto.person.PersonDTO;

@Stateless
//EJB para controlar a transação automaticamente
//@Stateless para não salvar o objeto após a chamada, é o normalmente utilizado
//@Stateful para manter o objeto na sessão, utilizado para carrinho de compras por exemplo
//@Startup @Singleton para executar somente uma vez na inicialização do sistema
//https://www.devmedia.com.br/ejb-introducao-ao-novo-enterprise-javabeans-3-2/30807
@LocalBean
//it's a way to permit to use implements class
public class PersonDAO extends GenericDAO implements Serializable{

	private static final long serialVersionUID = -6321526905232236895L;
	
	public List<PersonDTO> listAll() {
		
		String responseJson = getRequest("person/listAll");
        Type listType = new TypeToken<List<PersonDTO>>(){}.getType(); 
        List<PersonDTO> listPersonDTO = new Gson().fromJson(responseJson, listType);
		
		return listPersonDTO;
	}
}
