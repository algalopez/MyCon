package com.algalopez.mycon.wifi.data.database;

import android.provider.BaseColumns;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */

class WifiContract {


    final static class WifiEntry implements BaseColumns {

        final static String TABLE_NAME = "WIFI";
        final static String COLUMN_SSID = "SSID";
        final static String COLUMN_PASSWORD = "PASSWORD";
        // final static String COLUMN_LASTUPDATED = "LASTUPDATED";

        public static String[] getProjection() {

            return new String[] {_ID, COLUMN_SSID, COLUMN_PASSWORD};
        }
    }


    final static class DeviceEntry implements BaseColumns {

        final static String TABLE_NAME = "DEVICE";
        final static String COLUMN_MAC = "MAC";
        final static String COLUMN_NAME = "NAME";
        final static String COLUMN_BRAND = "BRAND";

        public static String[] getProjection() {
            return new String[] {_ID, COLUMN_MAC, COLUMN_NAME, COLUMN_BRAND};
        }
    }


    final static class WifiConnectDeviceEntry implements BaseColumns {

        final static String TABLE_NAME = "WIFI_CONNECT_DEVICE";
        final static String COLUMN_WIFI = "WIFI_FK";
        final static String COLUMN_DEVICE = "DEVICE_FK";
        final static String COLUMN_IP = "IP";

        public static String[] getProjection() {
            return new String[] {COLUMN_WIFI, COLUMN_DEVICE, COLUMN_IP};
        }
    }

}
