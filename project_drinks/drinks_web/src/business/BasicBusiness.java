package business;


import java.util.List;

public abstract class BasicBusiness<Entity> {

	public abstract void insert(Entity entity);
	public abstract void remove(Entity entity);
	public abstract List<Entity> list();
	public abstract void update(Entity entity);
}
