package org.ko.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ko.mvc.annotation.Autowired;
import org.ko.mvc.annotation.RequestMapping;
import org.ko.mvc.annotation.RestController;
import org.ko.mvc.service.CatService;

@RestController
@RequestMapping("cat")  
public class CatController {
	
	@Autowired("catService") CatService catService;  
  
    @RequestMapping("insert")  
    public String insert(HttpServletRequest request, HttpServletResponse response) {  
    	catService.insert();
        return "insert";  
    }  
  
    @RequestMapping("delete")  
    public String delete(HttpServletRequest request, HttpServletResponse response) {  
    	catService.remove();
        return "delete";  
    }  
  
    @RequestMapping("update")  
    public String update(HttpServletRequest request, HttpServletResponse response) {  
    	catService.update();
        return "update";  
    }  
  
    @RequestMapping("select")  
    public String select(HttpServletRequest request, HttpServletResponse response) {  
    	catService.find();
        return "select";  
    }  
}
