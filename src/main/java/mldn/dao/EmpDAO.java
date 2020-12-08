package mldn.dao;

import mldn.vo.Emp;

import java.util.List;
import java.util.Set;

/**
 * 定义emp表的数据层的操作标准
 */
public interface EmpDAO {
    /**
     * 实现数据的增加操作
     * @param vo 包含了要增加数据的vo对象
     * @return 数据保存成功返回true，否则返回false
     * @throws Exception SQL执行异常
     * */
    int doCreate(Emp vo) ;

    /**
     * 实现数据的修改操作，本次修改是根据id进行全部字段数据的修改
     * @param vo 包含了要修改数据的信息，一定要提供有ID内容
     * @return 数据修改成功返回true，否则返回false
     * @throws Exception SQL执行异常
     *
     * */
    int doUpdate(Emp vo) ;

    /**
     * 执行数据的批量删除操作，所有要删除的数据以Set集合的形式保存
     * @param ids 包含了所有要删除的数据ID，不包含有重复内容
     * @return 删除成功返回true(删除的数据个数与要删除的数据个数相同)，否则返回false
     * @throws Exception SQL执行异常
     *  */
    int doRemoveBatch(Set<Integer> ids) ;

    /**
     *根据雇员编号查询指定的雇员信息
     * @param id 要查询的雇员编号
     * @return 如果雇员信息存在，则将数据以vo类对象的形式返回，如果雇员数据不存在，则返回null
     * @throws Exception SQL执行异常
     *  */
    Emp findById(Integer id);

    /**
     * 查询指定数据表的全部记录，并且以集合的形式返回
     * @return 如果表中有数据，则所有数据会封装为vo对象而后利用list集合返回，<br>
     * 如果没有数据，那么集合的长度为0（size()==0，不是null）
     * @throws Exception SQL执行异常
     * */
    List<Emp> findAll() ;

    /**
     *分页进行数据的模糊查询，查询结果以集合的形式返回
     *@param currentPage 当前所在页
     *@param lineSize 每页显示数据行数
     *@param column 要进行模糊查询的数据列
     *@param Keyword 模糊查询的关键字
     * @return 如果表中有数据，则所有的数据会封装为vo对象而后利用List集合返回<br>
     * 如果没有数据，那么集合的长度为0（size()==0,不是null）
     * @throws Exception SQL执行异常
     * */
    List<Emp> findAllSplit(Integer currentPage,Integer lineSize,String column,String Keyword) ;

    /**
     * 进行模糊查询数据量的统计，如果表中没有记录统计的结果就是0
     * @param column 要进行模糊查询的数据列
     * @param KeyWord 模糊查询的关键字
     * @return 返回表中的数据量，如果没有数据返回0
     * @throws Exception SQL执行异常
     *
     * */
   Integer getAllCount(String column,String KeyWord) ;







}
