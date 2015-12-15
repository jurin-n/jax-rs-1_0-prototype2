package com.jurin_n.jax_rs.providers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

import com.jurin_n.jax_rs.representation.PracticeMenuRepresentation;

public class JsonMarshallerTest {

	private JsonMarshaller sut;
	private PracticeMenuRepresentation json;
	
	@Before
	public void setUp(){
		sut = new JsonMarshaller();
		json = new PracticeMenuRepresentation("id001","メニュー");
	}
	
	@Test
	public void test_getSize_マイナス１返却される() {
		long result = sut.getSize(
				  json
				, PracticeMenuRepresentation.class
				, null
				, null
				, null);
		assertThat(result,is(-1L));
	}
	
	@Test
	public void test_isWriteable_trueになるケース(){
		Class<?> clazz = json.getClass();
		assertThat(sut.isWriteable(clazz, null, null, null), is(true));
	}
	
	@Test
	public void test_isWriteable_falseになるケース(){
		Class<?> clazz = String.class;
		assertThat(sut.isWriteable(clazz, null, null, null), is(false));
	}
}
