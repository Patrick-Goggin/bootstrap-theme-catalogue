package pgoggin.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by patrickgoggin on 3/2/17.
 */
@Transactional
@Component
public class ThemeDaoImpl implements ThemeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ThemeRepository repo;

    public Theme create(Theme theme){
        repo.save(theme);
        return theme;
    }

    public void createMultiple(Iterable<Theme> themes){
        repo.save(themes);
    }

    public List<Theme> getAll(){
        List<Theme> list = repo.findAll();
        return list;
    }

    public Theme get(long id){
        Theme theme = repo.getOne(id);
        return theme;
    }

    public List<Theme> getByName(String name){
        List<Theme> themes = entityManager.createQuery("from Theme where name = :name").setParameter("name", name).getResultList();
        return themes;
    }

    public void update(Theme theme){
        entityManager.merge(theme);
    }

    public Theme delete(long id){
        Theme theme = repo.getOne(id);
        repo.delete(id);
        return theme;
    }

    public void deleteMultiple(Iterable<Theme> themes){
        repo.deleteInBatch(themes);
    }

    public void deleteAll(){
        repo.deleteAllInBatch();
        // int deletedCount = entityManager.createQuery("DELETE FROM Country").executeUpdate();
    }
}
