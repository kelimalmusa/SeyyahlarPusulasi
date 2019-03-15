/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.CityDao;
import DAO.CountryDao;
import DAO.DocumentDao;
import Entity.City;
import Entity.Country;
import Entity.Document;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/*
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class CityController implements Serializable {

    private City city;
    private LinkedList<City> cityList;
    private CityDao citydao;

    private LinkedList<Country> countryList;
    private LinkedList<Document> docList;
    private DocumentDao docDao;
    private CountryDao countrydao;

    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getCitydao().getCity().size() / (double) pageSize);
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

    public void updateForm(City city) {
        this.city = city;

    }

    public CityController() {
        this.cityList = new LinkedList();
        citydao = new CityDao();
    }

    public City getCity() {
        if (this.city == null) {
            this.city = new City();
        }
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LinkedList<City> getCityLists() {
        this.cityList = this.getCitydao().getCity();
        return cityList;
    }

    public LinkedList<City> getCityList() {
        this.cityList = this.getCitydao().getCity(page, pageSize);
        return cityList;
    }

    public void setCityList(LinkedList<City> cityList) {
        this.cityList = cityList;
    }

    public CityDao getCitydao() {
        if (this.citydao == null) {
            this.citydao = new CityDao();
        }
        return citydao;
    }

    public void setCitydao(CityDao citydao) {
        this.citydao = citydao;
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

    public LinkedList<Country> getCountryList() {
        this.countryList = this.getCountrydao().getCountries();
        return countryList;
    }

    public String deleteConfirm(City city) {
        this.city = city;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getCitydao().delete(this.city);
        this.city = new City();
        return "City";
    }

    public void save() {
        this.getCitydao().create(this.city);
        this.city = new City();

    }

    public void updateCity() {

        this.getCitydao().update(this.city);
        this.city = new City();

    }

    public void update(City city) {
        this.city = city;

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

    public void clearForm() {
        this.city = new City();
    
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
