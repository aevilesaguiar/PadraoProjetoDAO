package model.DAO;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    //operaçao responsável por inserir no banco de dados esse Objeto Seller que eu enviar como parametro de entrada
    void insert(Seller obj);
    void update(Seller  obj);
    void deleteByid(Integer id);
    //operaçao responsável por pegar o id do parametro e consultar no BD o objeto com esse ID se existir vai retornar, senão existir retorna nulo
    Seller findById(Integer id);
    List<Seller> findAll();
}
