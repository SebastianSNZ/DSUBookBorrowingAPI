package com.dsu.bookborrowing.service;

import com.dsu.bookborrowing.entity.RolModel;
import com.dsu.bookborrowing.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public ArrayList<RolModel> getRol() {
        return (ArrayList<RolModel>) rolRepository.findAll();
    }

    public RolModel setRol(RolModel rol) {
        return rolRepository.save(rol);
    }
}
