package edu.cetys.springlabs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cetys.springlabs.dto.form.UserCredentialForm;
import edu.cetys.springlabs.enums.UserRole;
import edu.cetys.springlabs.model.Token;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.repository.TokenRepository;
import edu.cetys.springlabs.service.TokenService;
import edu.cetys.springlabs.service.UserService;


@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger("LoginController.class");
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenService tokenService;
	
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	
	@GetMapping("/forgot-password")
	public String forgotPasswordForm(Model model) {
	
		
		return "forgot-password";
	}
	
	@PostMapping("/password-reset")
	public String passwordReset(@ModelAttribute UserCredentialForm userCredential, RedirectAttributes redirectAttributes, Model model) {
		
		// 1.- Verifique que el usuario exista y extraer su user_id
		// 2.- Generar una cadena aleatoria e insertarlo en el campo token de la base de datos
		// 3.- Imprimir email y token de usuario en consola
		// 4.- Regresar confirmacion al usuario
		
		//System.out.println("Email: " + userCredential.getEmail());
		
		try {
			User dbUser = userService.findByEmail(userCredential.getEmail());
		
		
			if (dbUser != null) {
				Token newToken = tokenService.addToken(dbUser);
				
				logger.info("TODO: Send email with password reset to: " + userCredential.getEmail() + " with token  " + newToken.getToken());
			
				// TODO: send email
			
			} 
		} catch(UsernameNotFoundException e) {	
			logger.warn("A user is trying to request a password reset for an invalid email: " + userCredential.getEmail());
		}
		
		redirectAttributes.addFlashAttribute("confirmationMessage",  "Thank you, you will shortly receive an email with instructions on how to reset your password.");
		
		return "redirect:/forgot-password";
	}
	
}
