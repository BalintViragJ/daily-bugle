package hu.progmasters.ujratervezes.week16.dailybugle.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class ReaderRepository {
private JdbcTemplate jdbcTemplate;
}
