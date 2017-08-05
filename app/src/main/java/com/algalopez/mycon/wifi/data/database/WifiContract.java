package com.algalopez.mycon.wifi.data.database;

import android.provider.BaseColumns;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */

public class WifiContract {


    public final static class WifiEntry implements BaseColumns {

        public final static String TABLE_NAME = "WIFI";
        public final static String COLUMN_SSID = "SSID";
        public final static String COLUMN_PASSWORD = "PASSWORD";
        public final static String COLUMN_LASTUPDATED = "LASTUPDATED";

        public static String[] getProjection() {

            return new String[] {_ID, COLUMN_SSID, COLUMN_PASSWORD, COLUMN_LASTUPDATED};
        }
    }


    public final static class DeviceEntry implements BaseColumns {

        public final static String TABLE_NAME = "DEVICE";
        public final static String COLUMN_MAC = "MAC";
        public final static String COLUMN_NAME = "NAME";
        public final static String COLUMN_BRAND = "BRAND";

        public static String[] getProjection() {
            return new String[] {_ID, COLUMN_MAC, COLUMN_NAME, COLUMN_BRAND};
        }
    }


    public final static class WifiConnectDeviceEntry implements BaseColumns {

        public final static String TABLE_NAME = "WIFI_CONNECT_DEVICE";
        public final static String COLUMN_WIFI = "WIFI_FK";
        public final static String COLUMN_DEVICE = "DEVICE_FK";
        public final static String COLUMN_IP = "IP";

        public static String[] getProjection() {
            return new String[] {COLUMN_WIFI, COLUMN_DEVICE, COLUMN_IP};
        }
    }

}
