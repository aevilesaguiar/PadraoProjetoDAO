package model.DAO;

import model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {
    //operações staticas para instanciar os DAOS

    public static SellerDAO createSellerDao(){
        return new SellerDaoJDBC();
    }
}
