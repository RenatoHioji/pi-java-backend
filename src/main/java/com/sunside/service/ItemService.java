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

    public Item update(UUID id, ItemDTORequest request){
        Item item = itemRepository.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        this.deleteFiles(item);
        Item itemUpdated = ItemMapper.map(item, request);
        this.saveFiles(request, itemUpdated);

        return itemRepository.save(itemUpdated);
    }

    public Item createToUser(ItemDTORequest request, UUID userId){
        Item item = ItemMapper.map(request);
        item.setUserId(userId);

        saveFiles(request, item);
        return itemRepository.save(item);
    }

    public void delete(UUID id){
        Item item = itemRepository.findById(id)
                        .orElseThrow(() -> new BusinessException("Item não encontrado"));
        try{
            this.deleteFiles(item);
            itemRepository.deleteById(id);

        }catch (Exception e){
            throw new BusinessException("Erro ao deletar item");
        }

    }

    public void deleteFiles(Item item){
        awsUtils.deleteFile(item.getVideo());
        awsUtils.deleteFile(item.getAudio());
        awsUtils.deleteFile(item.getImage());
    }

    public void saveFiles(ItemDTORequest request, Item item){
        awsUtils.saveFile(item.getVideo(), request.video());
        awsUtils.saveFile(item.getAudio(), request.audio());
        awsUtils.saveFile(item.getImage(), request.image());
    }
}
