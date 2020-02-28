package com.backend.bank.controller;

import com.backend.bank.model.AccountModel;
import com.backend.bank.model.FavoriteModel;
import com.backend.bank.model.SavingModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.service.UserService;
import com.backend.bank.service.impl.SavingServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/saving")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SavingController {
    SavingServiceImpl savingService;
    UserService userService;

    @Value("${app.properties.secret}")
    private String secret;

    public SavingController(SavingServiceImpl savingService, UserService userService) {
        this.savingService = savingService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createSaving(@RequestBody SavingModel savingModel) {
        // if(TokenUtils.verifyToken(token, secret)) {
        if(this.savingService.save(savingModel) != null) {
            return new ResponseEntity<>("100", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("200", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @GetMapping(value = "/{savingId}")
    public ResponseEntity<Object> getSaving(@PathVariable String savingId /*,@RequestHeader String token*/) {
        // if(TokenUtils.verifyToken(token)) {
        SavingModel savingModel = this.savingService.findOneById(Integer.parseInt(savingId));

        if(savingModel != null) {
            return new ResponseEntity<>(savingModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("201", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @PutMapping()
    public ResponseEntity<Object> updateSaving( /*,@RequestHeader String token,*/ @RequestBody SavingModel savingModel) {
        // if(TokenUtils.verifyToken(token)) {
        SavingModel response = this.savingService.update(savingModel);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("202", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        //}
    }

    @DeleteMapping(value = "/{savingId}")
    public ResponseEntity<String> deleteSaving(@PathVariable String savingId/*, @RequestHeader String token*/, @RequestBody UserModel user) {
        // if(TokenUtils.verifyToken(token)) {
        if (this.userService.verifyPassword(user)) {
            if (this.savingService.delete(Integer.parseInt(savingId))) {
                return new ResponseEntity<>("100", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("201", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("101", HttpStatus.FORBIDDEN);
        }
        //}
    }
}
