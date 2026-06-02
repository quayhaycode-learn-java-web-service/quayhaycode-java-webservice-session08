package com.rikkei.baith4.service;

import com.rikkei.baith4.exception.AccessDeniedException;
import com.rikkei.baith4.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    // Giả lập phương thức xử lý lấy link video bài giảng
    public String watchLesson(Long lessonId, String username) {
        if (lessonId == 999) {
            throw new ResourceNotFoundException("Video bài giảng không tồn tại hoặc đã bị xóa.");
        }

        // Giả định bài giảng ID 105 yêu cầu phải mua khóa học
        boolean isFree = false;
        boolean hasPurchased = "vip_user".equals(username); 

        if (!isFree && !hasPurchased) {
            throw new AccessDeniedException("You have not purchased this course yet.");
        }

        return "https://video.elearning.com/lessons/" + lessonId;
    }
}