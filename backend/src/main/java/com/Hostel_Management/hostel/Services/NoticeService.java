package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Repository.NoticeRepository;
import com.Hostel_Management.hostel.models.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // Create a notice
    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    // Get all notices
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // Delete a notice by ID
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    // Optional: Get a single notice by ID
    public Notice getNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found with id: " + id));
    }
}

