package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wys.live.server.service.CategoryService;

/**
 * @author wys
 * @date 2021/2/4 1:00
 */
@RestController
@AllArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @GetMapping("/insertCategories")
    public void insertCategories() {
        categoryService.insertAllCategories();
    }

}
