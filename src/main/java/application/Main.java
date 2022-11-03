package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "Books");
        System.out.println(obj);

        Seller seller = new Seller(21,"Bob","bob@mail.com",new Date(),3000.00,obj );

        //Instanciando DAO - o meu programa não conehce a implementação, só a interface - é uma forma de fazer Injeção de dependencia sem explicitar a implementação
        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println(seller);


    }
}