package DAL;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinov.Item;

@Repository
public interface ItemRepository{
	List<Item> findAll();

	Item getById(String ID);
}
