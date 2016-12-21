package ImplDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBUTils.DBUtil;
import Entity.Emp;

import java.sql.PreparedStatement;


public class EmpDAOImpl implements Dao.EmpDAO {

	public List<Emp> findAll() {
		List<Emp> list = new ArrayList<Entity.Emp>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from tb_emp";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				createEmp(rs);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败！", e);
		} finally {
			DBUtil.close(conn);
		}
	}

	public Emp findById(int id) {
		Emp emp = new Emp();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from tb_emp where empno=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();// 必须移动游标
			emp = new Emp();
			emp.setEmpno(rs.getInt("empno"));
			emp.setComm(rs.getDouble("comm"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setMgr(rs.getInt("mgr"));
			emp.setHiredate(rs.getDate("hiredate"));
			emp.setJob(rs.getString("job"));
			emp.setEname(rs.getString("ename"));
			emp.setSal(rs.getDouble("sal"));
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return emp;
	}

	public int save(Emp emp) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into tb_emp(empno,ename,job,mgr) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getJob());
			ps.setInt(3, emp.getMgr());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("增加员工失败", e);
		} finally {
			DBUtil.close(conn);
		}
		return 0;
	}

	public int update(Emp emp) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update tb_emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,empno=?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getEname());
			ps.setString(3, emp.getJob());
			ps.setInt(4, emp.getMgr());
			ps.setDouble(5, emp.getSal());
			ps.setDouble(6, emp.getComm());
			ps.setInt(7, emp.getEmpno());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改员工信息失败", e);
		} finally {
			DBUtil.close(conn);
		}
		return 0;
	}

	public int delete(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from tb_emp where empno =？";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除员工信息失败", e);
		} finally {
			DBUtil.close(conn);
		}
	}

	public List<Emp> page(int page, int pageSize) {
		List<Emp> list = new ArrayList<Emp>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// limit start,pageSize
			// 第一页limit 0,10
			// 第一页limit 10,10
			// 第一页limit 20,10

			String sql = "select * from tb_emp limit ?,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int start = (page - 1) * pageSize;
			ps.setInt(1, start);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Emp emp = createEmp(rs);
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败！", e);
		} finally {
			DBUtil.close(conn);
		}
	}

	private Emp createEmp(ResultSet rs) throws SQLException {
		Emp emp = new Emp();
		emp.setEmpno(rs.getInt("empno"));
		emp.setComm(rs.getDouble("comm"));
		emp.setDeptno(rs.getInt("deptno"));
		emp.setMgr(rs.getInt("mgr"));
		emp.setHiredate(rs.getDate("hiredate"));
		emp.setJob(rs.getString("job"));
		emp.setEname(rs.getString("ename"));
		emp.setSal(rs.getDouble("sal"));
		return emp;
	}

	public int count() {

		List<Emp> list = new ArrayList<Emp>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select  count(*)  as nums from tb_emp";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();// 移动游标才能获取数据(游标,指针位置)
			int num = rs.getInt("nums");
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户失败！", e);
		} finally {
			DBUtil.close(conn);
		}
	}

}
