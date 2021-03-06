package hu.progmasters.ujratervezes.week16.dailybugle.controller;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Article;
import hu.progmasters.ujratervezes.week16.dailybugle.domain.Rating;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    public final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //List Articles
    @GetMapping()
    public ResponseEntity<List<ArticleLister>> listArticles(){

        return new ResponseEntity<>(articleService.listArticles(), HttpStatus.OK);
    }

    //Save Article
    @PostMapping()
    public ResponseEntity<Void> saveArticle(@RequestBody ArticleCreateData data){
        boolean success = articleService.saveArticle(data);
        if(success){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Find Article by id
    @GetMapping("/{id}")
    public ResponseEntity<Article> findArticleById(@PathVariable int id){

        Article article = articleService.findArticleById(id);
        if(article != null){
            return new ResponseEntity<>(article, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Delete Article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        if(articleService.deleteArticle(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    //Edit Article
    @PutMapping("/{id}")
    public ResponseEntity<ArticleLister> modifyArticle(@PathVariable int id, @RequestBody Article article){
        boolean success = articleService.modifyArticle(id, article);
        if(success){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/upload")
    public ResponseEntity<Void> uploadArticle(@RequestBody String path){
        boolean success = articleService.uploader(path);
        if(success){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }


    //List the 10 most recent articles
    @GetMapping("/fresh")
    public ResponseEntity<List<ArticleLister>> getFresh(){
        return new ResponseEntity<>(articleService.getFresh(), HttpStatus.OK);
    }

    //List 10 articles with best reviews
    @GetMapping("/bestof")
    public ResponseEntity<List<ArticleLister>> getTopTen(){
        return new ResponseEntity<>(articleService.getTopTen(), HttpStatus.OK);
    }

    //List 10 articles with best reviews, not older than 3 days
    @GetMapping("/bestoffresh")
    public ResponseEntity<List<ArticleLister>> getBestOfFresh() {
        return new ResponseEntity<>(articleService.getBestOfFresh(), HttpStatus.OK);
    }

    @PostMapping("/rating")
    public ResponseEntity<Void> makeRating(@RequestBody Rating rating){
        if(articleService.makeRating(rating)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


