package com.restsiqs.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.restsiqs.Entities.Technologicalexam;

/**
 * A data access object (DAO) providing persistence and search support for
 * Technologicalexam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.restsiqs.Entities.Technologicalexam
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TechnologicalexamDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TechnologicalexamDAO.class);
	// property constants
	public static final String TNAME = "tname";
	public static final String TDATE = "tdate";
	public static final String TSORCE = "tsorce";
	public static final String STUDENT_ID = "studentId";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Technologicalexam transientInstance) {
		log.debug("saving Technologicalexam instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Technologicalexam persistentInstance) {
		log.debug("deleting Technologicalexam instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Technologicalexam findById(java.lang.String id) {
		log.debug("getting Technologicalexam instance with id: " + id);
		try {
			Technologicalexam instance = (Technologicalexam) getCurrentSession()
					.get("com.restsiqs.Entities.Technologicalexam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Technologicalexam instance) {
		log.debug("finding Technologicalexam instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.restsiqs.Entities.Technologicalexam")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Technologicalexam instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Technologicalexam as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTname(Object tname) {
		return findByProperty(TNAME, tname);
	}

	public List findByTdate(Object tdate) {
		return findByProperty(TDATE, tdate);
	}

	public List findByTsorce(Object tsorce) {
		return findByProperty(TSORCE, tsorce);
	}

	public List findByStudentId(Object studentId) {
		return findByProperty(STUDENT_ID, studentId);
	}

	public List findAll() {
		log.debug("finding all Technologicalexam instances");
		try {
			String queryString = "from Technologicalexam";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Technologicalexam merge(Technologicalexam detachedInstance) {
		log.debug("merging Technologicalexam instance");
		try {
			Technologicalexam result = (Technologicalexam) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Technologicalexam instance) {
		log.debug("attaching dirty Technologicalexam instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Technologicalexam instance) {
		log.debug("attaching clean Technologicalexam instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TechnologicalexamDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TechnologicalexamDAO) ctx.getBean("TechnologicalexamDAO");
	}
}