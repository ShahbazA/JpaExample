package com.shahbaz;

import com.hibernate.User;
import com.hibernate.UserDetails;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Shahbaz on 3/24/2018.
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping(path="/save")
    public @ResponseBody String saveUser(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();


        UserDetails userDetails = new UserDetails();
        userDetails.setAddress("Address1");
        userDetails.setAge(32);
//        userDetails.setUser(user);

        User user = new User();
        user.setName("Random");
        user.setEmail("random1@gmail.com");
        user.setUser_detail_id(userDetails);

        session.save(user);
        session.getTransaction().commit();

        return addNewUser("Random");
    }

    //    @Query("select u from User u where u.email = ?1")
//    @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?0", nativeQuery = true)
    public List<String> getByName(String name) {
        javax.persistence.Query query = entityManager.createQuery("select u.email from User u where u.name = ?1");
        query.setParameter(1, name);

        return query.getResultList();
    }


    @GetMapping(path="/getbyname")
    public @ResponseBody String addNewUser(String name){
        List<String> list = getByName(name);
        String email  = (String)list.get(0);
        return email;
    }
}
