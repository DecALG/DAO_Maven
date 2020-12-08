package mldn.dao.impl;

import mldn.dao.EmpDAO;
import mldn.vo.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class EmpDAOImpl implements EmpDAO {

    private Connection conn;//需要利用Connection对象操作
    private PreparedStatement pstmt;
    /**
     * 如果要想使用数据层进行原子性的功能操作实现，必须要提供有Connection接口对象<br>
     * 另外，由于开发之中业务层要调用数据层，所以数据库的打开与关闭交由业务层处理
     * @param conn 表示数据库连接对象
     * */
    public EmpDAOImpl(Connection conn){
        this.conn=conn;
    }


    @Override
    public int doCreate(Emp vo) {
        String sql="insert into emp(empno,ename,job,hiredate,sal,comm) values(?,?,?,?,?,?)";
        try {
            this.pstmt=this.conn.prepareStatement(sql);
            this.pstmt.setInt(1,vo.getEmpno());
            this.pstmt.setString(2, vo.getEname());
            this.pstmt.setString(3, vo.getJob());
            this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
            this.pstmt.setDouble(5,vo.getSal());
            this.pstmt.setDouble(6,vo.getComm());
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int doUpdate(Emp vo) {
        String sql="update emp set ename=?,job=?,hiredate=?,sal=?,comm=? where empno=?";
        try {
            this.pstmt=this.conn.prepareStatement(sql);
            this.pstmt.setString(1, vo.getEname());
            this.pstmt.setString(2, vo.getJob());
            this.pstmt.setDate(3,new java.sql.Date(vo.getHiredate().getTime()));
            this.pstmt.setDouble(4,vo.getSal());
            this.pstmt.setDouble(5,vo.getComm());
            this.pstmt.setInt(6,vo.getEmpno());
            return this.pstmt.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int doRemoveBatch(Set<Integer> ids){
        if(ids==null||ids.size()==0){
            return -1;
        }
        StringBuffer sql=new StringBuffer();
        sql.append("DELETE FROM emp WHERE empno IN(");
        Iterator<Integer> iter= ids.iterator();
        while (iter.hasNext()){
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length()-1,sql.length()).append(")");
        try {
            this.pstmt=this.conn.prepareStatement(sql.toString());
            return this.pstmt.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Emp findById(Integer id)  {

        String sql="SELECT empno,ename,job,hiredate,sal,comm FROM emp WHERE empno=?";
        Emp vo=new Emp();
        try {
            this.pstmt=this.conn.prepareStatement(sql);
            this.pstmt.setInt(1,id);
            ResultSet rs=this.pstmt.executeQuery();
            if (rs.next()){
                vo=new Emp();
                vo.setEmpno(rs.getInt(1));
                vo.setEname(rs.getString(2));
                vo.setJob(rs.getString(3));
                vo.setHiredate(rs.getDate(4));
                vo.setSal(rs.getDouble(5));
                vo.setComm(rs.getDouble(6));
                return vo;
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emp> findAll(){
        List<Emp> all=new ArrayList<Emp>();
        String sql="SELECT empno,ename,job,hiredate,sal,comm FROM emp";
        try {
            this.pstmt=this.conn.prepareStatement(sql);
            ResultSet rs=this.pstmt.executeQuery();
            while (rs.next()){
                Emp vo=new Emp();
                vo.setEmpno(rs.getInt(1));
                vo.setEname(rs.getString(2));
                vo.setJob(rs.getString(3));
                vo.setHiredate(rs.getDate(4));
                vo.setSal(rs.getDouble(5));
                vo.setComm(rs.getDouble(6));
                all.add(vo);
            }
            return all;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emp> findAllSplit(Integer currentPage, Integer lineSize, String column, String Keyword) {
        List<Emp> all=new ArrayList<Emp>();
        String sql="SELECT * FROM " +
                "(SELECT empno,ename,job,hiredate,sal,comm,ROWNUM rn" +
                "FROM emp"
                +"WHERE"+column+"LIKE ? AND ROWNUM<=?) temp" +
                "WHERE temp.rn>?";
        try {
            this.pstmt=this.conn.prepareStatement(sql);
            this.pstmt.setString(1,"%"+Keyword+"%");
            this.pstmt.setInt(2,currentPage*lineSize);
            this.pstmt.setInt(3,(currentPage-1)*lineSize);

            ResultSet rs=this.pstmt.executeQuery();
            while (rs.next()){
                Emp vo=new Emp();
                vo.setEmpno(rs.getInt(1));
                vo.setEname(rs.getString(2));
                vo.setJob(rs.getString(3));
                vo.setHiredate(rs.getDate(4));
                vo.setSal(rs.getDouble(5));
                vo.setComm(rs.getDouble(6));
                all.add(vo);
            }
            return all;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAllCount(String column, String KeyWord) {
       String sql="SELECT COUNT(empo) FROM emp WHERE"+column+"LIKE ?";
       try {
           this.pstmt=this.conn.prepareStatement(sql);
           this.pstmt.setString(1,"%"+KeyWord+"%");
           ResultSet rs=this.pstmt.executeQuery();
           if (rs.next()){
               return rs.getInt(1);
           }
       }catch (SQLException throwables){
           throwables.printStackTrace();
       }
        return null;
    }
}
