package org.example.localcloudstorageemulatorminis3api.Repository;

import org.example.localcloudstorageemulatorminis3api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(User username);

}
