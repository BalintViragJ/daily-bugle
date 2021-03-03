package hu.progmasters.ujratervezes.week16.dailybugle.repository;



import hu.progmasters.ujratervezes.week16.dailybugle.domain.Article;
import hu.progmasters.ujratervezes.week16.dailybugle.domain.Journalist;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.ArticleLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.io.*;

import java.util.List;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JournalistRepository journalistRepository;

    @Autowired
    public ArticleRepository(JdbcTemplate jdbcTemplate, JournalistRepository journalistRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.journalistRepository = journalistRepository;
    }

    public List<ArticleLister> listArticles() {
        String query = "SELECT a.id, a.journalist_id, a.title, a.synopsis, a. active, j.name, " +
                "j.address, j.email, j.telephone_number FROM article a JOIN journalist " +
                "j ON a.journalist_id = j.id;";

        return jdbcTemplate.query(query, ((resultSet, i) -> {
            Journalist journalist = new Journalist();
            Article article = new Article();
            ArticleLister articleLister = new ArticleLister();

            //set journalist
            journalist.setId(resultSet.getInt("journalist_id"));
            journalist.setName(resultSet.getString("name"));
            journalist.setAddress(resultSet.getString("address"));
            journalist.setEmail(resultSet.getString("email"));
            journalist.setTelephoneNumber(resultSet.getString("telephone_number"));

            //set article
            article.setId(resultSet.getInt("id"));
            article.setJournalist(journalist);
            article.setTitle(resultSet.getString("title"));
            article.setSynopsis(resultSet.getString("synopsis"));
            article.setActive(resultSet.getBoolean("active"));

            //set article for listing
            articleLister.setJournalistName(article.getJournalist().getName());
            articleLister.setId(article.getId());
            articleLister.setSynopsis(article.getSynopsis());
            articleLister.setTitle(article.getTitle());
            if (article.isActive()) {
                return articleLister;
            }
            return null;
        }));


    }

    public boolean saveArticle(ArticleCreateData data) {
        boolean flag = false;
        String saveArticle = "INSERT INTO article (journalist_id, title, synopsis, text, created, edited, active) " +
                "VALUES (?,?,?,?, now(), now(), TRUE);";

        try {
            jdbcTemplate.update(saveArticle, data.getJournalistId(),
                    data.getTitle(), data.getSynopsis(), data.getText());
            flag = true;
        } catch (DataAccessException ex) {
            flag = false;
        }

        return flag;
    }

    public Article findArticleById(int id) {
        String query = "SELECT a.id, a.journalist_id, a.title, a.synopsis, a.text, " +
                "a.created, a.edited, a.active, j.name " +
                "FROM article a JOIN journalist j ON a.journalist_id = j.id WHERE a.id=?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, ((resultSet, i) -> {

            Article article = new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("title"),
                    resultSet.getString("synopsis"),
                    resultSet.getString("text"),
                    resultSet.getTimestamp("created"),
                    resultSet.getTimestamp("edited"),
                    resultSet.getBoolean("active")

            );

            if (article.isActive()) {
                return article;
            }
            return null;

        }));

    }

    public boolean deleteArticle(int id) {
        boolean flag = false;

        String delete = "UPDATE article SET edited = now(), active = FALSE WHERE id = ?;";
        int success = jdbcTemplate.update(delete, id);

        if (success > 0) {
            flag = true;
        }

        return flag;
    }

    public boolean modifyArticle(int id, Article article) {
        boolean flag = false;
        int updateSuccess = 0;
        String update = "UPDATE article SET ";
        if (article.getTitle() != null) {
            update += "title = ?, edited = now() WHERE id=?;";
            updateSuccess = jdbcTemplate.update(update, article.getTitle(), id);
        } else if (article.getSynopsis() != null) {
            update += "synopsis = ?, edited = now() WHERE id=?;";
            updateSuccess = jdbcTemplate.update(update, article.getSynopsis(), id);
        } else if (article.getText() != null) {
            update += "text = ?, edited = now() WHERE id=?;";
            updateSuccess = jdbcTemplate.update(update, article.getText(), id);
        }

        if (updateSuccess > 0) {
            flag = true;
        }
        return flag;

    }

    public boolean uploader(String path) {
        boolean flag = false;



        int journalistId = 0;
        String title = "";
        String synopsis = "";
        String text = "";

        //Format the input path
        path = path.substring(path.indexOf(":") + 3, path.lastIndexOf("\""));
        System.out.println(path);



        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line ="";
            while ((line = reader.readLine()) != null) {
                journalistId = Integer.parseInt(line);
                title = reader.readLine();
                synopsis = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    text += line + " ";
                }

            }
            flag = true;

        } catch (IOException ex) {
            flag = false;

        }
        String save = "INSERT INTO article (journalist_id, title, synopsis, text, created, edited, active) " +
                "VALUES (?,?,?,?, now(), now(), TRUE);";
        int saveArticle = 0;
        try {
            saveArticle = jdbcTemplate.update(save, journalistId, title, synopsis, text);
        } catch (DataAccessException e){
            flag = false;
        }
        if(saveArticle > 0){
            flag = true;
        }


        return flag;
    }


}
