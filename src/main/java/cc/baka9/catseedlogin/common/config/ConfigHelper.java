package cc.baka9.catseedlogin.common.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

public class ConfigHelper {

    public static String parseLocationString(LocationData location) {
        return String.format("%s:%.2f:%.2f:%.2f:%.2f:%.2f",
                location.world,
                location.x,
                location.y,
                location.z,
                location.yaw,
                location.pitch);
    }

    public static LocationData parseLocationString(String locationStr, LocationData defaultLocation) {
        if (locationStr == null || locationStr.isEmpty()) {
            return defaultLocation;
        }
        String[] parts = locationStr.split(":");
        try {
            return new LocationData(
                    parts.length > 0 ? parts[0] : defaultLocation.world,
                    parts.length > 1 ? Double.parseDouble(parts[1]) : defaultLocation.x,
                    parts.length > 2 ? Double.parseDouble(parts[2]) : defaultLocation.y,
                    parts.length > 3 ? Double.parseDouble(parts[3]) : defaultLocation.z,
                    parts.length > 4 ? Float.parseFloat(parts[4]) : defaultLocation.yaw,
                    parts.length > 5 ? Float.parseFloat(parts[5]) : defaultLocation.pitch
            );
        } catch (NumberFormatException e) {
            return defaultLocation;
        }
    }

    public static class LocationData {
        public String world;
        public double x;
        public double y;
        public double z;
        public float yaw;
        public float pitch;

        public LocationData(String world, double x, double y, double z, float yaw, float pitch) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }
    }

    public static String sanitizeString(String input) {
        if (input == null) {
            return null;
        }
        return input.trim();
    }

    public static int parseIntOrDefault(String value, int defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long parseLongOrDefault(String value, long defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean parseBooleanOrDefault(String value, boolean defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
