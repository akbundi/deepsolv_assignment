package controller;

import com.deepsolv.linkedininsights.model.Page;
import com.deepsolv.linkedininsights.model.Post;
import com.deepsolv.linkedininsights.service.LinkedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linkedin")
public class LinkedInController {

    @Autowired
    private LinkedInService linkedInService;

    @GetMapping("/page/{pageId}")
    public Page getPage(@PathVariable String pageId) {
        return linkedInService.getPage(pageId);
    }

    @GetMapping("/pages/followers")
    public List<Page> getPagesByFollowerCountRange(@RequestParam int minFollowers, @RequestParam int maxFollowers) {
        return linkedInService.getPagesByFollowerCountRange(minFollowers, maxFollowers);
    }

    @GetMapping("/pages/name")
    public List<Page> getPagesByName(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return linkedInService.getPagesByName(name);
    }

    @GetMapping("/pages/industry")
    public List<Page> getPagesByIndustry(@RequestParam String industry, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return linkedInService.getPagesByIndustry(industry);
    }

    @GetMapping("/page/{pageId}/posts")
    public List<Post> getRecentPosts(@PathVariable String pageId) {
        return linkedInService.getRecentPosts(pageId);
    }
}