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

	// ��ҳ,int page ҳ��, int pageSizeÿҳ��ʾ����
	public List<Emp> page(int page, int pageSize);

	/**
	 * ��ȡ�����ܼ�¼��
	 * @return��������Ŀ
	 */
	public int count();

}
