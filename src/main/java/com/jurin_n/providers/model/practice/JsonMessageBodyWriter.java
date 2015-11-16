package com.jurin_n.providers.model.practice;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import com.jurin_n.domain.model.practice.PracticePlan;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMessageBodyWriter implements MessageBodyWriter<List<PracticePlan>> {

	@Override
	public long getSize(List<PracticePlan> arg0, Class<?> type
						, Type genericType, Annotation[] annotation
						, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType) {
		return type==Vector.class;
	}

	@Override
	public void writeTo(List<PracticePlan> target, Class<?> type, Type genericType
					, Annotation[] annotation, MediaType mediaType
					, MultivaluedMap<String, Object> httpHeaders
					, OutputStream outputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, target);
	}
}
