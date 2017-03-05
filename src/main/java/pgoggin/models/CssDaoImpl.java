package pgoggin.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pgoggin.utilities.Writer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by patrickgoggin on 3/2/17.
 */
@Transactional
@Component
public class CssDaoImpl implements CssDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CssRepository repo;

    @Autowired
    private Writer writer;

    public Css getByThemeId(long themeId){
        List<Css> list = entityManager.createQuery("from Css where themeId = :themeId").setParameter("themeId", themeId).getResultList();
        Css css = list.get(0);
        return css;
    }

    public Css create(Css sheet){
        String file = sheet.getThemeId().toString()+".css";
        writer.writeFile(sheet, file);
        sheet.setCss(sheet.getName());
        repo.save(sheet);
        return sheet;
    }

    public void createMultiple(Iterable<Css> sheets){
        repo.save(sheets);
    }

    public List<Css> getAll(){
         List<Css> list = new ArrayList<Css>();
        return list;
    }

    public Css get(long id){
        return repo.getOne(id);
    }

    public void update(Css sheet){
        entityManager.merge(sheet);
    }

    public Css delete(long id){
        Css css = repo.getOne(id);
        repo.delete(id);
        return css;
    }

    public Css deleteByObject(Css css){
        Css c = getByThemeId(css.getThemeId());
        repo.delete(css);
        return c;
    }
    public void deleteMultiple(Iterable<Css> sheets){
        repo.deleteInBatch(sheets);
    }

    public void deleteAll(){
        repo.deleteAllInBatch();
    }

    public List<Css> getByName(String name){
        List<Css> styleSheets = entityManager.createQuery("from Css where name = :name").setParameter("name", name).getResultList();
        return styleSheets;
    }
}
