package com.jurin_n.providers;

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

import org.codehaus.jackson.map.ObjectMapper;

import com.jurin_n.domain.model.team.Team;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonMessageBodyWriter implements MessageBodyWriter<Team> {

	@Override
	public long getSize(Team arg0, Class<?> type
						, Type genericType, Annotation[] annotation
						, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType) {
		return type==Team.class;
	}

	@Override
	public void writeTo(Team target, Class<?> type, Type genericType
					, Annotation[] annotation, MediaType mediaType
					, MultivaluedMap<String, Object> httpHeaders
					, OutputStream outputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, target);
	}
}
