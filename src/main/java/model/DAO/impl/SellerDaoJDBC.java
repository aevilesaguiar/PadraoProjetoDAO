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
import java.util.List;

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
}
