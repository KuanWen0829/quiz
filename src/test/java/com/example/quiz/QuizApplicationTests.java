package com.example.quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class QuizApplicationTests {

	@Test
	public void test3() {//計算 A,B,C,D,E 各出現了幾次
		List<String> list = List.of("A","B","C","D","E");
		String str = "AABBBCDDAEEEAACCDD";
		Map<String,Integer> map = new HashMap<>();
		for(String item : list) {
			String newStr= str.replace(item, "");
			int count = str.length()-newStr.length();
			map.put(item, count);
		}
		System.out.println(map);
	}

}
