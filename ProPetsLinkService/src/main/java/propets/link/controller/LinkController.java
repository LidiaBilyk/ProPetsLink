package propets.link.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import propets.link.dto.PostDto;
import propets.link.service.LinkService;

@RestController
@RequestMapping("/{lang}/v1")
public class LinkController {
	@Autowired
	LinkService linkService;
	
	@GetMapping("/{id}")
	public List<PostDto> getLink(@PathVariable String id) {
		return linkService.getLink(id);
	}
}
