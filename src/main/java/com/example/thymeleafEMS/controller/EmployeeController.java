package com.example.thymeleafEMS.controller;

import com.example.thymeleafEMS.entity.Employee;
import com.example.thymeleafEMS.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployee(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees",employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    String showFormForAdd(Model model){
        model.addAttribute("employee",new Employee());
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    String showFormForUpdate(@RequestParam("employeeId")int id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "employees/employee-form";
    }
    @GetMapping("/deleteEmployee")
    String deleteEmployee(@RequestParam("employeeId") int id,Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    String save(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

}
