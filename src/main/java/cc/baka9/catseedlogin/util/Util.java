package cc.baka9.catseedlogin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    private static final Pattern passwordDifficultyRegex = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static boolean passwordIsDifficulty(String pwd) {
        return !passwordDifficultyRegex.matcher(pwd).find();
    }

    public static String time2Str(long time) {
        synchronized (sdf) {
            return sdf.format(new Date(time));
        }
    }

    public static boolean checkMail(String e_mail) {
        return e_mail.matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+");
    }

    public static String randomStr() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(10);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static boolean isOSLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
}
