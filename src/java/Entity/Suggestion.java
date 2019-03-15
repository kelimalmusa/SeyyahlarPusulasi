/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author kaleem
 */
public class Suggestion {

    private Long id;
    private String suggestion;
    private String comment;
    private Document document;

    public Suggestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getComment() {
        return comment;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Suggestion(Long id, String suggestion, String comment) {
        this.id = id;
        this.suggestion = suggestion;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Suggestion{" + "id=" + id + ", suggestion=" + suggestion + ", comment=" + comment + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suggestion other = (Suggestion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
