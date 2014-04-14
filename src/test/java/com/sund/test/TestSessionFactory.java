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
		testHbm.setName("����x");
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
		Query query = session.createQuery(sql).setString("name", "%����%");
		for(TestHbm hbm : (List<TestHbm>)query.list()) {
			System.out.println(hbm.getName()+" : "+hbm.getDate());
		}
		session.close();
	}
	
	/**
	 * get��find��ֱ�Ӳ�ѯ���ݿ�
	 * load�Ȳ�ѯ���棬�ٲ�ѯ���ݿ⣬���޽����ObjectNotExistsException�쳣
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
	 * ���session�д�����ͬ�־û���ʶ�Ķ��������û������Ķ���״̬���ǣ�
	 * ��������ڣ����ѯ���ݿ�
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
		hbm.setName("����");
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
		hbm.setName("����xx");
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
	 *	iterete�Ȳ�ѯ�����ж���ĳ־û���ʶ
	 *  �ٸ���n��sql��ѯ��ѯ����(�Ȳ�ѯ���棬�ٲ�ѯ���ݿ�)
	 */
	@Test
	public void testQueryIterate() {
		Session session = getSession();
		session.get(TestHbm.class, 5);
		System.out.println("had load object whose id is 5 ...");
		Iterator it = session.createQuery(" from TestHbm as hbm where hbm.name like ?").setString(0, "%����xx%").iterate();
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
		params.add("����x");
		params.add("����xx");
		List<TestHbm> list = session.createQuery(" from TestHbm as hbm where hbm.name in(:name) order by hbm.id ").setParameterList("name", params).list();
		for(int i=0; i<(list==null?0:list.size()); i++) {
			System.out.println(list.get(i).toString());
		}
		
		session.close();
	}
	
	/**
	 * ��ҳ 
	 * setFirstResult:���ö�ȡ�ĵ�һ�е��б�
	 * setMaxResults:���ö�ȡ���������
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
	 * ����������ѯ
	 */
	@Test
	public void testNamedQuery() {
		Session session = getSession();
		//����hbm.xml��
//		List<TestHbm> list = session.getNamedQuery("test.testHbm.xml").setString("name", "����x").list();
		//ע��
		List<TestHbm> list = session.getNamedQuery("test.testHbm").setString("name", "����x").list();
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
		List hbms = session.createFilter(cacheList, "where this.name = :name").setString("name", "����x").list();
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

