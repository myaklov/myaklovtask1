package com.alex.spring.dao;

import com.alex.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
@Repository
public class DaoImpl implements Dao {

   private static final Logger logger= LoggerFactory.getLogger(DaoImpl.class);
   private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.save(user);
        logger.info("запись добавлена "+user);
    }

    public void deleteUser(int id) {
        Session session=sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class,new Integer(id));
        if(user!=null){session.delete(user);}
        logger.info("запись удалена "+user);

    }

    public void updateUser(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("запись обновлена "+user);
    }

    public User getUserById(int id) {
        Session session=sessionFactory.getCurrentSession();
        User user=(User) session.load(User.class, new Integer(id));
        logger.info("информация успешно получена "+user);
        return user;
    }

    public List<User> getListUsers() {
        Session session=sessionFactory.getCurrentSession();
        List<User> list= session.createQuery("from User ").list();
        for (User user:list){
            logger.info("User list: "+user);
        }
        return list;
    }

    public List<User> findByName(String name) {
        Session session=sessionFactory.getCurrentSession();
        List<User> list= session.createQuery("FROM User where name='"+name+"'").list();
        for (User user:list){
            logger.info("User list: "+user);
        }
        return list;
    }
}
