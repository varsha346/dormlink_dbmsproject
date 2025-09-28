package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Services.NoticeService;
import com.Hostel_Management.hostel.models.Notice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices") // Base URL for all notice routes
public class NoticeRoutes {

    private final NoticeService noticeService;

    // Constructor injection (preferred over @Autowired)
    public NoticeRoutes(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // ---------------- CREATE NOTICE ----------------
    @PostMapping("/create")
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice) {
        Notice savedNotice = noticeService.createNotice(notice);
        return ResponseEntity.ok(savedNotice);
    }

    // ---------------- GET ALL NOTICES ----------------
    @GetMapping("/all")
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    // ---------------- DELETE NOTICE ----------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok("Notice deleted successfully");
    }

    // ---------------- UPDATE NOTICE ----------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Notice> updateNotice(
            @PathVariable Long id,
            @RequestBody Notice noticeDetails) {

        Notice existingNotice = noticeService.getNoticeById(id);

        existingNotice.setTitle(noticeDetails.getTitle());
        existingNotice.setDescription(noticeDetails.getDescription());
        // createdAt is left as is (or you can update if needed)

        Notice updatedNotice = noticeService.createNotice(existingNotice); // save again
        return ResponseEntity.ok(updatedNotice);
    }
    // ---------------- GET SINGLE NOTICE ----------------
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
        Notice notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok(notice);
    }

}

