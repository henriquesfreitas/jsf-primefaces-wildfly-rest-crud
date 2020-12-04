package br.com.jsf_primefaces_wildfly_crud.dto.person;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.jsf_primefaces_wildfly_crud.dto.common.GenericDTO;
import br.com.jsf_primefaces_wildfly_crud.dto.common.LogDTO;

public class PersonDTO extends GenericDTO implements Serializable{

	private static final long serialVersionUID = 8340800142458494523L;
	
	private Long idPerson;
	
	private String name;
	
	private String cpf;
	
//	private String dateBirth;
	private LocalDate dateBirth;
	
	private String phone;
	
	private String email;
	
	private LogDTO logDTO;

	public PersonDTO(Long idPerson, String cpf, LocalDate dateBirth, String email, String name, String phone) {
		this.idPerson = idPerson;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
//		this.dateBirth = dateBirth.toString();
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public PersonDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public LocalDate getDateBirth() {
//		if(dateBirth != null) {
//			return LocalDate.of(Integer.valueOf(dateBirth.substring(0, 4)), Integer.valueOf(dateBirth.substring(5, 7)), Integer.valueOf(dateBirth.substring(8)));
//		}
//		return null;
//	}
//
//	public void setDateBirth(LocalDate dateBirth) {
//		this.dateBirth = dateBirth.toString();
//	}
//	
//	public String getDateBirthString() {
//		return dateBirth;
//	}
//	
//	public void setDateBirthString(String dateBirth) {
//		this.dateBirth = dateBirth;
//	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(LogDTO logDTO) {
		this.logDTO = logDTO;
	}
	
}
