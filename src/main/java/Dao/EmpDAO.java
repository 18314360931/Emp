package Dao;

import java.util.List;
import java.util.Map;

import Entity.Emp;

public interface EmpDAO {

	public List<Emp> findAll();

	public Emp findById(int id);

	public int save(Emp emp);

	public int update(Emp emp);

	public int delete(int id);

	// 分页,int page 页数, int pageSize每页显示条数
	public List<Emp> page(int page, int pageSize);

	/**
	 * 获取表中总记录数
	 * @return返回总数目
	 */
	public int count();

}
