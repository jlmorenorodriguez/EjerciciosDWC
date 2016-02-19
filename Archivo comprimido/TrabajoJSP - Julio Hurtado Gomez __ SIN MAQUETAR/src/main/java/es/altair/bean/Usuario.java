
package es.altair.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	private int idUsuario;
	private String nombre;
	private String login;
	private String password;
	private int tipo;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private Set<Anuncio> anuncios;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	public Usuario(int idUsuario, String nombre, String login, String password, int tipo, Set<Anuncio> anuncios) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.tipo = tipo;
		this.anuncios = anuncios;
	}


	public Usuario(String nombre, String login, String password, int tipo) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.tipo = tipo;
	}


	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + login + ", password=" + password + ", nombre=" + nombre
				+ ", tipo=" + tipo + "]";
	}

}