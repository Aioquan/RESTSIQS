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

import com.restsiqs.Entities.Course;

/**
 * A data access object (DAO) providing persistence and search support for
 * Course entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.restsiqs.Entities.Course
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CourseDAO {
	private static final Logger log = LoggerFactory.getLogger(CourseDAO.class);
	// property constants
	public static final String CREDIT = "credit";
	public static final String TEACHER_1 = "teacher_1";
	public static final String COURSE_NAME = "courseName";
	public static final String COURSE_TIME = "courseTime";
	public static final String COURSE_DATE = "courseDate";
	public static final String TEST1 = "test1";
	public static final String TEST2 = "test2";
	public static final String TEST3 = "test3";
	public static final String EXERCISES1 = "exercises1";
	public static final String EXERCISES2 = "exercises2";
	public static final String EXERCISES3 = "exercises3";
	public static final String EXERCISES4 = "exercises4";
	public static final String EXERCISES5 = "exercises5";
	public static final String FINAL_TEST = "finalTest";
	public static final String DAILY_MARK = "dailyMark";
	public static final String SUM = "sum";

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

	public void save(Course transientInstance) {
		log.debug("saving Course instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Course persistentInstance) {
		log.debug("deleting Course instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Course findById(java.lang.String id) {
		log.debug("getting Course instance with id: " + id);
		try {
			Course instance = (Course) getCurrentSession().get(
					"com.restsiqs.Entities.Course", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Course instance) {
		log.debug("finding Course instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.restsiqs.Entities.Course")
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
		log.debug("finding Course instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Course as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCredit(Object credit) {
		return findByProperty(CREDIT, credit);
	}

	public List findByTeacher_1(Object teacher_1) {
		return findByProperty(TEACHER_1, teacher_1);
	}

	public List findByCourseName(Object courseName) {
		return findByProperty(COURSE_NAME, courseName);
	}

	public List findByCourseTime(Object courseTime) {
		return findByProperty(COURSE_TIME, courseTime);
	}

	public List findByCourseDate(Object courseDate) {
		return findByProperty(COURSE_DATE, courseDate);
	}

	public List findByTest1(Object test1) {
		return findByProperty(TEST1, test1);
	}

	public List findByTest2(Object test2) {
		return findByProperty(TEST2, test2);
	}

	public List findByTest3(Object test3) {
		return findByProperty(TEST3, test3);
	}

	public List findByExercises1(Object exercises1) {
		return findByProperty(EXERCISES1, exercises1);
	}

	public List findByExercises2(Object exercises2) {
		return findByProperty(EXERCISES2, exercises2);
	}

	public List findByExercises3(Object exercises3) {
		return findByProperty(EXERCISES3, exercises3);
	}

	public List findByExercises4(Object exercises4) {
		return findByProperty(EXERCISES4, exercises4);
	}

	public List findByExercises5(Object exercises5) {
		return findByProperty(EXERCISES5, exercises5);
	}

	public List findByFinalTest(Object finalTest) {
		return findByProperty(FINAL_TEST, finalTest);
	}

	public List findByDailyMark(Object dailyMark) {
		return findByProperty(DAILY_MARK, dailyMark);
	}

	public List findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List findAll() {
		log.debug("finding all Course instances");
		try {
			String queryString = "from Course";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Course merge(Course detachedInstance) {
		log.debug("merging Course instance");
		try {
			Course result = (Course) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Course instance) {
		log.debug("attaching dirty Course instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Course instance) {
		log.debug("attaching clean Course instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CourseDAO) ctx.getBean("CourseDAO");
	}
}