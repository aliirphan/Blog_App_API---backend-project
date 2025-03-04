package com.codewithirphan.blog.repositories;

import com.codewithirphan.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
