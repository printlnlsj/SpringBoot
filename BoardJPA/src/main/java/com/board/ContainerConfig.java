package com.board;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContainerConfig {

	@Value("${tomcat.ajp.protocol}")
	String ajpProtocol;
	
	@Value("${tomcat.ajp.port}")
	int ajpPort;

	@Value("${tomcat.ajp.enabled}")
	boolean ajpEnabled;

	@Bean
    TomcatServletWebServerFactory servletContainer() {
		
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		
		if(ajpEnabled) {
			
			Connector ajpConnector = new Connector(ajpProtocol);
			ajpConnector.setPort(ajpPort);
			ajpConnector.setSecure(false);
			ajpConnector.setAllowTrace(false);
			ajpConnector.setScheme("http");
			tomcat.addAdditionalTomcatConnectors(ajpConnector);
			((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
			
		}
		
		return tomcat;
	}
}
/*	
	private Connector createAjpConnector() {
		
		Connector ajpConnector = new Connector(ajpProtocol);
		ajpConnector.setPort(Integer.parseInt(ajpPort));
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("http");
		((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
		return ajpConnector;
		
	}
}
*/