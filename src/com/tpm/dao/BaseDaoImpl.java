package com.tpm.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class pclass;
	public BaseDaoImpl() {
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		Type[] types = ptype.getActualTypeArguments();
		Class tclass = (Class)types[0];
		this.pclass = tclass;
	}
	
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	public T get(String id) {
		return (T) this.getHibernateTemplate().get(pclass, id);
	}

	public T get(int id) {
		return (T) this.getHibernateTemplate().get(pclass, id);
	}

	public List<T> findAll() {
		return  (List<T>)this.getHibernateTemplate().find("from "+pclass.getSimpleName());
	}

	public int findCount() {
		return this.getHibernateTemplate().find("from "+pclass.getSimpleName()).size();
	}

	public List<T> findT(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(pclass);
		return (List<T>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

}
