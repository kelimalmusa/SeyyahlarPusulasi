/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.DocumentDao;
import Entity.Document;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class DocumentController implements Serializable {

    private DocumentDao documentDao;
    private LinkedList<Document> documentList;
    private Document document;
    private Part doc;
    private final String UploadTo = "C:\\Users\\kaleem\\Desktop\\internetFile\\";

    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getDocumentDao().findAll().size() / (double) pageSize);
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

    public void upload() {
        try {
            InputStream input = doc.getInputStream();
            File f = new File(UploadTo + doc.getSubmittedFileName());
            Files.copy(input, f.toPath());

            document = this.getDocument();
            document.setFilePath(f.getParent());
            document.setFileName(f.getName());
            document.setFileType(doc.getContentType());
            this.getDocumentDao().insert(document);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String deleteConfirm(Document document) {
        this.document = document;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getDocumentDao().delete(this.document);
        this.document = new Document();
        return "Document";
    }

    public DocumentDao getDocumentDao() {
        if (this.documentDao == null) {
            this.documentDao = new DocumentDao();
        }
        return documentDao;
    }

    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    public LinkedList<Document> getDocumentLists() {
        this.documentList = this.getDocumentDao().findAll();
        return documentList;
    }

    public LinkedList<Document> getDocumentList() {
        this.documentList = this.getDocumentDao().findAll(page, pageSize);
        return documentList;
    }

    public void setDocumentList(LinkedList<Document> documentList) {
        this.documentList = documentList;
    }

    public Document getDocument() {
        if (this.document == null) {
            this.document = new Document();
        }
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getUploadTo() {
        return UploadTo;
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

}

