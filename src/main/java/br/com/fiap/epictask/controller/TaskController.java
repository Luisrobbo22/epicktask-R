package br.com.fiap.epictask.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.fiap.epictask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.exception.NotAllowedException;
import br.com.fiap.epictask.exception.TaskNotFoundException;
import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private MessageSource messages;

	@Autowired
	private TaskService service;

	
	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("tasks");
		List<Task> tasks = service.findAll();
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}
	
	@RequestMapping("new")
	public String create(Task task) {
		return "task-form";
	}
	
	@PostMapping
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) return "task-form";
		service.create(task);
		redirect.addFlashAttribute("message", messages.getMessage("message.success.newtask", null, LocaleContextHolder.getLocale()) );
		return "redirect:/task";
	}
	
	@PostMapping("/hold/{id}")
	public String hold(@PathVariable Long id, Authentication auth) {
		final Task task = service.buildTaskHold(id, auth);
		service.create(task);
		return "redirect:/task";
	}
	
	@PostMapping("/release/{id}")
	public String release(@PathVariable Long id, Authentication auth) {
		final Task task = service.buildTaskRelease(id, auth);
		service.create(task);
		return "redirect:/task";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
