
package BUS;

import DAO.DAO_User;
import DTO.DTO_User;
import java.util.ArrayList;


public class BUS_User {
    public DAO.DAO_User userDAO = new DAO_User();
    private ArrayList<DTO_User> list = new ArrayList<>();
    
    public BUS_User() {
        list = userDAO.getAllData();
    }
    
    public ArrayList<DTO_User> getAllData() {
        return userDAO.getAllData();
    }
    
    public int login(String userName, String pass){
        return userDAO.Login(userName, pass);
    }
    
    public DTO_User getInfo(String userName, String pass){
        return userDAO.GetInfo(userName, pass);
    }
    
    public DTO_User getInfoByID(int id){
        return userDAO.selectById(String.valueOf(id));
    }
    
    public int insert(DTO_User cur) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(cur.getUserName())) {
                return 0;
            }
            if (list.get(i).getEmail().equals(cur.getEmail()))
                return -1;
        }
        return userDAO.insert(cur);
    }

    public int update(DTO_User cur) {
        for(int i = 0; i < list.size(); i++)
            if (list.get(i).getEmail().equals(cur.getEmail()) && list.get(i).getUserID() != cur.getUserID())
                return -1;
        return userDAO.update(cur);
    }

    public int delete(DTO_User cur) {
        return userDAO.delete(cur.getUserID());
    }
    public ArrayList<DTO_User> search(String cur){
        ArrayList<DTO_User> res = userDAO.search(cur);
        return res;
    }
    
    
}
