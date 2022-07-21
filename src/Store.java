import java.util.ArrayList;
import java.util.List;

public class Store {
	
	private List<PC> stock;
	private String name;
	
	public Store (String name) {
		
		stock=new ArrayList<PC>();
		this.name=name;
	}

	public List<PC> getStock() {
		return stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addToStock(PC pc) {
		
		stock.add(pc);
	}
	
	public void removeToStock(PC pc) {
		
		stock.remove(pc);
	}
	

	@Override
	public String toString() {
		return "Store [name=" + name + ", stock=" + stock + "]";
	}
}
