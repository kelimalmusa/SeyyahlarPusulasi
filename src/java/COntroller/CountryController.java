/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.ContinentDao;
import DAO.CountryDao;
import DAO.DocumentDao;
import Entity.City;
import Entity.Continent;
import Entity.Country;
import Entity.Document;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.rpc.encoding.Serializer;

/**
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class CountryController implements Serializable {

    private Country country;
    private LinkedList<Country> countryList;
    private CountryDao countrydao;
    private LinkedList<Document> docList;
    private DocumentDao docDao;
    private LinkedList<Continent> continentList;
    private ContinentDao continentdao;

    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getCountrydao().getCountries().size() / (double) pageSize);
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

    public void create() {
        this.getCountrydao().create(this.country);
        this.country = new Country();
    }

    public String deleteConfirm(Country country) {
        this.country = country;
        return "Confirm_Delete";
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

    public String delete() {
        this.getCountrydao().delete(this.country);
        this.country = new Country();
        return "Country";
    }

    public void updateCity() {

        this.getCountrydao().update(this.country);
        this.country = new Country();
    }

    public void update(Country country) {
        this.country = country;

    }

    public void clearForm() {
        this.country = new Country();
    }

    public Country getCountry() {
        if (this.country == null) {
            this.country = new Country();
        }
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LinkedList<Country> getCountryList() {
        this.countryList = this.getCountrydao().getCountries(page, pageSize);
        return countryList;
    }

    public void setCountryList(LinkedList<Country> countryList) {
        this.countryList = countryList;
    }

    public CountryDao getCountrydao() {
        if (this.countrydao == null) {
            this.countrydao = new CountryDao();
        }
        return countrydao;
    }

    public void setCountrydao(CountryDao countrydao) {
        this.countrydao = countrydao;
    }

    public ContinentDao getContinentdao() {
        if (this.continentdao == null) {
            this.continentdao = new ContinentDao();
        }
        return continentdao;
    }

    public LinkedList<Continent> getContinentList() {
        this.continentList = this.getContinentdao().findAll();
        return continentList;
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
}
