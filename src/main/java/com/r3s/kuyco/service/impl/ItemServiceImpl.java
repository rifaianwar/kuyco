package com.r3s.kuyco.service.impl;

import com.r3s.kuyco.exception.BadRequestException;
import com.r3s.kuyco.exception.NotFoundException;
import com.r3s.kuyco.model.entity.Category;
import com.r3s.kuyco.model.entity.Item;
import com.r3s.kuyco.model.request.ItemRq;
import com.r3s.kuyco.repository.CategoryRepository;
import com.r3s.kuyco.repository.ItemRepository;
import com.r3s.kuyco.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Item addItem(ItemRq itemRq) {
            for (String cat : itemRq.getCategories()) {
                Category findCategory = categoryRepository.findByName(cat);
                if (null == findCategory || findCategory.equals("") || findCategory.getDeletedDate() != null) {
                    log.error("categories not found: {}", cat);
                    throw new NotFoundException("category " + cat + " not found");
                }
            }
            List<Category> categories = categoryRepository.findByNameIn(itemRq.getCategories());
            Item item = new Item();
            item.setCreatedDate(new Date());
            item.setName(itemRq.getName());
            item.setDescription(itemRq.getDesc());
            item.setPrice(itemRq.getPrice());
            item.setCategories(categories);
            itemRepository.save(item);
            return item;
    }

    @Transactional
    @Override
    public Item updateItem(ItemRq itemRq, Long id) {
            for (String cat : itemRq.getCategories()) {
                Category findCategory = categoryRepository.findByName(cat);
                if (null == findCategory || findCategory.equals("")) {
                    log.error("category " + cat + " not found");
                    throw new NotFoundException("category " + cat + " not found");
                }
            }
            List<Category> categories = categoryRepository.findByNameIn(itemRq.getCategories());
            Optional<Item> item = itemRepository.findById(id);
            if (item.isEmpty()) {
                log.error("Item ID " + id + " not found");
                throw new NotFoundException("Item ID " + id + " not found");
            }
            item.get().setUpdatedDate(new Date());
            item.get().setName(itemRq.getName());
            item.get().setDescription(itemRq.getDesc());
            item.get().setPrice(itemRq.getPrice());
            item.get().setCategories(categories);
            itemRepository.save(item.get());
            return item.get();
    }

    @Override
    public Item getItemById(Long id) {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isEmpty()) {
                log.error("Item ID " + id + " not found");
                throw new NotFoundException("Item ID " + id + " not found");
            }
            return item.get();
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public String deleteItem(Long id) {
        List<Item> i = itemRepository.checkItemRelation(id);
        if (i.size() > 0) {
            throw new BadRequestException("Cannot delete Item with existing category relationship");
        }

        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            log.error("Item ID {} not found", id);
            throw new NotFoundException("Item not found");
        }
        item.get().setDeletedDate(new Date());
//        itemRepository.deleteTest(id);
        itemRepository.save(item.get());
        return "Successfully deleted Item";
    }
}
