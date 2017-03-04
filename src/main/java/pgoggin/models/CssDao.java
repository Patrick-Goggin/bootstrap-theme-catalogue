package pgoggin.models;

import java.util.List;

/**
 * Created by patrickgoggin on 3/2/17.
 */
public interface CssDao {
    public Css getByThemeId(long id);
    public Css create(Css sheet);
    public void createMultiple(Iterable<Css> sheets);
    public List<Css> getAll();
    public Css get(long id);
    public List<Css> getByName(String name);
    public void update(Css sheet);
    public Css delete(long id);
    public void deleteMultiple(Iterable<Css> sheets);
    public void deleteAll();
}
