package com.sunside.application.repositories;

import com.sunside.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository {

    List<Item> findAllByUserId(UUID userId);

    Item save(Item item);

    List<Item> findAll();

    void deleteById(UUID id);
}
