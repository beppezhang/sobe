package com.kpuswebup.comom.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class GenerationNum {

    /**
     * 得到n位长度的随机数
     * 
     * @param n 随机数的长度
     * @return 返回 n位的随机整数
     */
    public static int getRandomNumber(int n) {
        int temp = 0;
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);
        Random rand = new Random();

        while (true) {
            temp = rand.nextInt(max);
            if (temp >= min) break;
        }

        return temp;
    }

    public static String getTimeMillisSequence() {
        long nanoTime = System.nanoTime();
        String preFix = "";
        if (nanoTime < 0) {
            preFix = "A";// 负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题。
            nanoTime = nanoTime + Long.MAX_VALUE + 1;
        } else {
            preFix = "Z";
        }
        String nanoTimeStr = String.valueOf(nanoTime);

        int difBit = String.valueOf(Long.MAX_VALUE).length() - nanoTimeStr.length();
        for (int i = 0; i < difBit; i++) {
            preFix = preFix + "0";
        }
        nanoTimeStr = preFix + nanoTimeStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 24小时制
        String timeMillisSequence = sdf.format(System.currentTimeMillis()) + "-" + nanoTimeStr;

        return timeMillisSequence;
    }

}
