package propets.link.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import propets.link.model.Address;
import propets.link.model.Location;

@Getter
@Builder
public class PostDto {
	String id;
	boolean typePost;
	String userLogin;
    String username; 
    String avatar;
	LocalDateTime datePost;
	String type;
	String sex;
	String breed;
	@Singular
	List<String> tags;
	@Singular
	List<String> photos;
	Address address;
    Location location;
}
