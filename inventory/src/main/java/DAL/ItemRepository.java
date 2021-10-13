package DAL;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinov.Item;

@Repository
public interface ItemRepository{
	List<Item> findAll();
	Item findByID(String ID);
	void saveAll(List<Item> items);
	void deleteAll();
}
