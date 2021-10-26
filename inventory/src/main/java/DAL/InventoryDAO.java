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
public class InventoryDAO implements InventoryRepository {

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
	public Item findById(int id) {

		Connection connection = getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(
					"select item.id, item.name, item.type, item.attack, item.defence from item right join inventory on item.id = inventory.item "
					+ "where item.id = " + id + ";");

			ResultSet result = statement.executeQuery();

			while (result.next() && result.getInt(1) == id) {
				Item item = new Item();
				item.setId(result.getInt(1));

				item.setName(result.getString(2));
				item.setType(Type.values()[result.getInt(3)]);
				item.setDefense(result.getInt(4));
				item.setAttack(result.getInt(5));

				return item;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public List<Item> findAll() {

		Connection connection = getConnection();
		List<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(
					"select item.id, item.name, item.type, item.attack, item.defence from item right join inventory on item.id = inventory.item;");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Item item = new Item();
				item.setId(result.getInt(1));

				item.setName(result.getString(2));
				item.setType(Type.values()[result.getInt(3)]);
				item.setDefense(result.getInt(4));
				item.setAttack(result.getInt(5));

				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public void saveAll(List<Item> items) {
		Connection connection = getConnection();
		int id = 0;
		try {

			for (Item item : items) {

				// I cannot handle apostrophes in my sql querry thats why I do this sfuff
				if (item.getName().contains("'"))
					item.setName(item.getName().replace("'", "''"));
				;

				PreparedStatement statement = connection.prepareStatement(
						"insert INTO inventory (id, item) VALUES(" + id++ + ", " + item.getId() + ");");

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
			PreparedStatement statement = connection.prepareStatement("delete from inventory;");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
