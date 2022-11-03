package model.DAO.impl;

import db.DB;
import db.DbException;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    //Conexão com o BD - dependencia com a conexão
    private Connection conn;//esse objeto conn estará disponivel em toda a classe SellerDaoJdbc

    public SellerDaoJDBC(Connection conn){
        this.conn=conn;

    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteByid(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        //Um PreparedStatement é uma instrução SQL pré-compilada
        PreparedStatement st = null;

        //um objeto ResultSet é uma tabela de dados que representa um conjunto de resultados de banco de dados, que
        // geralmente é gerado pela execução de uma instrução que consulta o banco de dados .
        //resultSet é um objeto com linhas e colunas como mostra a imagem, só que estamos programando OO
        // a nossa classe DAO é responsavel por pegar os dados do BD relacional e transformar oem objetos associados
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                   "SELECT seller.*,department.Name as DepName "
                   +"FROM seller INNER JOIN department "
                   +"ON seller.DepartmentId = department.Id "
                   +"WHERE seller.Id = ?"
            );
            //configurar a interogação
            st.setInt(1,id);
            rs=st.executeQuery(); //quando eu acabo de executar uma consulta sql e vem o resultado do ResultSet ele está
                                 // apontando para a posição 0(zero) a posição zero não contem objeto é só na posição 1 que contém então eu faço o if para testar se veio algum resultado

            if(rs.next()){ //se a minha consulta não veio nenhum registro essa minha consulta vai dar falso , ele pula o if e eu retorno nulo

                Department dep = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs,dep);

                return obj;

            }
            return null;//retorno que o Seller(vendedor) é nulo, agora se rs deu verdadeiro eu navego os dados para instanciar o objeto

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }



    }

    private Seller instantiateSeller(ResultSet rs, Department dep)throws SQLException {
      Seller obj=  new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);//por que foi o objeto inteiro devido a asociação

        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
       Department dep =  new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));

        return dep;
    }



    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;

        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    +"WHERE DepartmentId = ? "
                    +"ORDER BY Name"

            );
            //configurar a interogação
            st.setInt(1,department.getId());

            rs=st.executeQuery(); //executo a query

            List<Seller> list = new ArrayList<>();

            //criado um map vazio e eu vou guardar qualquer departamento que eu instanciar , toda vez que ele passar  no while eu testo se o departamento já existe
            Map<Integer, Department> map = new HashMap<>();


            //ao invés de if será while para ele pescorrer o result set, por que diferente do outro aqui podemos ter 0 ou diversos valorses
            while(rs.next()){ //se a minha consulta não veio nenhum registro essa minha consulta vai dar falso , ele pula o if e eu retorno nulo

                //resumindo se o departamento já existir o meu map.get vai pegar ele, o if vai dar falso e eu vou reaproveitar o departamento que já existia
                //agora se o departamento não existir o map.get vai dar null para a vaiavel department1 o if vai dar verdadeiro e ele vai instanciar e salvar o departamento no map

                Department department1 = map.get(rs.getInt("DepartmentId"));//tento  buscar um map que possui esse id se for nulo eu instancio o departamento

                if(department1==null){

                    department1=instantiateDepartment(rs);

                    //salvar o departamento no map para que dá proxima vez eu possa verificar aqui e ver que ele já existe
                    map.put(rs.getInt("DepartmentId"),department1); //salvo o que estiver na variavel department1
                }

                Seller obj = instantiateSeller(rs,department1);

                list.add(obj);

            }
            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }




    }
}
