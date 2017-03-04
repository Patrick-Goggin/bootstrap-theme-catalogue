package pgoggin.models;


import java.util.List;

/**
 * Created by patrickgoggin on 3/2/17.
 */

public interface ThemeDao {
    public Theme create(Theme theme);
    public void createMultiple(Iterable<Theme> themes);
    public List<Theme> getAll();
    public Theme get(long id);
    public List<Theme> getByName(String name);
    public void update(Theme theme);
    public Theme delete(long id);
    public void deleteMultiple(Iterable<Theme> themes);
    public void deleteAll();
}
