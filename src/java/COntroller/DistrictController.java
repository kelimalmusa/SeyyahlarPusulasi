/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.CityDao;
import DAO.DistrictDao;
import Entity.City;
import Entity.District;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class DistrictController implements Serializable {

    private District district;
    private LinkedList<District> districtList;
    private DistrictDao districtdao;

    @Inject
    private DocumentController documentController;

    private LinkedList<City> cityList;
    private CityDao citydao;

    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getDistrictdao().findDistricts().size() / (double) pageSize);
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
        this.getDistrictdao().create(this.district);
        this.clearForm();

    }

    public void clearForm() {
        this.district = new District();
    }

    public String deleteConfirm(District distric) {
        this.district = distric;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getDistrictdao().delete(this.district);
        this.clearForm();
        return "District";
    }

    public void updateDistrict() {

        this.getDistrictdao().update(this.district);
        this.clearForm();
    }

    public void update(District district) {
        this.district = district;
    }

    public District getDistrict() {
        if (this.district == null) {
            this.district = new District();
        }
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public LinkedList<District> getDistrictList() {
        this.districtList = this.getDistrictdao().findDistricts(page, pageSize);
        return districtList;
    }

    public void setDistrictList(LinkedList<District> districtList) {
        this.districtList = districtList;
    }

    public DistrictDao getDistrictdao() {
        if (this.districtdao == null) {
            this.districtdao = new DistrictDao();
        }
        return districtdao;
    }

    public void setDistrictdao(DistrictDao districtdao) {
        this.districtdao = districtdao;
    }

    public LinkedList<City> getCityList() {
        this.cityList = this.getCitydao().getCity();
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

    public DocumentController getDocumentController() {
        return documentController;
    }

    public void setDocumentController(DocumentController documentController) {
        this.documentController = documentController;
    }

}
