package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shinov.Item;
import com.shinov.Item.Type;

public class PresetsDAO implements PresetsRepository {

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
	public List<Item> findByID(int ID) {
		Connection connection = getConnection();
		List<Item> items = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement("select * from presets where id=" + ID);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				ItemsDAO dao = new ItemsDAO();

				items.add(dao.findByID(result.getInt(2)));
				items.add(dao.findByID(result.getInt(3)));
				items.add(dao.findByID(result.getInt(4)));
				items.add(dao.findByID(result.getInt(5)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public void update(List<Item> items, int id) {
		Connection connection = getConnection();
		PreparedStatement statement;

		
		for(Item item:items) {
			
		try {
			statement = connection.prepareStatement("update presets set " + item.getType() + "="+ item.getId() +" where id=" + id + ";");
			
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	}

}
