package model.DAO;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {

    //operaçao responsável por inserir no banco de dados esse Objeto Department que eu enviar como parametro de entrada
    void insert(Department  obj);
    void update(Department  obj);
    void deleteByid(Integer id);
    //operaçao responsável por pegar o id do parametro e consultar no BD o objeto com esse ID se existir vai retornar, senão existir retorna nulo
    Department findById(Integer id);
    List<Department> findAll();

}
