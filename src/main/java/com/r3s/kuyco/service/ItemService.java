package com.r3s.kuyco.service;

import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.request.ItemRq;

import java.util.List;

public interface ItemService {
    Item addItem(ItemRq itemRq);
    Item updateItem(ItemRq itemRq, Long id);
    Item getItemById(Long id);
    List<Item> getAll();
    String deleteItem(Long id);
}
