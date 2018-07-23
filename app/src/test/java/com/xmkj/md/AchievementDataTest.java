package com.xmkj.md;

import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.model.MonthlyAchievementBean;
import com.xmkj.md.utils.MdHttpHelper;

import org.json.JSONObject;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/2
 * 地点: 深圳
 */

public class AchievementDataTest {
    @Test
    public void testGetAchievement() throws Exception {
        JSONObject obj = new JSONObject(jsonStr());
        JSONObject dataObj = obj.getJSONObject("Data");
        AchievementBean<MonthlyAchievementBean> achievement = AchievementBean.getAchievement(dataObj);
        List<MonthlyAchievementBean> data = achievement.getData();
        assertEquals(2, achievement.getCount());
        assertEquals(1793500, (int) achievement.getLoanAmount());
        assertEquals(31, data.size());
        MonthlyAchievementBean mAchievement = data.get(0);
        assertEquals(1, (int) mAchievement.getDay());
        for (int i = 0; i < data.size(); i++) {
            assertEquals(1, (int) mAchievement.getDay());
        }
//        System.out.println(achievements.toString());
    }

    private String jsonStr() {
        return "{\n" +
                "    \"Success\": true,\n" +
                "    \"Data\": {\n" +
                "        \"P1\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 1\n" +
                "        },\n" +
                "        \"P2\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 2\n" +
                "        },\n" +
                "        \"P3\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 3\n" +
                "        },\n" +
                "        \"P4\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 4\n" +
                "        },\n" +
                "        \"P5\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 5\n" +
                "        },\n" +
                "        \"P6\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 6\n" +
                "        },\n" +
                "        \"P7\": {\n" +
                "            \"Count\": 2,\n" +
                "            \"SumMoney\": 1793500,\n" +
                "            \"Day\": 7\n" +
                "        },\n" +
                "        \"P8\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 8\n" +
                "        },\n" +
                "        \"P9\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 9\n" +
                "        },\n" +
                "        \"P10\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 10\n" +
                "        },\n" +
                "        \"P11\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 11\n" +
                "        },\n" +
                "        \"P12\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 12\n" +
                "        },\n" +
                "        \"P13\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 13\n" +
                "        },\n" +
                "        \"P14\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 14\n" +
                "        },\n" +
                "        \"P15\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 15\n" +
                "        },\n" +
                "        \"P16\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 16\n" +
                "        },\n" +
                "        \"P17\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 17\n" +
                "        },\n" +
                "        \"P18\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 18\n" +
                "        },\n" +
                "        \"P19\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 19\n" +
                "        },\n" +
                "        \"P20\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 20\n" +
                "        },\n" +
                "        \"P21\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 21\n" +
                "        },\n" +
                "        \"P22\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 22\n" +
                "        },\n" +
                "        \"P23\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 23\n" +
                "        },\n" +
                "        \"P24\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 24\n" +
                "        },\n" +
                "        \"P25\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 25\n" +
                "        },\n" +
                "        \"P26\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 26\n" +
                "        },\n" +
                "        \"P27\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 27\n" +
                "        },\n" +
                "        \"P28\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 28\n" +
                "        },\n" +
                "        \"P29\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 29\n" +
                "        },\n" +
                "        \"P30\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 28\n" +
                "        },\n" +
                "        \"P31\": {\n" +
                "            \"Count\": 0,\n" +
                "            \"SumMoney\": 0,\n" +
                "            \"Day\": 31\n" +
                "        },\n" +
                "        \"Count\": 2,\n" +
                "        \"Sum\": 1793500\n" +
                "    }\n" +
                "}";
    }

}
