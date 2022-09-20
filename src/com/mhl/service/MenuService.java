package com.mhl.service;

import com.mhl.dao.MenuDAO;
import com.mhl.daomain.Menu;

import java.util.List;

/**
 * @author wang zifan
 * @version 1.0
 * @date 2022/9/17  17:58
 */
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
