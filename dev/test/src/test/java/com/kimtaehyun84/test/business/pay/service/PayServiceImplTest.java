package com.kimtaehyun84.test.business.pay.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

public class PayServiceImplTest {

    @Before
    public void setUp() throws Exception {

        HashMap<String,String> inputParam = new HashMap<>();
        inputParam.put("amount", "10000");
        inputParam.put("targetNum", "5");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void distribute() {

        HashMap<String,String> inputParam = new HashMap<>();
        inputParam.put("amount", "10000");
        inputParam.put("targetNum", "5");


        String token = getToken();
        assertNotEquals(token, null);
        System.out.println(token);

        Random rand  = new Random(System.currentTimeMillis());
        int totalAmount = Integer.parseInt(inputParam.get("amount"));
        int totalTargetNum = Integer.parseInt(inputParam.get("targetNum"));
        int[] distributeAmount = new int[totalTargetNum];
        for(int i = 0 ; i<distributeAmount.length; i++){
            System.out.println("==================" + i + "===============");
            if(i+1 == distributeAmount.length){
                distributeAmount[i] = totalAmount;
            }
            else{
                distributeAmount[i] = (int)Math.floor(totalAmount*rand.nextDouble());

            }
            totalAmount -= distributeAmount[i];
            System.out.println( distributeAmount[i] +" :" + totalAmount );
        }
        int tempTotal = 0;
        for(int j = 0; j<distributeAmount.length; j++){
            tempTotal+=distributeAmount[j];
        }
        assertEquals(tempTotal,Integer.parseInt(inputParam.get("amount")));


    }
    private String getToken(){

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        String token = "";
        for(int i = 0; i < 3; i++){

            int val = rand.nextInt(126);
            if(val < 33)
                val += 33;
            token = token.concat(Character.toString((char)val));
        }

        return token;

    }

}