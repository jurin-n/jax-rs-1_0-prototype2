package com.jurin_n.jax_rs.providers;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Ignore("テストの書き方わからずorz")
	@Test
	public void test_writeTo(){
        // バイト配列ストリームを作る
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        
        // バイト配列ストリームをオブジェクトストリームでラップ
        ObjectOutputStream oout;
		try {
			oout = new ObjectOutputStream(bout);
			sut.writeTo(json, null, null, null, null, null, oout);
			ObjectMapper mapper = new ObjectMapper();
			PracticeMenuRepresentation jsonMarshal = mapper.readValue(bout.toString("UTF-8"),PracticeMenuRepresentation.class);
			System.out.println(jsonMarshal.getId());
			System.out.println(jsonMarshal.getMenu());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
