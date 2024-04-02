package com.lol.analyzer.aram.common.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Default")
class HomeController {
    @GetMapping()
    fun root(): String {
        return "Hello World!"
    }

    @GetMapping("/health")
    fun health(): String {
        return "OK"
    }
}