package com.unstoppable.unstoppablestudy;

import com.unstoppable.unstoppablestudy.enums.ColorEnum;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class UnstoppablestudyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void colorEnumTest() {
		int redCode = 200;
		ColorEnum colorEnum = null;
		setColor(redCode,colorEnum );
		log.info("coler:{}",colorEnum);

		if(colorEnum!=null){
			log.info("redCode == colorEnum.getCode() : {} ",redCode == colorEnum.getCode());
		}
	}

	ColorEnum setColor(int redCode,ColorEnum colorEnum){
		if(redCode == ColorEnum.RED.getCode()){
			colorEnum = ColorEnum.RED;
		}
		return colorEnum;
	}
}
