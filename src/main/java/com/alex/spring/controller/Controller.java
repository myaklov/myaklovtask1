package com.alex.spring.controller;

import com.alex.spring.model.User;
import com.alex.spring.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
@org.springframework.stereotype.Controller
public class Controller {

    private final int INTPAGE=15;

  private Service service;
@Autowired(required = true)
@Qualifier(value = "Service")
    public void setService(Service service) {
        this.service = service;
    }







    @RequestMapping("/")
    public String home(){
       return "index";
   }

   @RequestMapping(value = "users", method = RequestMethod.GET)
   public  ModelAndView listUsers(Model model, Integer page){
       ModelAndView modelAndView = new ModelAndView("users");
       User user=new User();
       List<User> list=this.service.getListUsers();

       PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(list);
       pagedListHolder.setPageSize(INTPAGE);
       modelAndView.addObject("user", user);
       modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
       modelAndView.addObject("holder", pagedListHolder);

       if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

       modelAndView.addObject("page", page);
       if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
           pagedListHolder.setPage(0);
           modelAndView.addObject("users", pagedListHolder.getPageList());
       }
       else if(page <= pagedListHolder.getPageCount()) {
           pagedListHolder.setPage(page-1);
           modelAndView.addObject("users", pagedListHolder.getPageList());
       }

       return modelAndView;
   }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,@RequestParam("isadmin") String radio) {
       if(radio!=null) {
           if (radio.equals("yes")) {
               user.setAdmin(true);
           } else {
               user.setAdmin(false);
           }
       }

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(timestamp);

        if(user.getId()==0) {

            if((user.getName()!=null)&&(user.getName().length()>0)&&(user.getAge()!=null)){
            this.service.addUser(user);}


        } else {
            this.service.updateUser(user);}


        return "redirect:/users";
    }






    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.service.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
model.addAttribute("user", this.service.getUserById(id));
return "users";
    }




    @RequestMapping(value = {"/search/", "/search"})
    public ModelAndView usersearch(@RequestParam("username") String name, Model model, Integer page){

        ModelAndView modelAndView = new ModelAndView("search");
        User user=new User();
        List<User> list=this.service.findByName(name);

        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(list);
        pagedListHolder.setPageSize(INTPAGE);
        modelAndView.addObject("username", name);
        modelAndView.addObject("user", user);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
        modelAndView.addObject("holder", pagedListHolder);

        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }


        return modelAndView;
    }






}
