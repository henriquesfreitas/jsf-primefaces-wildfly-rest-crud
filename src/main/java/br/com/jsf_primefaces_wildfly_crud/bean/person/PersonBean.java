package br.com.jsf_primefaces_wildfly_crud.bean.person;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import br.com.jsf_primefaces_wildfly_crud.bean.common.GenericBean;
import br.com.jsf_primefaces_wildfly_crud.common.debug.LogInterceptor;
import br.com.jsf_primefaces_wildfly_crud.common.debug.Logger;
import br.com.jsf_primefaces_wildfly_crud.dao.person.PersonDAO;
import br.com.jsf_primefaces_wildfly_crud.dao.person.PersonDAOException;
import br.com.jsf_primefaces_wildfly_crud.dto.person.PersonDTO;

//http://blog.triadworks.com.br/nao-misture-anotacoes-do-jsf-com-anotacoes-do-cdi
//Percebemos aqui que temos um problema, pois o escopo de Request é curto demais para manter o bean na quantidade de tempo que desejamos
//e o escopo Session é longo demais e acaba mantendo o bean além do que desejamos. O ViewScoped veio para tentar resolver esse problema, 
//criando um meio-termo entre o Request e o Session.
//O ViewScoped mantém o estado do bean enquanto houver requisições da mesma view/página, e quando ele muda de página o estado do bean é descartado.
//https://antoniogoncalves.org/2011/09/25/injection-with-cdi-part-iii/
@Named
@ViewScoped
@Interceptors({LogInterceptor.class})
public class PersonBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = -8898732422678618156L;


	//https://stackoverflow.com/questions/8138232/should-i-use-ejb-or-inject
	//you could use @EJB instead of @Inject, it's makes sense since 
	//ContaDAO use the EJB @Stateless, but with @Inject you can have more options and it's recommend to Java EE 6 or up
	//"@Inject can inject any bean, while @EJB can only inject EJBs. You can use either to inject EJBs, but I'd prefer @Inject everywhere."
	@Inject
	private PersonDAO personDAO;
	
	private PersonDTO personDTO;
	
	private List<PersonDTO> listPersonDTO;
	
	@PostConstruct
	public void init() {
		newPerson();
		listAll();
	}
	
	public void newPerson() {
		personDTO = new PersonDTO();
	}

//		anotação para começar uma transação com CDI, porém como será controlado pelo EJB na classe DAO
//		não necessidade de colocar essa anotação, a menos que queira que esse método inteiro fique
//		dentro de uma transação
//		@Transactional 
	@SuppressWarnings("unchecked")
	public void save() {
		logger.printInfo("saving "+ personDTO.getName());
		try {
			personDAO.save("person/save", personDTO, PersonDTO.class);
			listAll();
			newPerson();
			addMessageInfo("Pessoa salva com sucesso!");
		} catch (PersonDAOException e) {
			addMessageException(e.getLocalizedMessage());
		}
	}
	
	public void editPerson(PersonDTO personDTO) {
		logger.printDebug("editing "+ personDTO.getName());
		this.personDTO = personDTO;
	}
	
	public void removePerson(Long idPerson) {
		try {
			personDAO.remove("person/"+idPerson);
			listAll();
			addMessageInfo("Pessoa removida com sucesso!");
		} catch (Exception e) {
			addMessageException(e.getLocalizedMessage());
		}
	}

	private void listAll() {
		listPersonDTO = personDAO.listAll();
	}

	public PersonDTO getPersonDTO() {
		return personDTO;
	}

	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}

	public List<PersonDTO> getListPersonDTO() {
		return listPersonDTO;
	}

	public void setListPersonDTO(List<PersonDTO> listPersonDTO) {
		this.listPersonDTO = listPersonDTO;
	}
	
}
