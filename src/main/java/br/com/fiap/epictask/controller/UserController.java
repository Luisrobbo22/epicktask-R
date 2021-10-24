package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.dto.UserDTO;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @GetMapping
    public ModelAndView index(@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("users");
        Page<User> users = userService.findAll(pageable);

        modelAndView.addObject("users", users);
        System.out.println(users);
        return modelAndView;
    }

    @RequestMapping("new")
    public String create(User user) {
        return "user-form";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        userService.create(user);

        if (result.hasErrors())
            return "user-form";

        userService.configurePassword(user);

        redirect.addFlashAttribute("message",
                messages.getMessage("message.success.newuser",
                        null,
                        LocaleContextHolder.getLocale()));
        return "redirect:user";
    }


    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> users = userService.remove(id);

		modelAndView.addObject("users", users);

		return modelAndView;
}

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("change-users");
        Optional<User> optionalUser = userService.findById(id);

        User user = optionalUser.get();
        redirect.addFlashAttribute("message", messages.getMessage("message.success.rankingnotexist", null, LocaleContextHolder.getLocale()) );

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView update(@Valid User user) {
        ModelAndView modelAndView = new ModelAndView("users");
        Optional<User> userOptional = userService.findByEmail(user);

        User newuser = userOptional.get();
        List<User> users = userService.update(newuser, user);

        modelAndView.addObject("users", users);

        return modelAndView;
    }

}
