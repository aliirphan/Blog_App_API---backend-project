package com.codewithirphan.blog.repositories;

import com.codewithirphan.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
