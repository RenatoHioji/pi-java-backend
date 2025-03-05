package com.sunside.utils;

import com.sunside.exceptions.BusinessException;

import java.util.UUID;

public class IdUtills {
    private IdUtills() {
        throw new IllegalStateException("Utility class");
    }
    public static UUID transformToUuid(String uuid){
        try{
            return UUID.fromString(uuid);
        }catch (Exception e){
            throw new BusinessException("Erro: Não foi possível transformar ID em UUID");
        }
    }
}
