package com.cg.controller;


import com.cg.model.Customer;
import com.cg.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String showHome(Model model){
//        List<Customer> customers = customerService.findAll();
        List<Customer> customers = customerService.findAllByDeletedFalse();
        model.addAttribute("customers",customers);
        return "home";
    }

    @GetMapping("/create-customer")
    public String showFromCreate(Model model){
        model.addAttribute("customer",new Customer());
        return "create-customer";
    }
    @GetMapping("/edit/{idCustomer}")
    private String showEditForm(@PathVariable Long idCustomer, Model model){
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            model.addAttribute("customer", customer);
            return "edit-customer";
        }else{
            return "404";
        }
    }
    @GetMapping("/suspension/{idCustomer}")
    private String showDeletedForm(@PathVariable Long idCustomer,Model model){
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        if (customerOptional.isPresent()){
            model.addAttribute("customer",customerOptional.get());
        } else {
            return "404";
        }
        return "suspension";
    }

    @PostMapping("suspension/{idCustomer}")
    private String DeleteForm(@PathVariable Long idCustomer){
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        if (customerOptional.isPresent()){
            customerOptional.get().setDeleted(true);
            customerService.save(customerOptional.get());
        } else {
            return "404";
        }
        return "redirect:/customer";
    }

    @PostMapping("/edit/{id}")
    private String editCustomer(@PathVariable("id") long id,
                                RedirectAttributes model,
                                @Validated @ModelAttribute("customer") Customer customer,
                                BindingResult bindingResult) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if(customerOptional.isPresent()){
            if(!bindingResult.hasFieldErrors()){
                customer.setBalance(customerOptional.get().getBalance());
                customerService.save(customer);
                model.addFlashAttribute("message","Update Customer Successfully");
                return "redirect:/customer/edit/"+id;
            }else{
                model.addFlashAttribute("error", "Error");
                return "redirect:/customer/edit/"+id;
            }
        }else{
            model.addFlashAttribute("error","This customer is not exist");
            return "redirect:/customer/edit/"+id;
        }
    }


    @PostMapping("create-customer/action")
    public String createCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult,Model model){
        if (!bindingResult.hasErrors()){
            List<Customer> customersEmail = customerService.findAllByEmail(customer.getEmail());
            List<Customer> customersPhone  =customerService.findAllByPhone(customer.getPhone());
            if (customersEmail.size() ==0 && customersPhone.size()==0){
                customer.setBalance(BigDecimal.ZERO);
                customerService.save(customer);
                model.addAttribute("message","success");
            } else {
                model.addAttribute("message","Số điện thoại hoặc email đã tồn tại");
                model.addAttribute("error", "Create new customer failed");
            }
        } else {
            model.addAttribute("error", "Create new customer failed");
            model.addAttribute("customer",customer);
        }
        return "create-customer";
    }


}
