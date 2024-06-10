package com.example.ProjectCC.controller;

import com.example.ProjectCC.service.ChatService;
import com.example.ProjectCC.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ChatService chatService;

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("login_id", session.getAttribute("login_id"));
        model.addAttribute("login_user", session.getAttribute("login_user"));
        model.addAttribute("chatRoomMsg", chatService.findChat((String) session.getAttribute("login_id")));
        model.addAttribute("userNames", chatService.findChatName((String) session.getAttribute("login_id")));

        return "profile";
    }

    @PostMapping("/saveImage")
    @Transactional
    public String saveImage(@RequestParam(value="name") String name, @RequestParam(value="status") String status,
                            @RequestParam(value="image") MultipartFile image, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("login_id");
        String path = "userImg/" + userId + ".png";

        profileService.saveImage(image, path, userId, name, status);

        model.addAttribute("login_id", userId);
        model.addAttribute("chatRoomMsg", chatService.findChat((String) session.getAttribute("login_id")));
        model.addAttribute("userNames", chatService.findChatName((String) session.getAttribute("login_id")));

        if(!status.isBlank()) {
            model.addAttribute("status", status);
        }
        if (!name.isBlank()) {
            model.addAttribute("login_user", name);
        } else {
            model.addAttribute("login_user", session.getAttribute("login_user"));
        }

        return "home";
    }

    @GetMapping("/tag")
    public String tag(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("login_id");
        model.addAttribute("login_id", session.getAttribute("login_id"));
        model.addAttribute("login_user", session.getAttribute("login_user"));
        model.addAttribute("chatRoomMsg", chatService.findChat(id));
        model.addAttribute("userNames", chatService.findChatName(id));
        model.addAttribute("tags", profileService.findTagById(id));

        return "tag";
    }

    @PostMapping("/tag")
    @ResponseBody
    public void saveTag(@RequestBody String tag, HttpServletRequest request) {
        HttpSession session = request.getSession();
        profileService.saveTag((String) session.getAttribute("login_id"), tag);
    }
}
