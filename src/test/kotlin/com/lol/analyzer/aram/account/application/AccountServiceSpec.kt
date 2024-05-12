package com.lol.analyzer.aram.account.application

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AccountServiceSpec: StringSpec({
    "2 + 2 should be 4" {
        (2 + 2) shouldBe  4
    }

    "2 * 2 should be 4" {
        (2 * 2) shouldBe 4
    }
})
