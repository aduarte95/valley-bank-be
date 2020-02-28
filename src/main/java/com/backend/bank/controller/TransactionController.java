package com.backend.bank.controller;

import com.backend.bank.model.TransactionModel;
import com.backend.bank.model.TransactionModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.service.UserService;
import com.backend.bank.service.impl.SavingServiceImpl;
import com.backend.bank.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TransactionController {
    TransactionServiceImpl transactionService;
    UserService userService;

    @Value("${app.properties.secret}")
    private String secret;

    public TransactionController(TransactionServiceImpl transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionModel transactionModel) {
        transactionModel.setDate(new Date());
        if(this.transactionService.save(transactionModel) != null) {
            return new ResponseEntity<>("100", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("200", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<Object> getTransaction(@PathVariable String transactionId) {
        TransactionModel transactionModel = this.transactionService.findOneById(Integer.parseInt(transactionId));

        if(transactionModel != null) {
            return new ResponseEntity<>(transactionModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("201", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getTransaction() {

        List<TransactionModel> transactionModels = this.transactionService.findAll();

        return new ResponseEntity<>(transactionModels, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Object> updateTransaction( /*,@RequestHeader String token,*/ @RequestBody TransactionModel transactionModel) {

        TransactionModel response = this.transactionService.update(transactionModel);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("202", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{savingId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String savingId/*, @RequestHeader String token*/, @RequestBody UserModel user) {

        if (this.userService.verifyPassword(user)) {
            if (this.transactionService.delete(Integer.parseInt(savingId))) {
                return new ResponseEntity<>("100", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("201", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("101", HttpStatus.FORBIDDEN);
        }
    }
}
