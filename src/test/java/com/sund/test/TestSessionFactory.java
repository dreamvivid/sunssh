package com.sund.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.junit.Test;

import com.sund.test.common.HibernateUtil;
import com.sund.test.domain.TestHbm;

public class TestSessionFactory {
	public static Session getSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		return sessionFactory.openSession();
	}
	
	@Test
	public void testHbm() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TestHbm testHbm = new TestHbm();
		testHbm.setName("张三x");
		testHbm.setDate(new Date());
		session.save(testHbm);
		session.flush();
		tx.commit();
		session.close();
	}
	
	@Test
	public void testQryList() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "from TestHbm where name like :name";
		Query query = session.createQuery(sql).setString("name", "%张三%");
		for(TestHbm hbm : (List<TestHbm>)query.list()) {
			System.out.println(hbm.getName()+" : "+hbm.getDate());
		}
		session.close();
	}
	
	/**
	 * get和find都直接查询数据库
	 * load先查询缓存，再查询数据库，查无结果抛ObjectNotExistsException异常
	 */
	@Test
	public void testQryById() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.println("getHbm.........");
		TestHbm hbm = (TestHbm) session.get(TestHbm.class, new Integer(4));
		TestHbm hbm2 = (TestHbm) session.get(TestHbm.class, new Integer(4));
		System.out.println(hbm);
		
		System.out.println("delete from cache.......");
		session.evict(hbm);
		
		System.out.println("loadHbm.........");
		TestHbm loadHbm = (TestHbm) session.load(TestHbm.class, new Integer(4));
		System.out.println(loadHbm);
		session.close();
	}
	
	@Test
	public void testDelete() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TestHbm hbm = (TestHbm) session.get(TestHbm.class, new Integer(4));
		session.delete(hbm);
		tx.commit();
		session.close();
	}
	
	/**
	 * 如果session中存在相同持久化标识的对象，则用用户给出的对象状态覆盖；
	 * 如果不存在，则查询数据库
	 */
	@Test
	public void testMerge() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		TestHbm hbm = (TestHbm) session.get(TestHbm.class, new Integer(5));
		TestHbm hbm2 = (TestHbm) session.get(TestHbm.class, new Integer(5));
		TestHbm hbm3 = (TestHbm) session.get(TestHbm.class, new Integer(5));
		hbm2.setName("TEST MERGE");
		//session.evict(hbm);
		session.merge(hbm3);
		System.out.println("hbm.name: "+hbm.getName());
		System.out.println("hbm2.name: "+hbm2.getName());
		session.saveOrUpdate(hbm);
		session.flush();
		session.close();
	}
	
	@Test
	public void testPersist() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TestHbm hbm = new TestHbm();
		hbm.setName("李四");
		hbm.setDate(new Date());
		session.persist(hbm);
		session.flush();
		tx.commit();
		session.close();
	}
	
	@Test
	public void testDetachedObject() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		TestHbm hbm = (TestHbm) session.get(TestHbm.class, new Integer(5));
		session.close();
		hbm.setName("张三xx");
		Session session2 = sessionFactory.openSession();
		Transaction tx = session2.beginTransaction();
		session2.saveOrUpdate(hbm);
		tx.commit();
		session2.close();
	}
	
	@Test
	public void testLockMode() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		TestHbm hbm = (TestHbm) session.get(TestHbm.class, new Integer(5), LockMode.PESSIMISTIC_WRITE);
		System.out.println("hbm: "+hbm);
		session.close();
	}
	

	@Test
	public void testQuery() {
		Session session = getSession();
		TestHbm hbm = new TestHbm();
		hbm.setId(5);
		List<TestHbm> list = session.createQuery("from TestHbm hbm where hbm = ?").setEntity(0, hbm).list();
		for(int i=0; i<((list==null)?0:list.size()); i++) {
			TestHbm testHbm = list.get(i);
			System.out.println(testHbm.getId()+":"+testHbm.getName()+":"+testHbm.getDate());
		}
		session.close();
	}
	
	@Test
	public void testQueryWithSpecificResult() {
		Session session = getSession();
		session.close();
	}
	
	/**
	 *	iterete先查询出所有对象的持久化标识
	 *  再附加n条sql查询查询对象(先查询缓存，再查询数据库)
	 */
	@Test
	public void testQueryIterate() {
		Session session = getSession();
		session.get(TestHbm.class, 5);
		System.out.println("had load object whose id is 5 ...");
		Iterator it = session.createQuery(" from TestHbm as hbm where hbm.name like ?").setString(0, "%张三xx%").iterate();
		while(it.hasNext()) {
			TestHbm testHbm = (TestHbm) it.next();
			System.out.println(testHbm.getId()+":"+testHbm.getName()+":"+testHbm.getDate());
			
		}
		session.close();
	}
	
	@Test
	public void testQueryCount() {
		Session session = getSession();
		Long count = (Long) session.createQuery(" select count(hbm.id) from TestHbm hbm ").uniqueResult();
		System.out.println("The count is : "+count);
		session.close();
	}
	
	@Test
	public void testQueryConditionByArray() {
		Session session = getSession();
		List<String> params = new ArrayList<String>();
		params.add("张三x");
		params.add("张三xx");
		List<TestHbm> list = session.createQuery(" from TestHbm as hbm where hbm.name in(:name) order by hbm.id ").setParameterList("name", params).list();
		for(int i=0; i<(list==null?0:list.size()); i++) {
			System.out.println(list.get(i).toString());
		}
		
		session.close();
	}
	
	/**
	 * 分页 
	 * setFirstResult:设置读取的第一行的行标
	 * setMaxResults:设置读取的最多行数
	 */
	@Test
	public void testPagination() {
		Session session = getSession();
		List<TestHbm> list = session.createQuery(" from TestHbm as hbm order by hbm.id ").setFirstResult(4).setMaxResults(2).list();
		for(int i=0; i<(list==null?0:list.size()); i++) {
			System.out.println(list.get(i).toString());
		}
		session.close();
	}
	
	/**
	 * 外置命名查询
	 */
	@Test
	public void testNamedQuery() {
		Session session = getSession();
		//放入hbm.xml中
//		List<TestHbm> list = session.getNamedQuery("test.testHbm.xml").setString("name", "张三x").list();
		//注解
		List<TestHbm> list = session.getNamedQuery("test.testHbm").setString("name", "张三x").list();
		for(int i=0; i<(list==null?0:list.size()); i++) {
			System.out.println(list.get(i).toString());
		}
		session.close();
	}
	
	@Test
	public void testQueryFilter() {
		Session session = getSession();
		List<TestHbm> list = session.createQuery("from TestHbm").list();
		List<TestHbm> cacheList = new ArrayList<TestHbm>(list.size());
		for(TestHbm hbm : list) {
			cacheList.add((TestHbm) session.merge(hbm));
		}
		List hbms = session.createFilter(cacheList, "where this.name = :name").setString("name", "张三x").list();
		for(int i=0; i<(hbms==null?0:hbms.size()); i++) {
			System.out.println(hbms.get(i).toString());
		}
		session.close();
	}
	
	@Test
	public void testSqlQuery() {
		Session session = getSession();
		List hbms = session.createSQLQuery("select * from TEST_HBM hbm").addEntity(TestHbm.class).list();
		for(int i=0; i<(hbms==null?0:hbms.size()); i++) {
			System.out.println(hbms.get(i).toString());
		}
		session.close();
	}
	
	@Test
	public void testMetadata() {
		Session session = getSession();
		TestHbm hbm = (TestHbm) session.load(TestHbm.class, 5);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClassMetadata meta = sessionFactory.getClassMetadata(TestHbm.class);
		String[] propertyNames = meta.getPropertyNames();
		Type[] propertyTypes = meta.getPropertyTypes();
		Object[] propertyValues = meta.getPropertyValues(hbm, EntityMode.POJO);
		Map data = new HashMap();
		for(int i=0; i<(propertyNames==null?0:propertyNames.length); i++) {
			data.put(propertyNames[i], propertyValues[i]);
		}
		session.close();
		System.out.println(data);
	}
	
	@Test
	public void testGetAndLoad() {
		Session session = getSession();
		TestHbm hbm1 = (TestHbm) session.get(TestHbm.class, 5);
		System.out.println(hbm1);
		TestHbm hbm2 = (TestHbm) session.get(TestHbm.class, 5);
		System.out.println(hbm2);
		session.close();
	}
}

