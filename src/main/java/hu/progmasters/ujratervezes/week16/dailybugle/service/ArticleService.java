package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Article;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.RatingLister;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleLister> listArticles(){
        return articleRepository.listArticles();
    }

    public boolean saveArticle(ArticleCreateData data){
        return articleRepository.saveArticle(data);
    }

    public Article findArticleById(int id){
        return articleRepository.findArticleById(id);
    }

    public boolean deleteArticle(int id){
        return articleRepository.deleteArticle(id);
    }

    public boolean modifyArticle(int id, Article article){
        return articleRepository.modifyArticle(id, article);
    }

    public boolean uploader(String path){
        return articleRepository.uploader(path);
    }

    public List<ArticleLister> getFresh(){
        return articleRepository.getFresh();
    }

    public List<ArticleLister> getTopTen(){
        List <RatingLister> ratingListers = articleRepository.getTopTen();
        return articleRepository.listTopTen(ratingListers);

    }

    public List<ArticleLister> getBestOfFresh(){
        List<RatingLister> ratingListers = articleRepository.getBestOfTen();
        return articleRepository.listBestOfTen(ratingListers);
    }
}
