package com.jurin_n.domain.model.practice;

import static org.junit.Assert.*;

import org.junit.Test;

public class PracticePlanIdTest {

	@Test
	public void test等値でないが等価のPracticePlanIdのテスト() {
		PracticePlanId id1 = new PracticePlanId("aaaa12345");
		PracticePlanId id2 = new PracticePlanId("aaaa12345");
		
		if(id1 == id2) fail();
		if(   id1.equals(id2) 
		   && id2.equals(id1)
		   && id1.equals(id1)
		   && id2.equals(id2)
		   && id1.hashCode() == id2.hashCode()
		   && id1.hashCode() == id1.hashCode()
		   && id2.hashCode() == id2.hashCode()
				){
			return;
		}
		fail();
	}
	
	@Test
	public void test等値のPracticePlanIdのテスト() {
		PracticePlanId id1 = new PracticePlanId("aaaa12345");
		PracticePlanId id2 = id1;
		
		if(!(id1 == id2)) fail(); //等値でないのでエラー
		
		if(   id1.equals(id2) 
		   && id2.equals(id1)
		   && id1.equals(id1)
		   && id2.equals(id2)
		   && id1.hashCode() == id2.hashCode()
		   && id1.hashCode() == id1.hashCode()
		   && id2.hashCode() == id2.hashCode()
				){
			return;
		}
		fail();
	}
	
	@Test
	public void test文字列表現toStringのテスト() {
		PracticePlanId id1 = new PracticePlanId("aaaa12345");
		if(id1.toString().equals("PracticePlanId[id=aaaa12345]")) return;
		fail();
	}
}
