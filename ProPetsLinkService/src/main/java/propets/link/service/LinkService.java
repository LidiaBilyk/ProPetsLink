package propets.link.service;

import java.util.List;

import propets.link.dto.PostDto;

public interface LinkService {
	
	List<PostDto> getLink(String id);

}
