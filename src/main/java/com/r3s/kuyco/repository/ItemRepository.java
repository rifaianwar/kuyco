package com.r3s.kuyco.repository;

import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.request.TrxItemRq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
    List<Item> findByIdIn(List<Long> itemId);
    @Query(value = "select * from item c inner join item_category ic on ic.item_id = :id ", nativeQuery = true)
    List<Item> checkItemRelation(Long id);
}
