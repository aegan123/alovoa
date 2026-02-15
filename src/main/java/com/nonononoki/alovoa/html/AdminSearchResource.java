package com.nonononoki.alovoa.html;

import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.model.AlovoaException;
import com.nonononoki.alovoa.model.UserDto;
import com.nonononoki.alovoa.repo.UserRepository;
import com.nonononoki.alovoa.service.AuthService;
import com.nonononoki.alovoa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@AllArgsConstructor
public class AdminSearchResource {

    public static final String URL = "/admin-search";

    private AuthService authService;
    private UserService userService;
    private UserRepository userRepo;

    @GetMapping(URL)
    public ModelAndView search() throws AlovoaException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
            UnsupportedEncodingException {
        User user = authService.getCurrentUser(true);

        if (!user.isAdmin()) {
            throw new AlovoaException("not authorized");
        }

        userRepo.saveAndFlush(user);

        ModelAndView mav = new ModelAndView("admin-search");
        mav.addObject("user", UserDto.userToUserDto(UserDto.DtoBuilder.builder().ignoreIntention(true)
                .currentUser(user).user(user).userService(userService).build()));
        return mav;
    }
}
