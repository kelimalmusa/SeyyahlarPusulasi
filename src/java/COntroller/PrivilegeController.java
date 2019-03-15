/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.GroupDao;
import DAO.PrivilegeDao;
import Entity.Group;
import Entity.Privilege;
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
public class PrivilegeController implements Serializable {

    private Privilege privilege;
    private LinkedList<Privilege> privilegeList;
    private PrivilegeDao privilegeDao;
    private LinkedList<Group> groupList;
    private GroupDao groupDao;
    private int page = 1;
    private int pageSize = 3;

    public boolean createPrivi(Long group_id) {
        return this.getPrivilegeDao().createPrivi(group_id);
    }

    public boolean readPrivi(Long group_id) {
        return this.getPrivilegeDao().readPrivi(group_id);
    }

    public boolean updatePrivi(Long group_id) {
        return this.getPrivilegeDao().updatePrivi(group_id);
    }

    public boolean deletePrivi(Long group_id) {
        return this.getPrivilegeDao().deletePrivi(group_id);
    }

    public int pageCount() {
        return (int) Math.ceil(this.getPrivilegeDao().findAll().size() / (double) pageSize);
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
        this.getPrivilegeDao().create(privilege);
        this.clearForm();
    }

    public void updateForm(Privilege privilage) {
        this.privilege = privilage;
    }

    public String deleteConfirm(Privilege privilege) {
        this.privilege = privilege;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getPrivilegeDao().delete(this.privilege);
        this.privilege = new Privilege();
        return "Privilege";
    }

    public void update() {
        this.getPrivilegeDao().update(privilege);
        this.clearForm();
    }

    public void clearForm() {
        this.privilege = new Privilege();
    }

    public Privilege getPrivilege() {
        if (this.privilege == null) {
            this.privilege = new Privilege();
        }
        return privilege;
    }

    public LinkedList<Group> getGroupList() {
        this.groupList = this.getGroupDao().getGroups();
        return groupList;
    }

    public void setGroupList(LinkedList<Group> groupList) {
        this.groupList = groupList;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public LinkedList<Privilege> getPrivilegeList() {
        this.privilegeList = this.getPrivilegeDao().findAll(page, pageSize);
        return privilegeList;
    }

    public void setPrivilegeList(LinkedList<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public PrivilegeDao getPrivilegeDao() {
        if (this.privilegeDao == null) {
            this.privilegeDao = new PrivilegeDao();
        }
        return privilegeDao;
    }

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
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

    public GroupDao getGroupDao() {
        if (this.groupDao == null) {
            this.groupDao = new GroupDao();
        }
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

}
