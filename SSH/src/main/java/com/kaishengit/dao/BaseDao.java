package com.kaishengit.dao;

import com.kaishengit.exception.DaoAndUtilsException;
import com.kaishengit.pojo.Student;
import com.kaishengit.utils.voentity.RequestQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
public abstract class BaseDao<T, PK extends Serializable> {

    private static final String EQ = "eq";
    private static final String LIKE = "like";
    private static final String GT = "gt";
    private static final String GE = "ge";
    private static final String LT = "lt";
    private static final String LE = "le";

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 获得当前对象 例如 Student 为Student.class
     */
    public BaseDao() {

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();

        entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

    }

    /**
     * 查找通过ID
     * @param id
     * @return
     */
    public T selectById(Integer id) {

        return (T) getSession().get(entityClass, id);

    }

    /**
     * 查询全部
     * @return
     */
    public List<T> selectAll() {

        Criteria criteria = getSession().createCriteria(entityClass);

        return criteria.list();

    }


    /**
     * 保存或修改
     * @param t
     */
    public void insertOrUpdate(T t) {

        getSession().saveOrUpdate(t);

    }

    /**
     * 分页 查询 搜索
     * @param pageStart
     * @param requestQueryList
     * @return
     */
    public List<T> selectAllByPage(Long pageStart, List<RequestQuery> requestQueryList) {

        Criteria criteria = getSession().createCriteria(entityClass);

        for(RequestQuery requestQuery: requestQueryList) {

            criteria.add(createCriterion(requestQuery.getQueryName(),
                    requestQuery.getQueryMethod(), requestQuery.getQueryValue()));

        }

        criteria.addOrder(Order.desc("id"));

        criteria.setFirstResult(pageStart.intValue());
        criteria.setMaxResults(10);

        return criteria.list();

    }

    /**
     * 验证方法名是否存在
     * @param name 方法名
     * @return true 存在 false 不存在
     */
    private boolean validateMethodName(String name) {

        if(!StringUtils.isNotEmpty(name)) {

            throw new DaoAndUtilsException("方法参数不能为null");

        }
        //拼装getStuAge（）
        String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);

        //获得当前类的所有方法
        Method[] methods = entityClass.getMethods();

        for(Method method: methods) {

            if(methodName != null && methodName.equalsIgnoreCase(method.getName())) {
                return true;
            }

        }

        return false;

    }

    /**
     * 创建查询条件
     * @param paramName
     * @param type
     * @param value
     * @return
     */
    public Criterion createCriterion(String paramName, String type, Object value) {

        if(!validateMethodName(paramName)) {
            throw new DaoAndUtilsException("所传实体类名不存在");
        }

        if(EQ.equalsIgnoreCase(type)) {

            return Restrictions.eq(paramName, value);

        } else if(LIKE.equalsIgnoreCase(type)) {

            return Restrictions.like(paramName, value);

        } else if(GT.equalsIgnoreCase(type)) {

            return Restrictions.gt(paramName, value);

        } else if(GE.equalsIgnoreCase(type)) {

            return Restrictions.ge(paramName, value);

        } else if(LT.equalsIgnoreCase(type)) {

            return Restrictions.lt(paramName, value);

        } else if(LE.equalsIgnoreCase(type)) {

            return Restrictions.le(paramName, value);

        }

        return null;

    }


    /**
     * 计算总条数
     * @return
     */
    public Long countAll(List<RequestQuery> requestQueryList) {

        Criteria criteria = getSession().createCriteria(entityClass);

        for(RequestQuery requestQuery: requestQueryList) {

            criteria.add(createCriterion(requestQuery.getQueryName(),
                    requestQuery.getQueryMethod(), requestQuery.getQueryValue()));

        }

        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();

    }



}
