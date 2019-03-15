/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COntroller;

import DAO.UserDao;
import Entity.User;
import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kaleem
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private User user;
    private User userLogin;
    private LinkedList<User> userlist;
    private UserDao userdao;

    private int page = 1;
    private int pageSize = 3;

    @Inject
    private CityController cityController;
    private GroupController groupController;

    public void create() {
        this.getUserdao().create(this.user);
        this.clearForm();
    }

    public int pageCount() {
        return (int) Math.ceil(this.getUserdao().getUsers().size() / (double) pageSize);
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

    public void update(User user) {
        this.user = user;
    }

    public void updateUser() {
        this.getUserdao().update(this.user);
        this.clearForm();

    }

    public String login() {
        if (this.user.getName().equals(this.userdao.loginControl(this.user.getName(), this.user.getPassword()).getName()) && this.user.getPassword().equals(this.userdao.loginControl(this.user.getName(), this.user.getPassword()).getPassword())) {
            userLogin = this.userdao.loginControl(this.user.getName(), this.user.getPassword());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);
            this.user = new User();
            return "/Admin/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalide Username Or Password"));
            return "/login";
        }
    }

    

    public String deleteConfirm(User user) {
        this.user = user;
        return "Confirm_Delete";
    }

    public String delete() {
        this.getUserdao().delete(this.user);
        this.clearForm();
        return "User";
    }

    public void clearForm() {
        this.user = new User();
    }

    public UserController() {
        this.userlist = new LinkedList();
        userdao = new UserDao();
    }

    public UserController(User user, LinkedList<User> userlist, UserDao userdao) {
        this.user = user;
        this.userlist = userlist;
        this.userdao = userdao;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupController getGroupController() {
        if (this.groupController == null) {
            this.groupController = new GroupController();
        }
        return groupController;
    }

    public User getUserLogin() {
        if(this.userLogin==null)
            this.userLogin=new User();
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }
    

    public void setGroupController(GroupController groupController) {
        this.groupController = groupController;
    }

    public LinkedList<User> getUserlist() {
        this.userlist = this.getUserdao().getUsers(page, pageSize);
        return userlist;
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

    public void setUserlist(LinkedList<User> userlist) {
        this.userlist = userlist;
    }

    public UserDao getUserdao() {
        if (this.userdao == null) {
            this.userdao = new UserDao();
        }
        return userdao;
    }

    public void setUserdao(UserDao userdao) {
        this.userdao = userdao;
    }

    public CityController getCityController() {
        return cityController;
    }

    public void setCityController(CityController cityController) {
        this.cityController = cityController;
    }

}
