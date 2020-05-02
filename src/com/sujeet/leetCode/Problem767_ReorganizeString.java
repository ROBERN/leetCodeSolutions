package com.sujeet.leetCode;

import java.util.*;

public class Problem767_ReorganizeString {
    private static class Key {
        char c;
        int count;
<<<<<<< HEAD
        Random random = new Random();
=======
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
        Key(char ch, int countVal) {
            c = ch;
            count = countVal;
        }
    }

    public static String reorganizeString(String S) {
<<<<<<< HEAD
=======
        TreeMap<Character, Integer> map = new TreeMap<>();

>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
        int[] counts = new int[26];
        for(int i = 0; i < S.length(); i++) {
            counts[S.charAt(i) -'a']++;
        }
<<<<<<< HEAD
        String string = " hello  man";
        string.split(" ");
=======
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
        List<Key> keys = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            if(counts[i] > 0) {
                keys.add(new Key((char)('a'+i), counts[i]));
            }
        }
        keys.sort((a, b) -> b.count - a.count);

        char[] ans = new char[S.length()];
        for(int i = 0; i < S.length(); i++) ans[i] = '$';

        for(int i = 0; i < keys.size(); i++) {
            int idx = 0;
            int added = 0;
            while(idx < S.length() && added < keys.get(i).count) {
                if(ans[idx] == '$') {
                    ans[idx] = keys.get(i).c;
                    added++;
                    idx += 2;
                } else {
                    idx++;
                }
            } //end while
            if(added < keys.get(i).count) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < S.length(); i++) {
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem767_ReorganizeString.reorganizeString(
                "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao"
        );
    }
}
