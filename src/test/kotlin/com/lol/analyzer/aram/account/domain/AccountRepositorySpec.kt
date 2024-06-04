package com.lol.analyzer.aram.account.domain

import com.lol.analyzer.aram.account.fixture.AccountDataFactory
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositorySpec(
    @Autowired private val accountRepository: AccountRepository
): BehaviorSpec({
    Given("findByUuid") {
        When("uuid 에 해당하는 account 가 있으면") {
            Then("해당 account 를 반환한다") {
                val account = AccountDataFactory.account()
                accountRepository.save(account)

                val result = accountRepository.findByUuid(account.uuid!!)
                result!!.id shouldBe account.id
            }
        }

        When("uuid 에 해당하는 account 가 없으면") {
            Then("null 을 반환한다") {
                val result = accountRepository.findByUuid("random-uuid")
                result shouldBe null
            }
        }
    }

    Given("findByGameNameAndTagLine") {
        When("gameName 과 tagLine 을 가진 account 가 있으면") {
            val gameName = "Test name"
            val tagLine = "KR1"
            val account = AccountDataFactory.account(gameName = gameName, tagLine = tagLine)
            accountRepository.save(account)

            val result = accountRepository.findByGameNameAndTagLine(gameName, tagLine)
            result shouldNotBe null
            result!!.gameName shouldBe gameName
            result!!.tagLine shouldBe tagLine
        }

        When("gameName 과 tagLine 을 가진 account 가 없으면") {
            Then("null 을 반환한다") {
                val result = accountRepository.findByGameNameAndTagLine("not-exists", "not-exists")
                result shouldBe null
            }
        }
    }
})
