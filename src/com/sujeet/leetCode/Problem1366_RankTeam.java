package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.*;

public class Problem1366_RankTeam {
    public static  String rankTeams(String[] votes) {
        //"ABC",
        // "ACB",
        // "ABC"
        // "ADC"
        // "ABD"

        // Map Char -> PositionCount.
        // Soret them based on PostionCount or Alphabatcially
        // A -> 3, 0,0 0
        // B -> 0, 2, 1
        // C- > 0, 1, 2
        if(votes.length == 0)
            return "";

        int voteLength = votes[0].length();
        Map<Character, int[]> charToPosCounts = new HashMap<>();
        for(String vote : votes) {
            for(int posInVote = 0; posInVote < voteLength ; posInVote++) {
                char c = vote.charAt(posInVote);
                charToPosCounts.putIfAbsent(c, new int[voteLength]);
                charToPosCounts.get(c)[posInVote]++;
            }
        }

        List<Character> chars =  new ArrayList<>(charToPosCounts.keySet());

        Collections.sort(chars, ((a, b) -> {
            int[] posCountsA = charToPosCounts.get(a);
            int[] posCountsB = charToPosCounts.get(b);

            for(int i = 0; i < posCountsA.length; i++) {
                if(posCountsA[i] > posCountsB[i])
                    return -1;
                if(posCountsA[i] < posCountsB[i])
                    return 1;
            }
            return a-b;
        }));

        List<Character> list = new ArrayList<>(charToPosCounts.keySet());
        Collections.sort(list, (a, b) -> {
            for(int i = 0; i < voteLength; i++){
                if(charToPosCounts.get(a)[i] != charToPosCounts.get(b)[i]){
                    return charToPosCounts.get(b)[i] - charToPosCounts.get(a)[i];
                }
            }
            return a - b;
        });

        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        Problem1366_RankTeam.rankTeams(new String[] {
                "FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"
        });
    }
}
