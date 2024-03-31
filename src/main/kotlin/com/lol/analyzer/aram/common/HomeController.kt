package com.lol.analyzer.aram.common

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
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