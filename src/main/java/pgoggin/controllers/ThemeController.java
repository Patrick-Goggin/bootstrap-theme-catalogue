package pgoggin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pgoggin.models.Css;
import pgoggin.models.CssDao;
import pgoggin.models.Theme;
import pgoggin.models.ThemeDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        List<Theme> themes = dao.getAll();
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

}
