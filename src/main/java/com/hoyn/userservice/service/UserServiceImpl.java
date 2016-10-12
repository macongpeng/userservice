package com.hoyn.userservice.service;

import com.hoyn.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dennyma on 10/10/2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    /*static{
        createTables();
    }*/

    public List<User> findAllUsers() {
        //return users;

        log.info("findAllUsers@UserServiceImpl");
        List<User> users = jdbcTemplate.query(
                "SELECT id, name, age, salary FROM USER",
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("salary"))
        );

        log.info("findAllUsers@UserServiceImpl returns " + users);
        return users;
    }

    public User findById(long id) {
        List<User> users = jdbcTemplate.query(
                "SELECT id, name, age, salary FROM USER WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("salary"))
        );

        return (users.size()==0? null:users.get(0));
    }

    public List<User> findByName(String name) {
        List<User> users = jdbcTemplate.query(
                "SELECT id, name, age, salary FROM USER WHERE name = ?", new Object[] { name },
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("salary"))
        );

        return users;
    }

    public void saveUser(User user) {
        /*user.setId(counter.incrementAndGet());
        users.add(user);*/

        String insertSql = "INSERT INTO USER " +
                "(NAME, AGE, SALARY) VALUES (?, ?, ?)";

        jdbcTemplate.update(insertSql, user.getName(), user.getAge(), user.getSalary());
    }

    public void updateUser(User user) {
        /*int index = users.indexOf(user);
        users.set(index, user);*/

        String updateSql = "update USER set name = ?, age = ? , salary = ? where id = ?";
        jdbcTemplate.update(updateSql, user.getName(), user.getAge(), user.getSalary(), user.getId());
    }

    public void deleteUserById(long id) {

        /*for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }*/

        String deleteSql = "DELETE from USER where id = ?";
        jdbcTemplate.update(deleteSql, new Object[] { new Long(id) });
    }

    public boolean isUserExist(User user) {
        List<User> users = findByName(user.getName());
        return users!=null && users.size() > 0;
    }

    /*private static void createTables(){

        log.info("Creating tables");

        if (jdbcTemplate != null) {
            jdbcTemplate.execute("DROP TABLE USER IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE USER(" +
                    "id SERIAL, name VARCHAR(255), age INTEGER, salary DOUBLE");
        }
        else
        {
            log.warn("Could not initialize jdbcTemplate");
        }
    }*/

    public void deleteAllUsers() {
        String deleteSql = "DELETE from USER";
        jdbcTemplate.update(deleteSql);
    }

}