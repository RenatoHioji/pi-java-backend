package com.sunside.service;


import com.sunside.dto.item.ItemDTORequest;
import com.sunside.exceptions.BusinessException;
import com.sunside.mapper.ItemMapper;
import com.sunside.model.Item;
import com.sunside.repository.ItemRepository;
import com.sunside.utils.AWSUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final AWSUtils awsUtils;
    private final ItemRepository itemRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<Item> findAllByUserId(UUID userId) {
        return itemRepository.findAllByUserId(userId);
    }

    public Item create(ItemDTORequest request){

        return itemRepository.save(ItemMapper.map(request));
    }

    public Item update(UUID id, ItemDTORequest request){
        Item item = itemRepository.findById(id).orElseThrow(() -> new BusinessException("Create"));

        Item itemUpdated = ItemMapper.map(item, request);

        return itemRepository.save(itemUpdated);
    }

    public Item createToUser(ItemDTORequest request, UUID userId){
        Item item = ItemMapper.map(request);
        item.setUserId(userId);
        return itemRepository.save(item);
    }

    public void delete(UUID id){
        itemRepository.deleteById(id);
    }

}
