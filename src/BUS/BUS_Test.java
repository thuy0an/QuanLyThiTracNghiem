/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

import DAO.DAOInterface;
import DAO.DAO_Test;
import DTO.DTO_Test;

/**
 *
 * @author KIET
 */
public class BUS_Test implements DAOInterface<DTO_Test> {
    private ArrayList<DTO_Test> listTest;
    private DAO.DAO_Test daoTest;
    public BUS_Test() {
        this.daoTest = new DAO_Test();
        this.listTest = daoTest.getAllData();
    }

    @Override
    public int insert(DTO_Test t) {
        int result = daoTest.insert(t);
        if (result > 0) {
            if (listTest == null) {
                listTest = new ArrayList<>();
            }
            listTest.add(t);
        }
        return result;
    }

    @Override
    public int update(DTO_Test t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList<DTO_Test> getAllData() {
        // TODO Auto-generated method stub
        return daoTest.getAllData();
    }

    @Override
    public DTO_Test selectById(String t) {
        return daoTest.selectById(t);
    }

    public ArrayList<DTO_Test> searchData(String searchText) {
        return daoTest.searchData(searchText);
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
    
}
