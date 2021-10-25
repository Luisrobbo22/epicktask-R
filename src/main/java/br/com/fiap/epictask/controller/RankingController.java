package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @GetMapping()
    public ModelAndView listTaskFinalized(RedirectAttributes redirect){
        ModelAndView modelAndView = new ModelAndView("ranking");
        final List<User> users = service.findByPoints();
        if (users.size() == 0)
            redirect.addFlashAttribute("message", messages.getMessage("message.success.deleteduser", null, LocaleContextHolder.getLocale()) );
        modelAndView.addObject("users", users);
        return modelAndView;

    }
}
