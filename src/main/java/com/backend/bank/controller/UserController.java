package com.backend.bank.controller;

import com.backend.bank.model.UserModel;
import com.backend.bank.service.impl.UserServiceImpl;
import com.backend.bank.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {
    UserServiceImpl userService;

    @Value("${app.properties.secret}")
    private String secret;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/authenticate", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Object> getToken(UserModel user) {
        UserModel response = this.userService.verifyUser(user);

        if (response != null) {
            String token = TokenUtils.createToken(response.getUsername(), secret);
            response.setToken(token);

            if(!token.equals("")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("101", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> createUser(@RequestBody UserModel user) {
            if(this.userService.save(user) != null) {
                return new ResponseEntity<>("100", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("200", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping(value = "/verify-username")
    public ResponseEntity<Boolean> existsUsername(@RequestParam String username) {
        return new ResponseEntity<>(this.userService.existsByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/verify-email")
    public ResponseEntity<Boolean> existsEmail(@RequestParam String email) {
        return new ResponseEntity<>(this.userService.existsByEmail(email), HttpStatus.OK);
    }

    @GetMapping(value = "/verify-cellphone")
    public ResponseEntity<Boolean> existsCellphone(@RequestParam String cellphone) {
        return new ResponseEntity<>(this.userService.existsByCellphone(Integer.parseInt(cellphone)), HttpStatus.OK);
    }

    @GetMapping(value = "/verify-id-number")
    public ResponseEntity<Boolean> existsIdNumber(@RequestParam String idNumber) {
        return new ResponseEntity<>(this.userService.existsByIdNumber(Integer.parseInt(idNumber)), HttpStatus.OK);
    }

    @PostMapping(value = "/verify-password")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody UserModel user) {
        return new ResponseEntity<>(this.userService.verifyPassword(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId/*, @RequestHeader String token*/, @RequestBody UserModel user) {
        // if(TokenUtils.verifyToken(token)) {
        if(this.userService.verifyPassword(user)) {
            if (this.userService.deleteUser(Integer.parseInt(userId))) {
                return new ResponseEntity<>("100", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("201", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("101", HttpStatus.FORBIDDEN);

        }
        //}
    }

    @PutMapping
    public ResponseEntity<String> updateUser( /*,@RequestHeader String token,*/ @RequestBody UserModel user) {
        // if(TokenUtils.verifyToken(token)) {
        String response = this.userService.update(user);
            if(response.equals("SUCCESS")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        //}
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable String userId /*,@RequestHeader String token, @RequestBody User user*/) {
        // if(TokenUtils.verifyToken(token)) {
        UserModel userModel = this.userService.findUserById(Integer.parseInt(userId));

        if(userModel != null) {
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("201", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }
}
