package com.mhl.service;

import com.mhl.dao.EmployeeDAO;
import com.mhl.daomain.Employee;

/**
 * @author wang zifan
 * @version 1.0
 * @date 2022/9/15  18:42
 * 该类完成对employee表的各种操作（通过调用employeeDAO对象完成）
 */
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)", Employee.class, empId, pwd);
    }

}
