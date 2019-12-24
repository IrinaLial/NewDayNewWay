package com.trip.newway.repository;

import com.trip.newway.dto.user.UserDTO;
import com.trip.newway.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Igor Hnes on 24.12.2019.
 */
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail (String email);

   @Query(value = "select new com.trip.newway.dto.user.UserDTO(u.id, u.name, u.email) from User u")
   Page<UserDTO> findUsers(Pageable pageable);
}
