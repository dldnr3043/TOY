package com.myproject.toy;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToyLduWebApplicationTests {

	@Test
	void contextLoads() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c1 = Calendar.getInstance();

	 String strToday = sdf.format(c1.getTime());



        System.out.println("Today=" + strToday);



		
	}

}
