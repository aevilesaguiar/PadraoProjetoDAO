package model.DAO;

import db.DB;
import model.DAO.impl.DepartmentDaoJDBC;
import model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {
    //operações staticas para instanciar os DAOS

    //esse é um massete para não expor a implementação deixar apenas a interface
    public static SellerDAO createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public  static  DepartmentDAO createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
