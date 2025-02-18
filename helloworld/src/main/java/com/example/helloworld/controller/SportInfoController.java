package com.example.helloworld.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.helloworld.entity.SportInfo;
import com.example.helloworld.service.SportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
@CrossOrigin(origins = "http://localhost:8080")
public class SportInfoController {

    @Autowired
    private SportInfoService sportService;

    // 获取所有运动信息
    @GetMapping
    public List<SportInfo> getAllSports() {
        return sportService.list();
    }

    // 分页查询运动信息
    // 分页查询运动信息，支持搜索
    @GetMapping("/page")
    public Page<SportInfo> getSportsByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        QueryWrapper<SportInfo> queryWrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like("sport_type", keyword); // 模糊查询
        }
        Page<SportInfo> pageParam = new Page<>(page, size);
        pageParam.setOptimizeCountSql(false); // 禁用 SQL 优化
        return sportService.page(pageParam, queryWrapper);
    }

    // 添加运动信息
    @PostMapping("/api/sports")
    public boolean addSport(@RequestBody SportInfo sportInfo) {
        System.out.println("接收到的新增数据: " + sportInfo); // 打印接收到的数据
        if (sportInfo.getId() != null) {
            System.out.println("新增时 id 应该为空: " + sportInfo.getId());
            return false;
        }
        return sportService.save(sportInfo);
    }

    // 更新运动信息
    @PutMapping
    public boolean updateSport(@RequestBody SportInfo sportInfo) {
        return sportService.updateById(sportInfo);
    }

    // 删除运动信息
    @DeleteMapping("/{id}")
    public boolean deleteSport(@PathVariable Integer id) {
        return sportService.removeById(id);
    }
}
