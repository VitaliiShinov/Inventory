package DAL;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinov.Item;

@Repository
public interface InventoryRepository{
	List<Item> findAll();
	void saveAll(List<Item> items);
	void deleteAll();
}
