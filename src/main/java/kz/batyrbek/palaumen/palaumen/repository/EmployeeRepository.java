package kz.batyrbek.palaumen.palaumen.repository;

import kz.batyrbek.palaumen.palaumen.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e JOIN e.users u WHERE LOWER(u.email) LIKE %:search% OR LOWER(u.fullname) LIKE %:search% OR LOWER(e.position) LIKE %:search%")
    List<Employee> findByEmailOrFullnameOrPosition(@Param("search") String search);
//    @Query("SELECT e FROM Employee e JOIN e.users u WHERE LOWER(u.email) LIKE %:search% OR LOWER(u.fullname) LIKE %:search% OR LOWER(e.position) LIKE %:search%")
//    List<Employee> findByEmailOrFullnameOrPosition(@Param("search") String search) {
//        String searchLowerCase = "%" + search.toLowerCase() + "%";
//        return entityManager.createQuery(query)
//                .setParameter("search", searchLowerCase)
//                .getResultList();
//    }

}
