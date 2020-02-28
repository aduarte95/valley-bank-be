package com.backend.bank.controller;

import com.backend.bank.model.AccountModel;
import com.backend.bank.model.FavoriteModel;
import com.backend.bank.model.UserModel;
import com.backend.bank.service.UserService;
import com.backend.bank.service.impl.AccountServiceImpl;
import com.backend.bank.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorite")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class FavoriteController {
    FavoriteServiceImpl favoriteService;
    UserService userService;

    @Value("${app.properties.secret}")
    private String secret;

    public FavoriteController(FavoriteServiceImpl favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createFavorite(@RequestBody FavoriteModel favorite) {
        // if(TokenUtils.verifyToken(token, secret)) {
        if(this.favoriteService.save(favorite) != null) {
            return new ResponseEntity<>("100", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("200", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @GetMapping(value = "/{favoriteId}")
    public ResponseEntity<Object> getFavorite(@PathVariable String favoriteId /*,@RequestHeader String token*/) {
        // if(TokenUtils.verifyToken(token)) {
        FavoriteModel favoriteModel = this.favoriteService.findOneById(Integer.parseInt(favoriteId));

        if(favoriteModel != null) {
            return new ResponseEntity<>(favoriteModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("201", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        /*} else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }*/
    }

    @PutMapping
    public ResponseEntity<Object> updateFavorite( /*,@RequestHeader String token,*/ @RequestBody FavoriteModel favoriteModel) {
        // if(TokenUtils.verifyToken(token)) {
        FavoriteModel response = this.favoriteService.update(favoriteModel);

            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("202", HttpStatus.INTERNAL_SERVER_ERROR);

            }
            //}
    }

    @DeleteMapping(value = "/{favoriteId}")
    public ResponseEntity<String> deleteFavorite(@PathVariable String favoriteId/*, @RequestHeader String token*/, @RequestBody UserModel user) {
        // if(TokenUtils.verifyToken(token)) {
        if (this.userService.verifyPassword(user)) {
            if (this.favoriteService.deleteFavorite(Integer.parseInt(favoriteId))) {
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
