package com.beatriz.workshopmongo.sercives;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beatriz.workshopmongo.domain.Post;
import com.beatriz.workshopmongo.repositoriy.PostRepository;
import com.beatriz.workshopmongo.sercives.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id){
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000 );//somando mais 24 horas em ms
		return repo.fullSearch(text, minDate, maxDate);
	}
}
