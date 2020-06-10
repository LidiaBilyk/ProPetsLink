package propets.link.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface DispatcherService extends Sink{
	
	String MATCHES_FOUND = "matchesfound";
	
	@Input(MATCHES_FOUND)
	MessageChannel matchesfound();
}
