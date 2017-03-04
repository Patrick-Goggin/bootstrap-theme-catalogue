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


    private final RestTemplate restTemplate = new RestTemplate();

    public Css getByThemeId(long themeId){
        List<Css> list = entityManager.createQuery("from Css where themeId = :themeId").setParameter("themeId", themeId).getResultList();
        Css css = list.get(0);
        return css;
    }

    public Css create(Css sheet){
      //  Css sheet = new Css();
        //byte[] imageByteArray ....
        //String fileName = "image.png";

       // FileOutputStream fos = new FileOutputStream(fileLocation);
        //fos.write(imageByteArray);
        //fos.close();
       // System.out.println("sheet.getCss() = "+sheet.getCss());
        //System.out.println("sheet.getCss() = "+sheet.getCss());
        String file = sheet.getThemeId().toString()+".css";
        writer.writeFile(sheet, file);
        sheet.setCss(sheet.getName());
        repo.save(sheet);

        return sheet;
    }
    public void createMultiple(Iterable<Css> sheets){

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

    }
    public void deleteAll(){

    }

    public List<Css> getByName(String name){
        return new ArrayList<Css>();
    }
}
