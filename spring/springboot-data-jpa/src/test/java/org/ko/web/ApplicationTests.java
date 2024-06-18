package org.ko.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.domain.User;
import org.ko.web.repository.*;
import org.ko.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

    @Autowired private UserCrudRepository crudRepository;
    @Autowired private UserJpaRepository jpaRepository;
//    @Autowired private UserJpaSpecificationExecutor jpaSpecificationExecutor;
    @Autowired private UserPagingSortRepository pagingSortRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;


    @Test
    public void UserRepositoryTest1 () {
        User User = userRepository.findByName("张三");
        out.println(User.getId());
    }

    @Test
    public void testInsert () {
        User user = new User();
        user.setName("张三");
        user.setAge(17);
        user = crudRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void UserRepositoryTest2 () {
        List<User> Users = userRepository.findByNameStartingWithAndAgeLessThan("张三%", 18);
        Users.forEach(out::println);
    }

    @Test
    public void UserRepositoryTest3 () {
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("张三1");
        names.add("张三2");
        names.add("张三3");
        List<User> Users = userRepository.findByNameInOrAgeLessThan(names, 18);
        Users.forEach(out::println);

    }

    @Test
    public void UserRepositoryTest4 () {
        User User = userRepository.getUserByMaxId();
        out.println(User.getId());
    }

    @Test
    public void UserRepositoryTest5 () {
        List<User> Users = userRepository.queryParams("张三", 12);
        Users.forEach(out::println);
    }

    @Test
    public void UserRepositoryTest6 () {
        List<User> Users = userRepository.queryParamsName("张三", 12);
        Users.forEach(out::println);
    }

    @Test
    public void UserRepositoryTest7 () {
        List<User> Users = userRepository.queryLikeName("张三");
        Users.forEach(out::println);
    }

    @Test
    public void UserRepositoryTest8 () {
        long ret = userRepository.getCount();
        out.println(ret);
    }

    @Test
    public void UserRepositoryTest9 () {
        //本方法报错 需要配置事务 一般配置在service层
        int ret = userRepository.updateById(1, "小王");
        out.println(ret);
    }

    @Test
    public void updateTest1 () {
        userService.update(1, "小王");
    }

    @Test
    public void saveTest () {
        ArrayList<User> Users = new ArrayList<User>();
        User User = null;
        for (int i = 0; i < 100; i++) {
            User = new User();
            User.setAge(i);
            User.setName("小小" + i);
            Users.add(User);
        }
        userService.save(Users);
    }

    @Test
    public void pageTest () {

        /**
         * @see PageRequest
         * page 从0开始
         * size 大小
         */
        Pageable pageable = new PageRequest(0, 5);
        Page<User> Users = pagingSortRepository.findAll(pageable);
        out.println("总页数" + Users.getTotalPages());
        out.println("总记录数" + Users.getTotalElements());
        out.println("当前第几页" + Users.getNumber() + 1);
        out.println("当前页面的集合" + Users.getContent());
        out.println("当前页面的记录数" + Users.getNumberOfElements());

    }

    @Test
    public void sortTest () {

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);

        /**
         * @see PageRequest
         * page 从0开始
         * size 大小
         */
        Pageable pageable = new PageRequest(0, 5, sort);
        Page<User> Users = pagingSortRepository.findAll(pageable);
        out.println("总页数" + Users.getTotalPages());
        out.println("总记录数" + Users.getTotalElements());
        out.println("当前第几页" + Users.getNumber() + 1);
        out.println("当前页面的集合" + Users.getContent());
        out.println("当前页面的记录数" + Users.getNumberOfElements());
    }

    @Test
    public void findOneTest () {

        User User = jpaRepository.findOne(99);

        out.println(User);
    }

    @Test
    public void findOneExampleTest () {
        User User = jpaRepository.findOne(99);

        out.println(User);
    }

    /**
     * 1) 分页
     * 2) 排序
     * 3) 条件： age > 50
     */
    @Test
    public void specificationTest () {

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = new Sort(order);

        Specification<User> specification = new Specification<User>() {

            /**
             *
             * @param root  查询的类型-User
             * @param criteriaQuery 添加查询条件
             * @param criteriaBuilder 构建Predicate
             * @return
             */
            @Override
            public Predicate toPredicate(
                    Root<User> root,                    //查询的实体类-根对象
                    CriteriaQuery<?> criteriaQuery,         //查询的语句
                    CriteriaBuilder criteriaBuilder         //
            ) {
                //root (User (age))--->从root到age就是这个path
                Path path = root.get("age");
                //就是条件
                return criteriaBuilder.gt(path, 50);
            }
        };

        Pageable pageable = new PageRequest(0, 5, sort);
        Page<User> Users = jpaRepository.findAll(specification, pageable);
        out.println("总页数" + Users.getTotalPages());
        out.println("总记录数" + Users.getTotalElements());
        out.println("当前第几页" + Users.getNumber() + 1);
        out.println("当前页面的集合" + Users.getContent());
        out.println("当前页面的记录数" + Users.getNumberOfElements());

    }

}
