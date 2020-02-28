package com.backend.bank.controller;

import com.backend.bank.dto.AccountDTO;
import com.backend.bank.model.AccountModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.service.UserService;
import com.backend.bank.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class AccountController {
    AccountServiceImpl accountService;
    UserService userService;

    @Value("${app.properties.secret}")
    private String secret;

    public AccountController(AccountServiceImpl accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountModel account) {
        // if(TokenUtils.verifyToken(token, secret)) {
        if(this.accountService.save(account) != null) {
            return new ResponseEntity<>("100", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("200", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<Object> getAccount(@PathVariable String accountId /*,@RequestHeader String token*/) {
        // if(TokenUtils.verifyToken(token)) {
        AccountModel accountModel = this.accountService.findOneById(Integer.parseInt(accountId));

        if(accountModel != null) {
            return new ResponseEntity<>(accountModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("201", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @PutMapping
    public ResponseEntity<Object> updateAccount( /*,@RequestHeader String token,*/ @RequestBody AccountModel accountModel) {
        // if(TokenUtils.verifyToken(token)) {
        AccountModel response = this.accountService.update(accountModel);

        if (response != null) {
            return new ResponseEntity<>(accountModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("202", HttpStatus.INTERNAL_SERVER_ERROR);

        }
            //}
    }

    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<String> deleteUser(@PathVariable String accountId/*, @RequestHeader String token*/, @RequestBody UserModel user) {
        // if(TokenUtils.verifyToken(token)) {
        if (this.userService.verifyPassword(user)) {
            if (this.accountService.deleteAccount(Integer.parseInt(accountId))) {
                return new ResponseEntity<>("100", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("201", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("101", HttpStatus.FORBIDDEN);
        }
        //}
    }

    @GetMapping(value = "/get-account-by-number")
    public ResponseEntity<AccountModel> existsAccount(@RequestParam String accountNumber) {
        return new ResponseEntity<>(this.accountService.accountExists(Long.parseLong(accountNumber)), HttpStatus.OK);
    }

}
