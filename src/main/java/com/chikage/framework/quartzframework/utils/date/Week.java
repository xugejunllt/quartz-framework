package com.chikage.framework.quartzframework.utils.date;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @title: Week
 * @package: cn.freemud.svc.adapter.utils.date
 * @description: ${TODO}(用一句话描述该文件做什么)
 * @author: chikage(chikagelin @ 163.com)
 * @date: 2018/10/10 下午5:39
 * @version: v1.0
 * @copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum Week {
    MONDAY("星期一", "Monday", "Mon.", 1),
    TUESDAY("星期二", "Tuesday", "Tues.", 2),
    WEDNESDAY("星期三", "Wednesday", "Wed.", 3),
    THURSDAY("星期四", "Thursday", "Thur.", 4),
    FRIDAY("星期五", "Friday", "Fri.", 5),
    SATURDAY("星期六", "Saturday", "Sat.", 6),
    SUNDAY("星期日", "Sunday", "Sun.", 7);

    String name_cn;
    String name_en;
    String name_enShort;
    int number;

    Week(String name_cn, String name_en, String name_enShort, int number) {
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.name_enShort = name_enShort;
        this.number = number;
    }

    public String getChineseName() {
        return name_cn;
    }

    public String getName() {
        return name_en;
    }

    public String getShortName() {
        return name_enShort;
    }

    public int getNumber() {
        return number;
    }
}
