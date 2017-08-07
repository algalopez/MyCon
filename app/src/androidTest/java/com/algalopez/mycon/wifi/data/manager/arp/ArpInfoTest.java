package com.algalopez.mycon.wifi.data.manager.arp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */

public class ArpInfoTest {


    ArpInfo mArpInfo;

    @Before
    public void setUp() throws Exception {

        mArpInfo = new ArpInfo();
    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void readArpTest(){

        mArpInfo.readArp();

    }


}
