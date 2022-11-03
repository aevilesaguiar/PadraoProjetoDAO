package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class MainFindById {
    public static void main(String[] args) {

        //Instanciando DAO - o meu programa não conehce a implementação, só a interface - é uma forma de fazer Injeção de dependencia sem explicitar a implementação
        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        Seller seller = sellerDAO.findById(4);

        System.out.println(seller);


    }
}