package DAL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.shinov.Item;

public class DAO implements ItemRepository{
	private static SessionFactory factory;
	private static Item item;

	@Override
	public Item getById(String ID) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Item");
		EntityManager entityManager = emf.createEntityManager();

		Session session = entityManager.unwrap(org.hibernate.Session.class);
		SessionFactory factory = session.getSessionFactory();

		session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			item = (Item) session.get(Item.class, ID);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return item;
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
