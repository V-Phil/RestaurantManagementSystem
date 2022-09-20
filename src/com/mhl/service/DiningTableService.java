package com.mhl.service;

import com.mhl.dao.DiningTableDAO;
import com.mhl.daomain.DiningTable;

import java.util.List;

/**
 * @author wang zifan
 * @version 1.0
 * @date 2022/9/17  16:30
 */
public class DiningTableService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回餐桌信息
    public List<DiningTable> list() {
        return diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
    }

    //查询对应的餐桌编号是否存在
    public DiningTable getDiningTableById(int id) {

        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    //预定餐桌，对状态进行更新
    public boolean orderDiningTale(int id, String orderName, String orderTel) {
        int update
                = diningTableDAO.update("update diningTable set state = 'Scheduled', orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    //餐桌状态
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    //还原餐桌状态
    public boolean updateDiningTableToFree(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ?, orderName = '', orderTel = '' where id = ?", state, id);
        return update > 0;
    }
}
