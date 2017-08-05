package com.algalopez.mycon.wifi.data.manager;

import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public class WifiManagerTest {

    private WifiManager mWifiManager;

    @Before
    public void setUp() throws Exception {

        mWifiManager = new WifiManager(InstrumentationRegistry.getTargetContext());
    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testTest() throws Exception {

        mWifiManager.test();


    }
}
