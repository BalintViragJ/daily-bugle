package hu.progmasters.ujratervezes.week16.dailybugle.service;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Comment;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.CommentCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.CommentRepository;
import hu.progmasters.ujratervezes.week16.dailybugle.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    CommentRepository commentRepository;
    ReaderRepository readerRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ReaderRepository readerRepository) {
        this.commentRepository = commentRepository;
        this.readerRepository = readerRepository;
    }

    public List<Comment> getComments() {
        List<Comment> commentList = commentRepository.getComments();
        return commentList;
    }

    public Comment findCommentByCommentId(int id) {
        Comment comment = commentRepository.findCommentByCommentId(id);
    return comment;
    }

    public Comment findCommentByArticleId(int articleId) {
        Comment comment = commentRepository.findCommentByArticleId(articleId);
        return comment;
    }

    public Comment findCommentByReaderId(int readerId) {
        Comment comment = commentRepository.findCommentByReaderId(readerId);
        return comment;
    }

    public boolean saveComment(CommentCreateData data) {

        if (readerRepository.findReaders(data.getReaderId()) != null) {
            boolean saveSuccessful = commentRepository.saveComment(data);
            return saveSuccessful;
        }
        return false;
    }

    public boolean deleteComment(int id) {
        boolean saveSuccessful = commentRepository.deleteComment(id);
        return saveSuccessful;
    }
}
