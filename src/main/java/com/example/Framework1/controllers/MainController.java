/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.controllers;

import com.example.Framework1.models.Department;
import com.example.Framework1.models.Employee;
import com.example.Framework1.repositories.DepartmentRepository;
import com.example.Framework1.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Fajar
 */
@Controller
public class MainController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/department")
    public String main(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", new Department());
        return "department";
    }

    @RequestMapping("/employee")
    public String mainE(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    @PostMapping("department/save")
    public String save(Department department) {
        departmentRepository.save(department);
        return "redirect:/department";
    }

    @PostMapping("employee/save")
    public String saveE(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("department/get/{id}")
    public String getById(Model model, @PathVariable("id") String id) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id));
        return "department";
    }

    @GetMapping("delete/{id}")
    public String deleteById(Model model, @PathVariable("id") String id) {
        System.out.println("id : " + id);
        departmentRepository.deleteById(id);
        return "redirect:/department";
    }

    @GetMapping("edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        departmentRepository.getOne(id);
        // set employee as a model attribute to pre-populate the form
        modelAndView.addObject("department", departmentRepository.getOne(id));
        modelAndView.setViewName("updateDepartment");
        return modelAndView;
    }

    @PostMapping("edit/update")
    public String updateDepartment(Department department) {
        departmentRepository.save(department);
        return "redirect:/department";
    }

    @GetMapping("/delete_employee/{id}")
    public String deleteEById(Model model, @PathVariable("id") String id) {
        System.out.println("id : " + id);
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit_employee/{id}")
    public ModelAndView showEUpdateForm(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        employeeRepository.getOne(id);
        // set employee as a model attribute to pre-populate the form
        modelAndView.addObject("employee", employeeRepository.getOne(id));
        modelAndView.setViewName("updateEmployee");
        return modelAndView;
    }

    @PostMapping("/edit_employee/update")
    public String updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("employee/get/{id}")
    public String getEById(Model model, @PathVariable("id") String id) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id));
        return "employee";
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Coba Cetak");
//        //Save 1(Setter)
//        Department dep = new Department();
//        dep.setId("ADD3");
//        dep.setName("Application Development Department 3");
//        departmentRepository.save(dep);
//
//        //Save 2 (Constractor) (Insert/Update, Asalkan Id Sama)
//        //Department dep1 = new Department("ADD4", "Application Development Department 4 (Test)");
//        //departmentRepository.save(dep1);
//        departmentRepository.save(new Department("ADD4", "Application Development Department 4 (Test)"));
//        departmentRepository.delete(new Department("ADD4"));

        //List<Department> departments = departmentRepository.findAll();
        //for (Department department : departments) {
//        departmentRepository.findAll().stream().map((department) -> {
//            System.out.println("Department Id : " + department.getId());
//            return department;
//        }).forEachOrdered((department) -> {
//            System.out.println("Department Name : " + department.getName());
//        });
        return "employees";
    }

}
