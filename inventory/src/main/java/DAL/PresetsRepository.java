package DAL;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinov.Item;
import com.shinov.Item.Type;

@Repository
public interface PresetsRepository{
	List<Item> findByID(int ID);
	void update(List<Item> items, int id); 
}
