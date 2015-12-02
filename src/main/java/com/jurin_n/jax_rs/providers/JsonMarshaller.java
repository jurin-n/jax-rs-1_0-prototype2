package com.jurin_n.jax_rs.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
//import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jurin_n.domain.model.BaseEntity;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMarshaller implements MessageBodyWriter<BaseEntity> {

	@Override
	public long getSize(BaseEntity arg0, Class<?> type
						, Type genericType, Annotation[] annotation
						, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType) {
		return type.getSuperclass()==BaseEntity.class;
	}

	@Override
	public void writeTo(BaseEntity target, Class<?> type, Type genericType
					, Annotation[] annotation, MediaType mediaType
					, MultivaluedMap<String, Object> httpHeaders
					, OutputStream outputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.writeValue(outputStream, target);
	}
}
