package com.smoothstack.gcfashion.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StoreServiceTest.class, SaveTransaction.class, UpdateTransaction.class, Payment.class, Find.class, Get.class })
public class TestSuite {

}