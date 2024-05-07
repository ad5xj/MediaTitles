package org.ad5xj.Repository;

import org.ad5xj.Model.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> 
{
    List<User> listByLonin(String i_login);
    List<User> listByPrivLvl(Integer i_lvl);

    User findByLogin(String i_login);
}