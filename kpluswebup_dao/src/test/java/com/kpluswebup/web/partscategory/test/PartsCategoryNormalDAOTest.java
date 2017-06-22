package com.kpluswebup.web.partscategory.test;


import java.io.Reader;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kpluswebup.web.partscategory.dao.PartsCategoryNormalDAO;
import com.kpluswebup.web.vo.PartsCategoryVo;

public class PartsCategoryNormalDAOTest {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	private PartsCategoryVo partsCategoryVo;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		// create a SqlSessionFactory  
        Reader reader = Resources.getResourceAsReader("com/sds/config/mybatis/test/mybatis-config-test.xml");  
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
        reader.close(); 	
        
        // populate in-memory database  
        SqlSession session = sqlSessionFactory.openSession();  
        Connection conn = session.getConnection();  
        reader = Resources.getResourceAsReader("com/sds/config/mybatis/test/partscategory_normal-Hqldb.sql");  
        ScriptRunner runner = new ScriptRunner(conn);  
        runner.setLogWriter(null);  
        runner.runScript(reader);  
        reader.close();  
        session.close();        
    }
    @Before
    public void setUp() throws Exception
    {
    	partsCategoryVo = new PartsCategoryVo();
    	partsCategoryVo.setAncestorID("1B897961-5193-412C-8F18-137A8A9D13F4");
    	partsCategoryVo.setFlevel(2);
    }
    
    @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }	
    //根据 ancestorID来查找
    @Test
    public void findPartsCategoryNormalByAncestorID()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();  
       
        	PartsCategoryNormalDAO pcnDAO = sqlSession.getMapper(PartsCategoryNormalDAO.class);
        	List<PartsCategoryVo> partsCategoryVos = pcnDAO.findPartsCategoryNormalByAncestorID(partsCategoryVo.getAncestorID());
            for (PartsCategoryVo partsCategoryVo : partsCategoryVos) {
				//System.out.println(partsCategoryVo.getName());
			}
        	 sqlSession.close();  
        }   
    
    
    @Test
    public void findPartsCategoryNormalOneLevel()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();  
       
        	PartsCategoryNormalDAO pcnDAO = sqlSession.getMapper(PartsCategoryNormalDAO.class);
        	List<PartsCategoryVo> partsCategoryVos = pcnDAO.findPartsCategoryNormalOneLevel();
            for (PartsCategoryVo partsCategoryVo : partsCategoryVos) {
				System.out.println(partsCategoryVo.getName());
			}
        	 sqlSession.close();  
        }
    
    //根据 fLevel来查找
    @Test
    public void findPartsCategoryNormalByFlevel()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();  
       
        	PartsCategoryNormalDAO pcnDAO = sqlSession.getMapper(PartsCategoryNormalDAO.class);
        	List<PartsCategoryVo> partsCategoryVos = pcnDAO.findPartsCategoryNormalByFlevel(2);
            for (PartsCategoryVo partsCategoryVo : partsCategoryVos) {
				//System.out.println(partsCategoryVo.getName());
			}
        	 sqlSession.close();  
        }
    }
    
   
    
    

