package com.dsu.bookborrowing.controller;

import com.dsu.bookborrowing.entity.RolModel;
import com.dsu.bookborrowing.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/rol")
public class RolController {
    @Autowired
    RolService rolService;

    @CrossOrigin
    @GetMapping()
    public ArrayList<RolModel> getRol(){
        return  rolService.getRol();
    }

    @CrossOrigin
    @PostMapping
    public  RolModel setRol(@RequestBody RolModel rol){
        return  rolService.setRol(rol);
    }
}
