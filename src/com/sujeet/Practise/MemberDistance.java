package com.sujeet.Practise;


import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MemberDistance {

    public class MemberId {
        int memberId;
    }
    Map<MemberId, Set<MemberId>> connections;

    int getDistance(MemberId member1, MemberId member2) {
        if (member1 == member2)
            return 0;
        Set<MemberId> member1Connections = connections.get(member1);
        if (member1Connections.contains(member2))
            return 1;
        Set<MemberId> member2Connections = connections.get(member2);
        if (!Collections.disjoint(member1Connections, member2Connections))
            return 2;

        for (MemberId member1ConnectionId : member1Connections) {
            Set<MemberId> member1SecondDegreeConnection = connections.get(member1);
            if (!Collections.disjoint(member1SecondDegreeConnection, member2Connections))
                return 3;
        }
        return -1; //out of network
    }
}
