package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.*;
import com.example.MyComar_Back.SpringEmail.EmailInterface;
import com.example.MyComar_Back.SpringSecurity.PasswordEncoder;
import com.example.MyComar_Back.SpringSecurity.jwt.JwtUtils;
import com.example.MyComar_Back.SpringSecurity.jwt.UserDetailsImpl;
import com.example.MyComar_Back.SpringSecurity.payload.JwtResponse;
import com.example.MyComar_Back.SpringSecurity.payload.LoginRequest;
import com.example.MyComar_Back.SpringSecurity.payload.MessageResponse;
import com.example.MyComar_Back.SpringSecurity.payload.SignupRequest;
import com.example.MyComar_Back.reposotory.roleRespo;
import com.example.MyComar_Back.reposotoryInterface.AccountreposotoryInterface;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AccountController {

    private final AccountreposotoryInterface accountreposotoryInterface;
    private final PasswordEncoder encoder;
    private final EmailInterface email;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    roleRespo roleRepository;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (accountreposotoryInterface.existsByemail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        Set<String> strRoles = signUpRequest.getRole();
        Set<role> roles = new HashSet<>();
        role userRole = roleRepository.findByName(Eroles.ROLE_USER);
        roles.add(userRole);
        Account account = new Account(null, signUpRequest.getEmail(),
                encoder.bCryptPasswordEncoder().encode(signUpRequest.getPassword()), roles);
        accountreposotoryInterface.saveAccount(account);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping(value = "/GetALL_Accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok().body(accountreposotoryInterface.GetAccounts());
    }

    @PostMapping(value = ("/Create_account"))
    public ResponseEntity<Account> create_account(@Valid @RequestBody Account account) {
        return ResponseEntity.ok().body(accountreposotoryInterface.saveAccount(account));
    }

    @GetMapping(value="/Find_accountbyemail/{email}")
    public ResponseEntity<Account> Find_account_email(@PathVariable("email") String email) {
        Optional<Account> account = Optional.ofNullable(accountreposotoryInterface.getAccount(email));
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value="/Find_account/{id}")
    public ResponseEntity<Account> Find_Account(@PathVariable("id") long id) {
        Optional<Account> account = accountreposotoryInterface.FindById(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/UpdateAccount_email/{id}")
    public ResponseEntity<Account> update_email(@Valid @PathVariable("id") long id, @RequestBody String email) {
        Optional<Account> accountdata = accountreposotoryInterface.FindById(id);
        if (accountdata.isPresent()) {
            Account updatedaccount = accountdata.get();
            updatedaccount.setEmail(email);
            accountreposotoryInterface.updateAccount(updatedaccount);
            return new ResponseEntity<>(updatedaccount, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/UpdateAccount_password/{id}")
    public ResponseEntity<Account> update_password(@Valid @PathVariable("id") long id, @RequestBody String password) {
        Optional<Account> accountdata = accountreposotoryInterface.FindById(id);
        if (accountdata.isPresent()) {
            Account updatedaccount = accountdata.get();
            updatedaccount.setPassword(encoder.bCryptPasswordEncoder().encode(password));
            accountreposotoryInterface.updateAccount(updatedaccount);
            return new ResponseEntity<>(updatedaccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/Forgotpassword/{id}")
    public ResponseEntity<Account> generatepassword(@Valid @PathVariable("id") long id, @RequestBody String email) {
        String generatedpass="";
        while (generatedpass.length()<20) {
            RandomStringGenerator generatedpassword = new RandomStringGenerator.Builder().
                    selectFrom("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()).build();
            generatedpass=generatedpass.concat(generatedpassword.toString());
        }
        Optional<Account> accountdata = accountreposotoryInterface.FindById(id);
        if (accountdata.isPresent()) {
            Account updatedaccount = accountdata.get();
            updatedaccount.setPassword(encoder.bCryptPasswordEncoder().encode(generatedpass));
            accountreposotoryInterface.updateAccount(updatedaccount);
            this.email.SendEmail(email,"Forgot password","Your new password is"+ generatedpass);
            return new ResponseEntity<>(updatedaccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
