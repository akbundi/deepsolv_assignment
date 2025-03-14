package utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScraperUtil {

    public static Page scrapePage(String pageId) {
        String url = "https://www.linkedin.com/company/" + pageId;
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Failed to scrape page: " + pageId, e);
        }

        Page page = new Page();
        page.setName(doc.select("h1.org-top-card__title").text());
        page.setUrl(url);
        page.setLinkedinId(pageId);
        page.setProfilePicture(doc.select("img.org-top-card__primary-content").attr("src"));
        page.setDescription(doc.select("p.break-words").text());
        page.setWebsite(doc.select("a.org-top-card__primary-content").attr("href"));
        page.setIndustry(doc.select("dt:contains(Industry) + dd").text());
        page.setTotalFollowers(Integer.parseInt(doc.select("span.org-top-card__followers-count").text().replace(",", "")));
        page.setHeadCount(Integer.parseInt(doc.select("span.org-top-card__employees-count-range").text().replace(",", "")));
        page.setSpecialities(doc.select("p.org-top-card__specialities").text());

        List<Post> posts = new ArrayList<>();
        Elements postElements = doc.select("div.org-update-card");
        for (Element postElement : postElements) {
            Post post = new Post();
            post.setContent(postElement.select("div.org-update-card__content").text());
            post.setComments(postElement.select("span.org-update-card__comments-count").text());
            post.setLikes(Integer.parseInt(postElement.select("span.org-update-card__likes-count").text().replace(",", "")));
            post.setShares(Integer.parseInt(postElement.select("span.org-update-card__shares-count").text().replace(",", "")));
            posts.add(post);
        }
        page.setPosts(posts);

        List<User> users = new ArrayList<>();
        Elements userElements = doc.select("div.org-people-profile-card");
        for (Element userElement : userElements) {
            User user = new User();
            user.setName(userElement.select("span.org-people-profile-card__name").text());
            user.setProfilePicture(userElement.select("img.org-people-profile-card__profile-picture").attr("src"));
            users.add(user);
        }
        page.setUsers(users);

        return page;
    }
}
