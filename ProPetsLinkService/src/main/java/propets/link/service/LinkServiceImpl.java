package propets.link.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import propets.link.configuration.LinkConfiguration;
import propets.link.dao.LinkRepository;
import propets.link.dao.PostRepository;
import propets.link.dto.LinkDto;
import propets.link.dto.PostDto;
import propets.link.exceptions.BadRequestException;
import propets.link.exceptions.ConflictException;
import propets.link.exceptions.NotFoundException;
import propets.link.model.Link;
import propets.link.model.Post;


@EnableBinding(DispatcherService.class)
public class LinkServiceImpl implements LinkService {
	@Autowired
	LinkService linkService;
	@Autowired
	DispatcherService dispatcherService;
	@Autowired
	LinkRepository linkRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	LinkConfiguration linkConfiguration;

//	matches losts - one id, many logins	
	@StreamListener(DispatcherService.INPUT)
	public void lostConverter(Map<String, Set<String>> result) {
		Link link = lostsToLink(result);
		linkRepository.save(link);
// send to notifications
//		sendLink(link);
	}

//	private void sendLink(Link link) {
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> responseEntity = null;
//		try {
//			RequestEntity<LinkDto> requestEntity = new RequestEntity<LinkDto>(linkTolinkDto(link), HttpMethod.POST,	new URI(linkConfiguration.getNotificationsUri()));
//			responseEntity = restTemplate.exchange(requestEntity, String.class);
//		} catch (RestClientException e) {
//			throw new ConflictException();
//		} catch (URISyntaxException e) {
//			throw new BadRequestException();
//		}
//	}

	private Link lostsToLink(Map<String, Set<String>> result) {		
		String id = result.keySet().stream().findFirst().get();
		Set<String> userLogins = result.get(id);
		Set<Post> posts = new HashSet<>();
		Post post = postRepository.findById(id).get();
		posts.add(post);
		return Link.builder().userLogins(userLogins).posts(posts).build();
	}

//	matches founds - one login, many ids
	@StreamListener(DispatcherService.MATCHES_FOUND)
	public void foundConverter(Map<String, Set<String>> result) {
		Link link = foundsToLink(result);
		linkRepository.save(link);
// send to notifications
//		sendLink(link);
	}

	private Link foundsToLink(Map<String, Set<String>> result) {
		Set<String> userLogins = new HashSet<>();
		String userLogin = result.keySet().stream().findFirst().get();
		userLogins.add(userLogin);
		Set<String> testSet = result.get(userLogin);
		Iterable<Post> postFromService = postRepository.findAllById(testSet);
		Set<Post> posts = StreamSupport.stream(postFromService.spliterator(), false).collect(Collectors.toSet());
		return Link.builder().userLogins(userLogins).posts(posts).build();
}

	@Override
	public List<PostDto> getLink(String id) {
		Link link = linkRepository.findById(id).orElseThrow(NotFoundException::new);
		return link.getPosts().stream().map(p -> postToPostDto(p)).collect(Collectors.toList());
	}

	private PostDto postToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.typePost(post.isTypePost())
				.userLogin(post.getUserLogin())
				.username(post.getUsername())
				.avatar(post.getAvatar())
				.datePost(post.getDatePost())
				.type(post.getType())
				.breed(post.getBreed())
				.tags(post.getTags())
				.photos(post.getPhotos())
				.address(post.getAddress())
				.location(post.getLocation())
				.build();
	}

	private LinkDto linkTolinkDto(Link link) {
		return LinkDto.builder().id(link.getId()).userLogins(link.getUserLogins())
				.posts(link.getPosts().stream().map(p -> postToPostDto(p)).collect(Collectors.toSet())).build();
	}
}
