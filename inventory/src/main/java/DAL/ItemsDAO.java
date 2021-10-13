package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinov.Item;
import com.shinov.Item.Type;


@Repository
public class ItemsDAO implements ItemRepository {

	private final static String DB = "jdbc:mysql://localhost:3306/inventory";

	private static Connection getConnection() {
		try {
			return DriverManager.getConnection(DB, "root", "admin");
		} catch (SQLException e) {

			System.err.println("Unable to connect to database" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Item> findAll() {

		Connection connection = getConnection();
		List<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement("select * from item");

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Item item = new Item();
				item.setId(result.getInt(1));

				item.setDefense(result.getInt(2));
				item.setAttack(result.getInt(3));

				item.setName(result.getString(4));
				item.setType(Type.values()[result.getInt(5)]);
				items.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public Item findByID(String ID) {

		Connection connection = getConnection();
		Item item = null;

		try {
			PreparedStatement statement = connection.prepareStatement("select * from item where id = " + ID);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				item = new Item();
				item.setId(result.getInt(1));

				item.setDefense(result.getInt(2));
				item.setAttack(result.getInt(3));

				item.setName(result.getString(4));
				item.setType(Type.values()[result.getInt(5)]);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}

	@Override
	public void saveAll(List<Item> items) {
		Connection connection = getConnection();

		try {

			for (Item item : items) {

				// I cannot handle apostrophes in my sql querry thats why I do this sfuff
				if (item.getName().contains("'"))
					item.setName(item.getName().replace("'", "''"));
				;

				PreparedStatement statement = connection
						.prepareStatement("insert INTO item (id, name, type, attack, defence) VALUES(" + item.getId()
								+ ", '" + item.getName() + "', " + item.getType().ordinal() + ", " + item.getAttack()
								+ ", " + item.getDefense() + ");");

				statement.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAll() {
		Connection connection = getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("delete from item;");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
