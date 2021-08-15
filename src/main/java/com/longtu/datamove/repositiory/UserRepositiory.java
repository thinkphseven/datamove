package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepositiory extends JpaRepository<User, Long> {

    @Query(value="select * from dm_t_user where code = ? and password = ? ",nativeQuery = true)
    List<User> getUserByCodeAndPwd(String uname, String pwd);


    @Modifying
    @Query(value="update dm_t_user  set t.password = ? where code = ? ",nativeQuery = true)
    int updatePwd(String pwd, String code);
}
