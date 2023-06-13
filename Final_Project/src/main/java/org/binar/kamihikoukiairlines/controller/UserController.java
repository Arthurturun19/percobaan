package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.Exception.UserNotFoundException;
import org.binar.kamihikoukiairlines.dto.EditProfileRequest;
import org.binar.kamihikoukiairlines.model.Users;
import org.binar.kamihikoukiairlines.response.MessageResponse;
import org.binar.kamihikoukiairlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "User Controller | contains : Get by Id, Update profile user, Change Password, Delete user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserById/{userId}")
//    @PreAuthorize("hasAnyRole('BUYER', 'ADMIN')")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "userId") Long id) {
        Optional<Users> userData = userService.getUserById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{userId}")
//    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<String> updateUser(@PathVariable(value = "userId") Long id, @RequestBody EditProfileRequest user){
        userService.updateUser(id,user);
        return new ResponseEntity<>("Data berhasil di update", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
//    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam("email") String email,
                                            @RequestParam("currentPassword") String currentPassword,
                                            @RequestParam("newPassword") String newPassword) {
        try {
            userService.changePassword(email, currentPassword, newPassword);
            return ResponseEntity.ok(new MessageResponse("Password Berhasil Dirubah!"));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Pengguna dengan email tersebut tidak ditemukan");
        }
    }
}
