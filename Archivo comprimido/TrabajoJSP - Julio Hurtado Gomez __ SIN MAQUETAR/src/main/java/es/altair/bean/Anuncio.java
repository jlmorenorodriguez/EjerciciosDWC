package es.altair.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anuncios")
public class Anuncio implements Serializable {

	
	
	public Anuncio() {
		super();
	}

	@Id
	private int idAnuncio;
	private String descripcion;
	private byte[] imagen;
	private String uuid;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Anuncio(int idAnuncio, String descripcion, byte[] imagen, String uuid, Usuario usuario) {
		super();
		this.idAnuncio = idAnuncio;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.uuid = uuid;
		this.usuario = usuario;
	}

	public Anuncio(String descripcion, byte[] imagen, String uuid, Usuario usuario) {
		super();
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.uuid = uuid;
		this.usuario = usuario;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Anuncio [idAnuncio=" + idAnuncio + ", descripcion=" + descripcion + ", imagen="
				+ Arrays.toString(imagen) + ", uuid=" + uuid + ", usuario=" + usuario + "]";
	}

	
	
	
}