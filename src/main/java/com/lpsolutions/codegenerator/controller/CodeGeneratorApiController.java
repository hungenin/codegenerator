package com.lpsolutions.codegenerator.controller;

import com.lpsolutions.codegenerator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CodeGeneratorApiController {
    @Autowired
    CodeGeneratorService codeGeneratorService;

    @GetMapping("/test")
    public String getTestCode(@RequestParam(required = false) String[] h1,
                              @RequestParam(required = false) String[] p,
                              @RequestParam(required = false) String[] a,
                              @RequestParam(required = false) String[] table,
                              @RequestParam(required = false) String[] tr,
                              @RequestParam(required = false) String[] td) {
        return codeGeneratorService.getTestCode(h1, p, a, table, tr, td);
    }
}
