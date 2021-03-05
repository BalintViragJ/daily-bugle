package hu.progmasters.ujratervezes.week16.dailybugle.controller;

import hu.progmasters.ujratervezes.week16.dailybugle.domain.Comment;
import hu.progmasters.ujratervezes.week16.dailybugle.dto.CommentCreateData;
import hu.progmasters.ujratervezes.week16.dailybugle.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentService.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findCommentByCommentId(@PathVariable int id) {
        Comment comment = commentService.findCommentByCommentId(id);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Comment> findCommentByArticleId(@PathVariable int articleId) {
        Comment comment = commentService.findCommentByArticleId(articleId);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{readerId}")
    public ResponseEntity<Comment> findCommentByReaderId(@PathVariable int readerId) {
        Comment comment = commentService.findCommentByReaderId(readerId);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> saveComment(@RequestBody CommentCreateData data) {
        boolean saveSuccessful = commentService.saveComment(data);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        boolean saveSuccessful = commentService.deleteComment(id);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
