package com.ooad.scriptpro.service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.ooad.scriptpro.api.TypeRepository;
import com.ooad.scriptpro.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeRepository typeRepository;
    @Override
    public Type findServiceByName(String name) {
        return typeRepository.findTypeByName(name);
    }
}
