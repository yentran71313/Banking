package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Transfers;
import com.cg.service.customer.CustomerService;
import com.cg.service.customer.TransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class TransfersController {
    @Autowired
    CustomerService customerService;

    @Autowired
    TransfersService transfersService;
    @GetMapping("/transfer/history")
    private String showHistory(Model model){
        List<Transfers> transfers = transfersService.findAll();
        model.addAttribute("transfers",transfers);
        return "transfer-history";
    }
    @GetMapping("/transfer/{idSender}")
    private String showTransferForm(@PathVariable Long idSender, Model model){
        Optional<Customer> customerOptional = customerService.findById(idSender);
        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            Transfers transfers = new Transfers();
            transfers.setSender(customer);
            model.addAttribute("transfers",transfers);
            List<Customer> recipients = customerService.findAllByIdNotAndDeletedFalse(idSender);
            model.addAttribute("recipients",recipients);
            return "transfer";
        } else {
            return "404";
        }
    }
    @PostMapping("/transfer/{idSender}")
    private String transfers(@ModelAttribute("transfers") Transfers transfers, RedirectAttributes redirectAttributes, @PathVariable Long idSender, @RequestParam("recipientId") Long recipientId){
        BigDecimal transferAmount = transfers.getTransferAmount();
        Long idRecipient = recipientId;
        String message = transfersService.transfer(idSender,idRecipient,transferAmount);
        if(!message.equals("success")){
            redirectAttributes.addFlashAttribute("message", message);
        }else{
            redirectAttributes.addFlashAttribute("message", "success");
        }
        return "redirect:/transfer/"+ idSender;
    }
}

