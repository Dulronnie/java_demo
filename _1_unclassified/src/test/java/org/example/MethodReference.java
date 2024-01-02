package org.example;

import junit.framework.TestCase;
import sun.util.resources.LocaleData;

import java.awt.event.ItemEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 测试方法引用
 */
public class MethodReference extends TestCase {
    /**
     * 静态方法
     * @param a
     * @return
     */
    public static boolean justReturn(int a) {
        System.out.println("fkdjafljdajfldjajfdjftnrbgbebgnhhgbjzlvuuihuihjkjgfjsbhoih ghthtgug");
        return true;
    }

    public void testStaticMethod() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate nowDate = LocalDate.now();
        System.out.println("nowDate = " + nowDate);

        LocalTime nowTime = LocalTime.now();
        System.out.println("nowTime = " + nowTime);

        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("nowDateTime = " + nowDateTime);
        String format = dateTimeFormatter.format(nowDateTime);
        System.out.println("format = " + format);
        LocalDateTime with = nowDateTime.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("dateTimeFormatter.format(nowDateTime) = " + dateTimeFormatter.format(nowDateTime));
        System.out.println("dateTimeFormatter.format(nowDateTime) = " + dateTimeFormatter.format(with));

        Instant now = Instant.now();
        System.out.println("now = " + now.getEpochSecond());
    }


    public void testMd5() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.
    }

}
