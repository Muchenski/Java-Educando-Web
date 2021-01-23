package com.muchenski.course.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.muchenski.course.domain.Post;
import com.muchenski.course.domain.User;
import com.muchenski.course.dto.AuthorDto;
import com.muchenski.course.repositories.PostRepository;
import com.muchenski.course.repositories.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		// Como iremos associar o AuthorDto com os Post, necessitamos
		// salvar o User no banco, antes de instanciar o AuthorDto.
		// Pois só assim o Author irá receber o id do User(gerado pelo banco).

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post p1 = new Post(null, sdf.parse("2018-03-21"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDto(maria));
		Post p2 = new Post(null, sdf.parse("2018-03-23"), "Bom dia", "Acordei feliz hoje!", new AuthorDto(maria));

		postRepository.saveAll(Arrays.asList(p1, p2));

		maria.getPosts().addAll(Arrays.asList(p1, p2));

		userRepository.save(maria);
	}

}
