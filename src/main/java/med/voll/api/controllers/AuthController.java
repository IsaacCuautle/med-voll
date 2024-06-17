package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.JwtData;
import med.voll.api.dto.user.UserAuthData;
import med.voll.api.models.User;
import med.voll.api.services.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuth(@Valid @RequestBody UserAuthData userAuthData)
    {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthData.login(), userAuthData.password());
        var  authUser = authenticationManager.authenticate(authToken);
        var jwt = tokenService.tokenGenerate((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtData(jwt));
    }

}
