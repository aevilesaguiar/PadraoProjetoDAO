package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainTestesSeller {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("\n=== TEST 3: seller findAll === ");

        list = sellerDAO.findAll();

        for (Seller obj:list
        ) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: seller Insert === ");

        Seller newSeller = new Seller(null, "Greg", "greg@mail.com", new Date(),4000.00,department);
        sellerDAO.insert(newSeller);
        System.out.println("Inserted! New Id= "+newSeller.getId());

        System.out.println("\n=== TEST 5: seller Update === ");

        seller=sellerDAO.findById(1);
        seller.setName("Martha Waine");
        sellerDAO.update(seller);
        System.out.println("Update Completed! ");

        System.out.println("\n=== TEST 6: seller Deelete === ");
        System.out.println("Enter id for delete test: ");
        int id =sc.nextInt();
        sellerDAO.deleteByid(id);
        System.out.println("Delete completed");

        sc.close();

    }
}