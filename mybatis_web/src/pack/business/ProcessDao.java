package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private static ProcessDao dao = new ProcessDao();
	public static ProcessDao getInstance(){
		return dao;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List selectdataAll() throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List list = sqlSession.selectList("selectDataAll"); //DataMapper의 id를 읽기 위한 것임
		sqlSession.close();
		return list;
	}
	
	public DataDto selectDataPart(String arg) throws SQLException{
		SqlSession sqlSession = factory.openSession();
		DataDto dto = sqlSession.selectOne("selectDataById", arg); //DataMapper의 id를 읽기 위한 것임
		sqlSession.close();
		return dto;
	}
	
	public void insertData(DataDto dto) throws SQLException{	
		SqlSession sqlSession = factory.openSession();			//수동 commit
//		SqlSession sqlSession = factory.openSession(true);		//자동 commit
		int re = sqlSession.insert("insertData", dto);
		//System.out.println("re : " + re);
		sqlSession.commit(); //또는 sqlSession.rollback();
		sqlSession.close();
	}
	
	
	public void updateData(DataDto dto) throws SQLException{	
		SqlSession sqlSession = factory.openSession();			//수동 commit		
		int re = sqlSession.insert("updateData", dto);		
		sqlSession.commit(); //또는 sqlSession.rollback();
		sqlSession.close();
	}
	
	public boolean deleteData(int arg){
		SqlSession sqlSession = factory.openSession();
		boolean b= false;		
		try {
			int cou = sqlSession.delete("deleteData", arg);
			if(cou > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("deleteData err: " + e);
			sqlSession.rollback();
		} finally{
			if(sqlSession != null) 	sqlSession.close();
		}
		return b;
	}
	
}
