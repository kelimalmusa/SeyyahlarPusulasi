/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.CountryDao;
import DAO.DocumentDao;
import DAO.SuggestionDao;
import Entity.Document;
import Entity.Suggestion;
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
public class SuggestionController implements Serializable {

    private Suggestion suggestion;
    private LinkedList<Suggestion> suggestionList;
    private SuggestionDao suggestionDao;

    private LinkedList<Document> docList;
    private DocumentDao docDao;
    private CountryDao countrydao;

    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getSuggestionDao().findAll().size() / (double) pageSize);
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

    public void updateForm(Suggestion suggestion) {
        this.suggestion = suggestion;

    }

    public SuggestionController() {
        this.suggestionList = new LinkedList();
        suggestionDao = new SuggestionDao();
    }

    public Suggestion getSuggestion() {
        if (this.suggestion == null) {
            this.suggestion = new Suggestion();
        }
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public LinkedList<Suggestion> getSuggestionList() {
        this.suggestionList = this.getSuggestionDao().findAll(page, pageSize);
        return suggestionList;
    }

    public void setSuggestionList(LinkedList<Suggestion> suggestionList) {
        this.suggestionList = suggestionList;
    }

    public SuggestionDao getSuggestionDao() {
        if (this.suggestionDao == null) {
            this.suggestionDao = new SuggestionDao();
        }
        return suggestionDao;
    }

    public void setSuggestiondao(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public CountryDao getCountrydao() {
        if (this.countrydao == null) {
            this.countrydao = new CountryDao();
        }
        return countrydao;
    }

    public String deleteConfirm(Suggestion suggestion) {
        this.suggestion = suggestion;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getSuggestionDao().delete(this.suggestion);
        this.suggestion = new Suggestion();
        return "Suggestions";
    }

    public void save() {
        this.getSuggestionDao().create(this.suggestion);
        this.suggestion = new Suggestion();

    }

    public void updateSuggestion() {

        this.getSuggestionDao().update(this.suggestion);
        this.suggestion = new Suggestion();

    }

    public void update(Suggestion suggestion) {
        this.suggestion = suggestion;

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
        this.suggestion = new Suggestion();
    }

    public LinkedList<Document> getDocList() {
        if (this.docList == null) {
            this.docList = this.getDocDao().findAll();
        }
        return docList;
    }

    public void setDocList(LinkedList<Document> docList) {
        this.docList = docList;
    }

    public DocumentDao getDocDao() {
        if (this.docDao == null) {
            this.docDao = new DocumentDao();
        }
        return docDao;
    }

    public void setDocDao(DocumentDao docDao) {
        this.docDao = docDao;
    }
}
