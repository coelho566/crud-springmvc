package br.com.gft.crudgame.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.gft.crudgame.model.Game;
import br.com.gft.crudgame.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository games;

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagens";

	public List<Game> listAll() {
		return games.findAll();
	}

	public Game get(Long id) {

		return games.findById(id).get();
	}

	public void delete(Long id) {
		games.deleteById(id);
	}

	public void UploadFile(MultipartFile file) {

		try {

			Path filename = Paths.get(uploadDirectory, file.getOriginalFilename());
			Files.write(filename, file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvar(Game game, MultipartFile file) {

		String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
		this.UploadFile(file);
		game.setFoto(nameFile);
		games.save(game);
	}

	public void editar(Long id, Game game, MultipartFile file) {

		if (file.getSize() == 0) {
			Game jogos = this.get(id);
			game.setFoto(jogos.getFoto());
		} else {
			String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
			game.setFoto(nameFile);
			this.UploadFile(file);
		}

		games.save(game);

	}

}
