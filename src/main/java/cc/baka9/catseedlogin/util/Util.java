package cc.baka9.catseedlogin.util;

import cc.baka9.catseedlogin.common.util.DateUtil;
import cc.baka9.catseedlogin.common.util.ValidationUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    private static final Pattern passwordDifficultyRegex = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean passwordIsDifficulty(String pwd) {
        return ValidationUtil.isPasswordTooSimple(pwd);
    }

    public static String time2Str(long time) {
        synchronized (sdf) {
            return sdf.format(new Date(time));
        }
    }

    public static boolean checkMail(String e_mail) {
        return ValidationUtil.isValidEmail(e_mail);
    }

    public static String randomStr() {
        return DateUtil.generateVerificationCode();
    }

    public static boolean isOSLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
}
