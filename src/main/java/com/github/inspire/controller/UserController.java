package com.github.inspire.controller;

import com.github.inspire.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping("login")
    public Result<String> login() {
        return Result.success("inspire");
    }

    @RequestMapping("info")
    public Result<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "inspire");
        info.put("roles", Collections.singletonList("admin"));
        return Result.success(info);
    }

    @RequestMapping("logout")
    public Result<String> logout() {
        return  Result.success("success");
    }
}
