package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class MainTestes {
    public static void main(String[] args) {

        //Instanciando DAO - o meu programa não conehce a implementação, só a interface - é uma forma de fazer Injeção de dependencia sem explicitar a implementação
        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById === ");

        Seller seller = sellerDAO.findById(4);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment === ");

        Department department = new Department(2, null);

        List<Seller> list = sellerDAO.findByDepartment(department);

        for (Seller obj:list
             ) {
            System.out.println(obj);
        }


    }
}