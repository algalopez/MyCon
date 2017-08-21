package com.algalopez.mycon.wifi.data.storage.database;

import android.provider.BaseColumns;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */

public class WifiContract {


    public final static class WifiEntry implements BaseColumns {

        final static String TABLE_NAME = "WIFI";
        public final static String COLUMN_SSID = "SSID";
        public final static String COLUMN_PASSWORD = "PASSWORD";
        public final static String COLUMN_LASTUPDATED = "LASTUPDATED";
        public final static String COLUMN_NETMASK = "NETMASK";
        public final static String COLUMN_BSSID = "BSSID";
        public final static String COLUMN_RSSI = "RSSI";
        public final static String COLUMN_FREQUENCY = "FREQUENCY";
        public final static String COLUMN_LINKSPEED = "LINKSPEED";
        public final static String COLUMN_NETWORKID = "NOETWORKID";
        public final static String COLUMN_SERVERADDRESS = "SERVERADDRESS";
        public final static String COLUMN_DNS1 = "DNS1";
        public final static String COLUMN_DNS2 = "DNS2";

        static String[] getProjection() {

            return new String[] {
                    _ID,
                    COLUMN_SSID,
                    COLUMN_PASSWORD,
                    COLUMN_LASTUPDATED,
                    COLUMN_NETMASK,
                    COLUMN_BSSID,
                    COLUMN_RSSI,
                    COLUMN_FREQUENCY,
                    COLUMN_LINKSPEED,
                    COLUMN_NETWORKID,
                    COLUMN_SERVERADDRESS,
                    COLUMN_DNS1,
                    COLUMN_DNS2
            };
        }
    }


    public final static class DeviceEntry implements BaseColumns {

        final static String TABLE_NAME = "DEVICE";
        public final static String COLUMN_MAC = "MAC";
        public final static String COLUMN_NAME = "NAME";
        public final static String COLUMN_BRAND = "BRAND";

        static String[] getProjection() {
            return new String[] {_ID, COLUMN_MAC, COLUMN_NAME, COLUMN_BRAND};
        }
    }


    public final static class WifiConnectDeviceEntry implements BaseColumns {

        final static String TABLE_NAME = "WIFI_CONNECT_DEVICE";
        public final static String COLUMN_WIFI = "WIFI_FK";
        public final static String COLUMN_DEVICE = "DEVICE_FK";
        public final static String COLUMN_IP = "IP";

        static String[] getProjection() {
            return new String[] {COLUMN_WIFI, COLUMN_DEVICE, COLUMN_IP};
        }
    }

}
