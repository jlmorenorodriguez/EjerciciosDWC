package es.altair.dao;

import org.hibernate.Session;

import es.altair.HibernateUtil;
import es.altair.bean.Usuario;

public class UsuarioDAO {

	private static final String PASSPHRASE = "Anuncios123$%";

	public static Usuario comprobarUsuario(String login, String password) {
		Usuario usu = null;

		HibernateUtil.buildSessionFactory();
		Session sesion = null;
		try {
			HibernateUtil.openSessionAndBindToThread();

			sesion = HibernateUtil.getSessionFactory().getCurrentSession();

			sesion.beginTransaction();

			usu = (Usuario) sesion
					.createQuery("SELECT u FROM Usuario u WHERE login=:l AND password=AES_ENCRYPT(:p, :passphrase)")
					.setParameter("l", login).setParameter("p", password).setParameter("passphrase", PASSPHRASE)
					.uniqueResult();
			
			System.out.println(login + " " + password);

			sesion.getTransaction().commit();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}

		HibernateUtil.closeSessionFactory();

		return usu;
	}

}
