package com.myproject.toy.common.core.sequence.app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());

        
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");


        System.out.println("Today=" + uuid);
	}

}
