package propets.link.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@Document("links")
public class Link {
	@Id
	String id;
	@Singular
	Set<String> userLogins;
	@Singular
	Set<Post> posts;
	
	public boolean addLogin(String login) {
		return userLogins.add(login);
	}
	
	public boolean removeLogin(String login) {
		return userLogins.remove(login);
	}
	
	public boolean addPost(Post post) {
		return posts.add(post);
	}
	
	public boolean removePost(Post post) {
		return posts.remove(post);
	}
}
