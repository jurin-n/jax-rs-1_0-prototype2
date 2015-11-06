package com.jurin_n.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

//import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.map.ObjectMapper;

import com.jurin_n.entity.Team;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonMessageBodyReader implements MessageBodyReader<Team> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotation, MediaType mediaType) {
		return type==Team.class;
	}

	@Override
	public Team readFrom(Class<Team> type, Type genericType, Annotation[] annotation
					,MediaType mediaType, MultivaluedMap<String, String> httpHeaders
					,InputStream inputStream) throws IOException, WebApplicationException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputStream, Team.class);
	}
}
