package com.r3s.kuyco.repository;

import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.request.CategoryRq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    List<Category> findByNameIn(String[] name);

    @Query(value = "select * from category c inner join item_category ic on ic.category_id = :id ", nativeQuery = true)
    List<Category> checkCategoryRelation(long id);


}
