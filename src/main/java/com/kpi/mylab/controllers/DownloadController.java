package com.kpi.mylab.controllers;

import com.kpi.mylab.services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/download")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @GetMapping("/rx")
    public Map<String, ?> getWithRXJava() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("data", downloadService.getAllEntriesWithRXJava());
        return modelMap;
    }




}
