/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.ContinentDao;
import DAO.DocumentDao;
import Entity.Continent;
import Entity.Document;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class ContinentController implements Serializable {

    private Continent continent;
    private LinkedList<Continent> continentList;
    private LinkedList<Continent> continentListfull;
    private LinkedList<Document> docList;
    private DocumentDao docDao;
    private ContinentDao continentdao;

    private int page = 1;
    private int pageSize = 3;

    public ContinentController() {
        this.continentList = new LinkedList();
        continentdao = new ContinentDao();
    }

    public int pageCount() {
        return (int) Math.ceil(this.getContinentdao().findAll().size() / (double) pageSize);
    }

    public void next() {
        if (this.page == (this.pageCount())) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.pageCount();
        } else {
            this.page--;
        }

    }

    public Continent getContinent() {
        if (this.continent == null) {
            this.continent = new Continent();
        }

        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public LinkedList<Continent> getContinentList() {
        this.continentList = this.continentdao.findAll(page, pageSize);
        return continentList;
    }

    public LinkedList<Continent> getContinentListfull() {
        this.continentListfull = this.continentdao.findAll();
        return continentListfull;
    }

    public void setContinentListfull(LinkedList<Continent> continentListfull) {
        this.continentListfull = continentListfull;
    }


    public void setContinentList(LinkedList<Continent> continentList) {
        this.continentList = continentList;
    }
     public LinkedList<Document> getDocList() {
        if(this.docList==null)
            this.docList=this.getDocDao().findAll();
        return docList;
    }

    public void setDocList(LinkedList<Document> docList) {
        this.docList = docList;
    }

    public DocumentDao getDocDao() {
        if(this.docDao==null)
            this.docDao = new DocumentDao();
        return docDao;
    }

    public void setDocDao(DocumentDao docDao) {
        this.docDao = docDao;
    }
    

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ContinentDao getContinentdao() {
        if (this.continentdao == null) {
            this.continentdao = new ContinentDao();
        }
        return continentdao;
    }

    public void setContinentdao(ContinentDao continentdao) {
        this.continentdao = continentdao;
    }

    public String deleteConfirm(Continent continent) {
        this.continent = continent;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getContinentdao().delete(this.continent);
        this.continent = new Continent();
        return "Continent";
    }

    public void save() {
        this.getContinentdao().create(this.continent);
        this.continent = new Continent();

    }

    public void updateContinent() {

        this.getContinentdao().update(this.continent);
        this.continent = new Continent();

    }

    public void update(Continent continent) {
        this.continent = continent;

    }

    public void clearForm() {
        this.continent = new Continent();

    }

}
