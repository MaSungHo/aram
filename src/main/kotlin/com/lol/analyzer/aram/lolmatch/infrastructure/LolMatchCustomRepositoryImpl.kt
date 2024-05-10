package com.lol.analyzer.aram.lolmatch.infrastructure

import com.lol.analyzer.aram.account.domain.QAccount
import com.lol.analyzer.aram.lolmatch.domain.*
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class LolMatchCustomRepositoryImpl(
    private val entityManager: EntityManager,
    private val jpaQueryFactory: JPAQueryFactory,
): LolMatchCustomRepository {
    override fun getLolMatchByMatchId(matchId: String): LolMatch? {
        val qLolMatch = QLolMatch.lolMatch

        val query = jpaQueryFactory
            .selectFrom(qLolMatch)
            .where(qLolMatch.matchId.eq(matchId))

        return query.fetchOne()
    }

    override fun getLolMatchesByAccountPuuid(
        puuid: String,
        cursor: Long,
        count: Int
    ): List<LolMatch> {
        val qAccount = QAccount.account // TODO 여기에 있는것이 맞나.. 구조 확인
        val qLolMatch = QLolMatch.lolMatch
        val qAccountLolMatch = QAccountLolMatch.accountLolMatch

        // TODO: fix N+1
        val query = jpaQueryFactory
            .selectFrom(qLolMatch)
            .innerJoin(qLolMatch._accountLolMatches, qAccountLolMatch)
            .innerJoin(qAccountLolMatch.account, qAccount).on(qAccount.puuid.eq(puuid))
            .where(qLolMatch.id.gt(cursor))
            .limit(count.toLong())

        return query.fetch()
    }

    override fun getLolMatchesCountAndLastLolMatchId(puuid: String): Pair<Int, String?> {
        val qAccount = QAccount.account // TODO 여기에 있는것이 맞나.. 구조 확인
        val qLolMatch = QLolMatch.lolMatch
        val qAccountLolMatch = QAccountLolMatch.accountLolMatch

        val count = jpaQueryFactory
            .selectFrom(qLolMatch)
            .select(qLolMatch.count())
            .innerJoin(qLolMatch._accountLolMatches, qAccountLolMatch)
            .innerJoin(qAccountLolMatch.account, qAccount).on(qAccount.puuid.eq(puuid))
            .fetchFirst()

        val lastId = jpaQueryFactory
            .selectFrom(qLolMatch)
            .select(qLolMatch.matchId)
            .innerJoin(qLolMatch._accountLolMatches, qAccountLolMatch)
            .innerJoin(qAccountLolMatch.account, qAccount).on(qAccount.puuid.eq(puuid))
            .orderBy(qLolMatch.id.desc())
            .limit(1L)
            .fetchOne()

        return Pair(count.toInt(), lastId)
    }

    override fun save(lolMatch: LolMatch) {
        entityManager.persist(lolMatch)
    }
}