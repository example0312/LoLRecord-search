package org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.controller;

import org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.domain.*;
import org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.service.LeagueInfoService;
import org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.service.MatchIdService;
import org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.service.MatchInfoService;
import org.ajou.realcoding.team4.LoLmatch.LoLRecordsearch.service.SummonerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SummonerInfoService summonerInfoService;
    @Autowired
    private LeagueInfoService leagueInfoService;
    @Autowired
    private MatchIdService MatchIdService;
    @Autowired
    private MatchInfoService matchInfoService;

    @GetMapping("/summonerApi-service/summoners")
    public List<SummonerMatchInfo> getSummonerMatchInfo(@RequestParam String summonerId, @RequestParam String apiKey) {
        // step2
        Summoner currentSummonerInfo = summonerInfoService.getSummoner(summonerId, apiKey);

        // step3
        LeagueEntry currentLeagueEntry = leagueInfoService.getLeagueEntry(currentSummonerInfo.getId(), apiKey);

        // step4
        MatchId currentFiveMatchId = MatchIdService.getFiveMatchId(currentSummonerInfo.getAccountId(), apiKey);

        // step5
        List<SummonerMatchInfo> matchInfoList = matchInfoService.getMatchInfoList(summonerId,currentFiveMatchId, apiKey);

        return matchInfoList;
    }

}