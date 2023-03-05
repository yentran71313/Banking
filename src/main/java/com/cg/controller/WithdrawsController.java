package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposits;
import com.cg.model.Withdraws;
import com.cg.repository.WithdrawsRepository;
import com.cg.service.customer.CustomerService;
import com.cg.service.customer.DepositsService;
import com.cg.service.customer.WithdrawsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/withdraw")
public class WithdrawsController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WithdrawsService withdrawsService;
    @Autowired
    private WithdrawsRepository withdrawsRepository;
    @GetMapping("/{idCustomer}")
    private String showFormWithdraws(@PathVariable Long idCustomer, Model model){
        Optional<Customer> customer = customerService.findById(idCustomer);
        if (!customer.isPresent()){
            return "/404";
        }
        Withdraws withdraws = new Withdraws();
        withdraws.setCustomer(customer.get());
        model.addAttribute("withdraws",withdraws);
        return "withdraw";
    }

    @PostMapping("/{idCustomer}")
    private String withdraws(@PathVariable Long idCustomer, @ModelAttribute Withdraws withdraws, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        Optional<Customer> customer = customerService.findById(idCustomer);
        if (customer.isPresent()){
            String message = withdrawsService.withdraw(idCustomer,withdraws.getTransactionAmount());
            withdraws.setCustomer(customer.get());
            redirectAttributes.addFlashAttribute("withdraws",withdraws);
            redirectAttributes.addFlashAttribute("message","success");
            if(message.equals("Khong du tien")){
                redirectAttributes.addFlashAttribute("message", message);
            }else{
                redirectAttributes.addFlashAttribute("message", "success");
            }
            return "redirect:/withdraw/"+idCustomer;
        } else {
            return "404";
        }
    }
}
