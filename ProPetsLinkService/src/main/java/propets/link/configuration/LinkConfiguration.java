package propets.link.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@RefreshScope
public class LinkConfiguration {
	@Value("${notificationsUri}")
	String notificationsUri;
	@Value("${checkJwtUri}")
	String checkJwtUri;
}
