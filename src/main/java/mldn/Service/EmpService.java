package mldn.Service;

import mldn.vo.Emp;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定义emp表的业务层的执行标准，此类一定要负责数据库的打开也关闭操作
 * 此类可以通过DAOFactory类取得IEmpDAO接口对象
 *
* */
public interface EmpService {
    /**
     * 实现雇员数据的增加操作，本次操作调用IEmpDAO接口的如下方法：<br>
     * <li>需要调用IEmpDAO.findById()方法，判断要增加数据的id是否已经存在；
     * <li>如果现在要增加的数据编号不存在则调用IEmpDAO.doCreate()方法，返回操作的结果
     * @param vo 包含了要增加数据的vo对象
     * @return 如果增加数据的ID重复或者保存失败返回false，否则返回true
     * @throws Exception SQL执行异常
     * */
    public int insert(Emp vo);

    /**
     * 实现雇员数据的修改操作，本次要调用IEmpDAO.doUpdate()方法：本次修改属于全部内容的修改；
     * @param vo 包含了要修改数据的vo对象
     * @return 修改成功返回true，否则返回false
     * @throws Exception SQL执行异常
     * */
    public int update(Emp vo) ;


    /**
     *执行雇员数据的删除操作，可以删除多个雇员信息，调用IEmpDAO.doRemoveBatch()方法
     * @param ids 包含了全部要删除数据的集合，没有重复数据
     * @return 删除成功返回true,否则返回false
     * @throws Exception SQL执行异常
     * */
    public int delete(Set<Integer> ids) ;

    /**
     * 根据雇员编号查找雇员的完整信息，调用IEmpDAO.findById()方法
     * @param ids 要查找的雇员编号
     * @return 如果找到了则雇员信息以vo对象返回，否则返回null
     * @throws Exception SQL执行异常
     * */
    public Emp get(Integer ids) ;

    /**
     * 查询全部雇员信息，调用IEmpDAO.findAll()方法
     * @return 查询结果以List集合的形式返回，如果没有数据则集合的长度为0
     * @throws Exception SQL执行异常
     * */
    public List<Emp> list();

    /**
     *实现数据的模糊查询与数据统计，要调用IEmpDAO接口的两个方法：<br>
     * <li>调用IEmpDAO.findAllSplit()方法,查询出所有的表数据，返回的List<Emp>;
     * <li>调用IEmpDAO.getAllCount方法，查询所有的数据量，返回的Integer；
     * @param currentPage 当前所有页
     * @param lineSize 每页显示的记录数
     * @param column 模糊查询的数据列
     * @param keyWord 模糊查询关键字
     * @return 本方法由于需要返回多种数据类型，所以使用Map集合返回，由于类型不统一，所以所有value的类型设置为object，返回内容如下：<br>
     * <li>key=allEmps，value=IEmpDAO.findAllSplit()返回结果，List<Emp>;
     * <li>key=empCount,value=IEmpDAO.getAllCount()返回结果，Integer;
     * @throws Exception SQL执行异常
     * */
    public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) ;


}
