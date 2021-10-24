package br.com.fiap.epictask.controller;

import java.util.List;

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

import br.com.fiap.epictask.model.Task;

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
		List<Task> tasks = service.listTaskNotFinalized();
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

	@GetMapping("/finalized")
	public ModelAndView listTaskFinalized(RedirectAttributes redirect){
		ModelAndView modelAndView = new ModelAndView("task-finalized");
		final List<Task> tasks = service.listTaskFinalized();
		if (tasks.size() == 0)
			redirect.addFlashAttribute("message", messages.getMessage("message.success.deleteduser", null, LocaleContextHolder.getLocale()) );
		modelAndView.addObject("tasks", tasks);
		return modelAndView;

	}

}
