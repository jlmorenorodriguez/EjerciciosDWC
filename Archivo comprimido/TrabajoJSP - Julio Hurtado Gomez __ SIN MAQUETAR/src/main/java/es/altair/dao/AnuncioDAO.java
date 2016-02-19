package es.altair.dao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import es.altair.HibernateUtil;
import es.altair.bean.Anuncio;
import es.altair.bean.Usuario;

public class AnuncioDAO {
	public static ArrayList<Anuncio> listarAnuncios() {
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			anuncios = (ArrayList<Anuncio>) sesion.createQuery("FROM Anuncio a").list();
			
			

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

		return anuncios;
	}
	
	public static ArrayList<Anuncio> listarAnunciosPorUsuario(int idUsuario) {
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			anuncios = (ArrayList<Anuncio>) sesion.createQuery("FROM Anuncio a WHERE usuario_id=:id").setParameter("id", idUsuario).list();

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

		return anuncios;
	}


	public static byte[] obtenerImagenPorId(int idAnuncio) {
		byte[] imagen = null;

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			imagen = (byte[]) sesion.createQuery("SELECT a.imagen FROM Anuncio a WHERE idAnuncio=:id")
					.setParameter("id", idAnuncio).uniqueResult();

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

		return imagen;
	}

	public static void anadirAnuncio(Anuncio a) {
		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			sesion.save(a);

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
	}

	public static void borrarAnuncio(int idAnuncio) {
		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			Anuncio a = (Anuncio) sesion.get(Anuncio.class, idAnuncio);
			sesion.delete(a);

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
	}

	public static void borrarAnuncio(String uuid) {
		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			sesion.createQuery("DELETE FROM Anuncio WHERE uuid=:encrypt").setParameter("encrypt", uuid).executeUpdate();

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
	}

	public static Anuncio obtenerAnuncioPorId(int idAnuncio) {
		Anuncio a = null;

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			a = (Anuncio) sesion.get(Anuncio.class, idAnuncio);

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

		return a;
	}

	public static Anuncio obtenerAnuncioPorUUID(String uuid) {
		Anuncio a = null;

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			a = (Anuncio) sesion.createQuery("FROM Anuncio a WHERE a.uuid=:encrypt").setParameter("encrypt", uuid)
					.uniqueResult();

			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();

		return a;
	}

	public static void actualizarAnuncio( String descripcion, ByteArrayOutputStream os, Usuario usuario,
			String uuid) {
		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			sesion.beginTransaction();

			// Ha cambiado la imagen
			if (os != null) {
				Query query = sesion
						.createQuery(
								"UPDATE Anuncio SET descripcion=:desc, imagen=:img WHERE uuid=:encrypt")
						.setParameter("desc", descripcion)
						.setParameter("img", os.toByteArray()).setParameter("encrypt", uuid);
				int resultado = query.executeUpdate();
				System.out.println("FILAS: " + resultado);
			} else {
				Query query = sesion
						.createQuery("UPDATE Anuncio SET descripcion=:desc WHERE uuid=:encrypt")
						.setParameter("desc", descripcion)
						.setParameter("encrypt", uuid);
				int resultado = query.executeUpdate();
				System.out.println("FILAS: " + resultado);
			}
			




			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
	}

}
