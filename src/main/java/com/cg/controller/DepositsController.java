package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposits;
import com.cg.service.customer.CustomerService;
import com.cg.service.customer.DepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositsController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DepositsService depositsService;
    @GetMapping("/{idCustomer}")
    private String showFormDeposits(@PathVariable Long idCustomer, Model model){
        Optional<Customer> customer = customerService.findById(idCustomer);
        if (!customer.isPresent()){
            return "/404";
        }
        Deposits deposits = new Deposits();
        deposits.setCustomer(customer.get());
        model.addAttribute("deposits",deposits);
        return "deposit";
    }

    @PostMapping("/{idCustomer}")
    private String deposits(@PathVariable Long idCustomer, @ModelAttribute Deposits deposits, RedirectAttributes redirectAttributes){
        try {
        Optional<Customer> customer = customerService.findById(idCustomer);
        if (customer.isPresent()){
            String message = depositsService.deposit(idCustomer,deposits.getTransactionAmount());
            deposits.setCustomer(customer.get());
            redirectAttributes.addFlashAttribute("deposits",deposits);
            redirectAttributes.addFlashAttribute("message","success");
            if(message.equals("Khong du tien")){
                redirectAttributes.addFlashAttribute("message", message);
            }else{
                redirectAttributes.addFlashAttribute("message", "success");
            }
            return "redirect:/deposit/"+idCustomer;
        } else {
            return "404";
        }} catch (Exception e){
            return "404";
        }
}}
