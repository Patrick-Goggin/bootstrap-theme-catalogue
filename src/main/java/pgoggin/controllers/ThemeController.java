package pgoggin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pgoggin.models.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.V;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by patrickgoggin on 3/2/17.
 */
@Controller
@RequestMapping("/")
public class ThemeController {

    @Autowired
    ThemeDao dao;

    @Autowired
    CssDao cssDao;

    @Autowired
    CurrentThemeRepository cRepo;

    @Autowired
    ThemeRepository tRepo;


    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public CurrentTheme getCurrentTheme(HttpServletRequest req){
        if(cRepo.findAll().size() < 1){
            CurrentTheme theme = new CurrentTheme();
            cRepo.save(theme);
        }
        System.out.println("(From getCurrentTheme()) findAll.get(0).getId() = " + cRepo.findAll().get(0).getId());
        System.out.println("(From getCurrentTheme()) findAll.get(0).getId() = " + cRepo.findAll().get(0).getId());
        System.out.println("(From getCurrentTheme()) findAll.get(0).getId() = " + cRepo.findAll().get(0).getId());
            return cRepo.findAll().get(0);
    }

    @RequestMapping(value = {"/current"}, method = RequestMethod.PATCH)
    @ResponseBody
    public CurrentTheme update(@RequestBody CurrentTheme theme, HttpServletRequest req){
        theme.setId(cRepo.findAll().get(0).getId());
        Long id = theme.getId();
        cRepo.save(theme);
        return cRepo.getOne(id);
    }

    @RequestMapping(value = {"/theme/create"}, method = RequestMethod.POST)
    @ResponseBody
    public Theme create(@RequestBody Theme theme, HttpServletRequest req) {
        Theme e = dao.create(theme);
        return e;
    }

    @RequestMapping(value = {"/style"}, method = RequestMethod.POST)
    @ResponseBody
    public Css createCss(@RequestBody Css css, HttpServletRequest req) {
        Css sheet = cssDao.create(css);

        return sheet;
    }

    @RequestMapping(value = "/style/{id}.css", method = RequestMethod.GET)
    @ResponseBody
    public String getOneStockFromPortfolio(@PathVariable("id") long id){
        Css css = cssDao.getByThemeId(id);
        return css.getCss();
    }

    @RequestMapping(value = {"/themes"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Theme> getList(HttpServletRequest req) {
        List<Theme> themes = tRepo.findAll();
        System.out.println("(From getList()) LIST SIZE = " + themes.size());
        System.out.println("(From getList()) LIST SIZE = " + themes.size());
        System.out.println("(From getList()) LIST SIZE = " + themes.size());
        System.out.println("(From getList()) LIST SIZE = " + themes.size());
        if(themes.size()<17){
            themes = repopulateThemes();
        }
        return themes;
    }

    @RequestMapping(value = {"/theme/createMultiple"}, method = RequestMethod.POST)
    @ResponseBody
    public void createMultiple(@RequestBody Iterable<Theme> themes, HttpServletRequest req) {
        dao.createMultiple(themes);
    }

    @RequestMapping(value = {"/theme/all"}, method = RequestMethod.GET)
    public List<Theme> getAll(HttpServletRequest req) {
        return dao.getAll();
    }

    @RequestMapping(value = {"/theme/update"}, method = RequestMethod.PATCH)
    @ResponseBody
    public Theme update(@RequestBody Theme theme, HttpServletRequest req){
        dao.update(theme);
        return dao.get(theme.getId());
    }

    @RequestMapping(value = {"/theme/delete/all"}, method = RequestMethod.DELETE)
    @ResponseBody
    public List<Theme> deleteAll(HttpServletRequest req){
        dao.deleteAll();
        return new ArrayList<Theme>();
    }

    @RequestMapping(value = {"/theme/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public List<Theme> delete(HttpServletRequest req, @PathVariable("id") String id){
        dao.delete(Long.parseLong(id));
        return dao.getAll();
    }

    public List<Theme> repopulateThemes(){
            tRepo.deleteAllInBatch();
            tRepo.save(new Theme("Cerulean", "A clear blue sky", "https://bootswatch.com/cerulean/bootstrap.min.css"));
            tRepo.save(new Theme("Cosmo", "An ode to the metro", "https://bootswatch.com/cosmo/bootstrap.min.css"));
            tRepo.save(new Theme("Cyborg", "Jet black and electric blue", "https://bootswatch.com/cyborg/bootstrap.min.css"));
            tRepo.save(new Theme("Darkly", "Flatly in night mode", "https://bootswatch.com/darkly/bootstrap.min.css"));
            tRepo.save(new Theme("Flatly", "Flat and modern", "https://bootswatch.com/flatly/bootstrap.min.css"));
            tRepo.save(new Theme("Journal", "Crisp like a new sheet of paper", "https://bootswatch.com/journal/bootstrap.min.css"));
            tRepo.save(new Theme("Lumen", "Light and shadow", "https://bootswatch.com/lumen/bootstrap.min.css"));
            tRepo.save(new Theme("Paper", "Material is the metaphor", "https://bootswatch.com/paper/bootstrap.min.css"));
            tRepo.save(new Theme("Readable", "Optimized for legibility", "https://bootswatch.com/readable/bootstrap.min.css"));
            tRepo.save(new Theme("Sandstone", "A touch of warmth", "https://bootswatch.com/sandstone/bootstrap.min.css"));
            tRepo.save(new Theme("Simplex", "Mini and minimalist", "https://bootswatch.com/simplex/bootstrap.min.css"));
            tRepo.save(new Theme("Slate", "Shades of gunmetal gray", "https://bootswatch.com/slate/bootstrap.min.css"));
            tRepo.save(new Theme("Solar", "A spin on Solarized", "https://bootswatch.com/solar/bootstrap.min.css"));
            tRepo.save(new Theme("Spacelab", "Silvery and sleek", "https://bootswatch.com/spacelab/bootstrap.min.css"));
            tRepo.save(new Theme("Superhero", "The brave and the blue", "https://bootswatch.com/superhero/bootstrap.min.css"));
            tRepo.save(new Theme("United", "Ubuntu orange and a unique font", "https://bootswatch.com/united/bootstrap.min.css"));
            tRepo.save(new Theme("Yeti", "A friendly foundation", "https://bootswatch.com/yeti/bootstrap.min.css"));
            List<Theme> themes = tRepo.findAll();
        System.out.println("(From repopulateThemes()) LIST SIZE = " + themes.size());
        System.out.println("(From repopulateThemes()) LIST SIZE = " + themes.size());
        System.out.println("(From repopulateThemes()) LIST SIZE = " + themes.size());
        return themes;
    }

}
