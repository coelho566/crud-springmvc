package br.com.gft.crudgame.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.crudgame.model.CategoriaGame;
import br.com.gft.crudgame.model.Game;
import br.com.gft.crudgame.repository.TituloFilter;
import br.com.gft.crudgame.service.GameService;
/**
 * Camada de controller da aplicação
 * 
 * @author Leandro 
 * @version 1.0
 */
@Controller
public class GameController {

	@Autowired
	private GameService games;
	
	@RequestMapping
	public ModelAndView index(@ModelAttribute("filtro") TituloFilter filtro) {

		 List<Game> jogos = games.pequisar(filtro);
		
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaJogos", jogos);

		return mv;
	}

	@RequestMapping("/novo")
	private ModelAndView cadastrar() {
		Game jogos = new Game();
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("game", jogos);
		return mv;
	}

	@PostMapping("/salvar")
	private ModelAndView save(@RequestParam MultipartFile file, @Validated Game game, Errors errors, RedirectAttributes attributes ) {
		ModelAndView mv = new ModelAndView("cadastro");

		if (errors.hasErrors()) {
			return mv;
		}

		games.salvar(game, file);
		
		attributes.addFlashAttribute("mensagem", "Jogo cadastrado com sucesso!");

		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		
		ModelAndView mv = new ModelAndView("editar");
		Game game = games.get(id);
		mv.addObject("game", game);
		
		return mv;
	}
	
	@PostMapping("/editar/{id}")
	private ModelAndView saveEditar(@PathVariable Long id, @RequestParam MultipartFile file, @Validated Game game, Errors errors, RedirectAttributes attributes ) {
		ModelAndView mv = new ModelAndView("editar");

		if (errors.hasErrors()) {
			return mv;
		}
		
		games.editar(id, game, file);
		
		attributes.addFlashAttribute("mensagem", "Jogo editado com sucesso!");
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		games.delete(id);
		
		attributes.addFlashAttribute("mensagem", "Jogo excluido com sucesso");
		return "redirect:/";
	}

	@ModelAttribute("categoria")
	public List<CategoriaGame> todosStatus() {
		return Arrays.asList(CategoriaGame.values());
	}
}
