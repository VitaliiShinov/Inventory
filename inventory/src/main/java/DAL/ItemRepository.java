package DAL;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinov.Item;
import com.shinov.Item.Type;

@Repository
public interface ItemRepository{
	List<Item> findAll();
	void saveAll(List<Item> items);
	void deleteAll();
	List<Item> getByType(Type type);
	Item findByID(int ID);
}
