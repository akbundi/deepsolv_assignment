package service;

import com.deepsolv.linkedininsights.model.Page;
import com.deepsolv.linkedininsights.repository.PageRepository;
import com.deepsolv.linkedininsights.utils.ScraperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkedInService {

    @Autowired
    private PageRepository pageRepository;

    public Page getPage(String pageId) {
        Page page = pageRepository.findByLinkedinId(pageId);
        if (page == null) {
            page = ScraperUtil.scrapePage(pageId);
            pageRepository.save(page);
        }
        return page;
    }

    public List<Page> getPagesByFollowerCountRange(int minFollowers, int maxFollowers) {
        return pageRepository.findByTotalFollowersBetween(minFollowers, maxFollowers);
    }

    public List<Page> getPagesByName(String name) {
        return pageRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Page> getPagesByIndustry(String industry) {
        return pageRepository.findByIndustry(industry);
    }

    public List<Post> getRecentPosts(String pageId) {
        Page page = pageRepository.findByLinkedinId(pageId);
        if (page != null) {
            return page.getPosts().stream().limit(15).toList();
        }
        return new ArrayList<>();
    }
}