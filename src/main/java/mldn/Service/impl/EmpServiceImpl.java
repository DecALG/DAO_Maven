package mldn.Service.impl;

import mldn.Service.EmpService;
import mldn.dbc.DatabaseConnection;
import mldn.factory.DAOFactory;
import mldn.vo.Emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class EmpServiceImpl implements EmpService {
    //在这个类的对象内部就提供有一个数据库连接类的实例化对象
    private DatabaseConnection dbc=new DatabaseConnection();
    @Override
    public int insert(Emp vo) {
        try {
            //要增加的雇员编号如果不存在，则findById()返回的结果就是null，null表示可以进行新雇员数据的保存
            if (DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(vo.getEmpno())==null){
                return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return -1;

        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public int update(Emp vo) {
        try {
            return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public int delete(Set<Integer> ids) {
        try {
            return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);

        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Emp get(Integer ids) {
        try {
            return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(ids);

        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Emp> list()  {
        try {
            return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAll();

        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord)  {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allEmps",DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column, keyWord));
            map.put("empCont",DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column,keyWord));
            return map;

        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
