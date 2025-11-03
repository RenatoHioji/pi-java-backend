package com.sunside.service;


import com.sunside.client.AIClient;
import com.sunside.dto.item.CategoryDTOResponse;
import com.sunside.dto.item.ItemDTORequest;
import com.sunside.dto.item.SyllablesDTORequest;
import com.sunside.dto.item.SyllablesDTOResponse;
import com.sunside.exceptions.BusinessException;
import com.sunside.mapper.ItemMapper;
import com.sunside.model.Item;
import com.sunside.model.User;
import com.sunside.model.UserHistory;
import com.sunside.repository.ItemRepository;
import com.sunside.repository.UserHistoryRepository;
import com.sunside.repository.UserRepository;
import com.sunside.utils.AWSUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final AWSUtils awsUtils;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final AIClient aiClient;

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

    public Item findById(UUID id, String username){
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        Item item = this.itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException(("Item não encontrado.")));

        updateUserHistory(user, item);

        return item;
    }

    public void updateUserHistory(User user, Item item) {
        this.userHistoryRepository.findByUserIdAndItemId(user.getId(), item.getId())
                .ifPresentOrElse(
                        this::updateHistoryView,
                        () -> createNewUserHistory(user, item)
                );
    }

    private void updateHistoryView(UserHistory history) {
        int updatedViewCount = history.getViewed() + 1;
        history.setViewed(updatedViewCount);
        history.setLast_viewed(LocalDateTime.now());
        this.userHistoryRepository.save(history);
    }

    private void createNewUserHistory(User user, Item item) {
        UserHistory newUserHistory = UserHistory.builder()
                .user(user)
                .item(item)
                .last_viewed(LocalDateTime.now())
                .viewed(1)
                .build();

        this.userHistoryRepository.save(newUserHistory);
    }

    public List<Item> findRecents(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        return this.userHistoryRepository.findRecents(user.getId());
    }

    public List<Item> findMoreViewed(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        return this.userHistoryRepository.findMoreViewed(user.getId());
    }

    public CategoryDTOResponse searchCategory(MultipartFile file){
        return aiClient.searchCategory(file);
    }

    public SyllablesDTOResponse divideSyllables(SyllablesDTORequest word) {
        return aiClient.divideSyllables(word);
    }
}
