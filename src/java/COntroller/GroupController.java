/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.GroupDao;
import Entity.Group;
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
public class GroupController implements Serializable{
    private Group group;
    private LinkedList<Group> groupList;
    private GroupDao groupDao;
    private int page = 1;
    private int pageSize = 3;

    public int pageCount() {
        return (int) Math.ceil(this.getGroupDao().getGroups().size() / (double) pageSize);
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
        this.getGroupDao().create(group);
        this.clearForm();
    }

    public void updateForm(Group group) {
        this.group = group;
    }

    public String deleteConfirm(Group group) {
        this.group=group;
        return "Confirm_Delete";
    }
     public String delete() {
        this.getGroupDao().delete(this.group);
        this.group = new Group();
        return "Group";
    }

    public void update() {
        this.getGroupDao().update(group);
        this.clearForm();
    }
    

    public void clearForm() {
        this.group = new Group();
    }

    public Group getGroup() {
        if(this.group==null)
            this.group=new Group();
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LinkedList<Group> getGroupList() {
        this.groupList=this.getGroupDao().getGroups();
        return groupList;
    }

    public void setGroupList(LinkedList<Group> groupList) {
        this.groupList = groupList;
    }

    public GroupDao getGroupDao() {
        if(this.groupDao==null)
            this.groupDao= new GroupDao();
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
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
