package kz.batyrbek.palaumen.palaumen.repository;

import kz.batyrbek.palaumen.palaumen.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE LOWER(u.email) LIKE %:search% OR LOWER(u.fullname) LIKE %:search%")
    List<Users> findByEmailOrFullName(@Param("search") String search);

}
