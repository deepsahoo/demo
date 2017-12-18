package io.pivotal.workshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pivotal.repo.SnippetRepository;

import io.pivotal.domain.Snippet;

public class SnippetController {

	@Autowired
    SnippetRepository snippetRepository;

    @GetMapping("/snippets")
    public List<Snippet> snippets() {
        return snippetRepository.findAll();
    }

    @GetMapping("/snippets/{id}")
    public Snippet snippet(@PathVariable("id") String id) {
        return snippetRepository.findOne(id);
    }

    @PostMapping("/snippets")
    public ResponseEntity<?> add(@RequestBody Snippet snippet) {
        Snippet _snippet = snippetRepository.save(snippet);
        assert _snippet != null;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + _snippet.getId())
                .buildAndExpand().toUri());

        return new ResponseEntity<>(_snippet, httpHeaders, HttpStatus.CREATED);
    }

}
