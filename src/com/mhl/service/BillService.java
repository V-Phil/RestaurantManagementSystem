package com.mhl.service;

import com.mhl.dao.BillDAO;
import com.mhl.dao.DiningTableDAO;
import com.mhl.dao.MultiTableDAO;
import com.mhl.daomain.Bill;
import com.mhl.daomain.MultiTableBean;

import java.util.List;
import java.util.UUID;


/**
 * @author wang zifan
 * @version 1.0
 * @date 2022/9/18  16:37
 */
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    private MultiTableDAO multiTableDAO = new MultiTableDAO();


    //点餐方法
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billId = UUID.randomUUID().toString();
        int update = billDAO.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), 'no pay')",
                billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update <= 0) {
            return false;
        }

        return diningTableService.updateDiningTableState(diningTableId, "having lunch");

    }


    //返回账单
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    //返回账单
    public List<MultiTableBean> list2()
    {
        return multiTableDAO.queryMulti("select bill.*, name, price from bill, menu where bill.menuId = menu.id", MultiTableBean.class);
    }

    //查看是否有未完成的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill =
                billDAO.querySingle("select * from bill where diningTableId = ? and state = 'no pay' limit 0, 1", Bill.class, diningTableId);
        return bill != null;

    }


    //更新餐桌状态的方法
    public boolean payBill(int diningTableId, String payMode) {
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = 'no pay'", payMode, diningTableId);

        if (update <= 0) {
            return false;
        }
    //修改diningTable表
        if (!diningTableService.updateDiningTableToFree(diningTableId, "free")) {
            return false;
        }
        return true;
    }

}
