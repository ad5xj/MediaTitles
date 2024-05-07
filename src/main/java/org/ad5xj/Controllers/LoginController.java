package org.ad5xj.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.ad5xj.Model.Login;
import org.ad5xj.Model.User;
import org.ad5xj.DAO.UserImplDAO;
import org.ad5xj.Service.UserService;

@Controller
public class LoginController 
{
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) 
    {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, 
    		                         HttpServletResponse response, 
    		                         @ModelAttribute("login") Login i_login) 
    {
    	User user = new User();
    	UserImplDAO userobj = new UserImplDAO();
    	
        ModelAndView mav = null;
        
        String login = i_login.getLogin();
        String pwd = i_login.getPassword();

        if ( userService.validate(login,pwd) )
        {
        	user = userobj.retrieveByLoginPW(login,pwd);
        }
        else 
        {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
            return mav;
        }

        if (null != user) 
        {
            mav = new ModelAndView("welcome");
            mav.addObject("username", user.getName());
        } 
        else 
        {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
}