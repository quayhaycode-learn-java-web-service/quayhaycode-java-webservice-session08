package com.rikkei.baith4.controller;

import com.rikkei.baith4.service.LessonService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
@Validated // Bắt buộc phải có để kích hoạt Validation cho RequestParam
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/watch")
    public ResponseEntity<String> watchLesson(
            @RequestHeader("X-User") String username,
            @RequestParam @Min(value = 1, message = "ID bài giảng không hợp lệ. Phải lớn hơn hoặc bằng 1.") Long lessonId) {

        String videoUrl = lessonService.watchLesson(lessonId, username);
        return ResponseEntity.ok(videoUrl);
    }
}