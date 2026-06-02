package com.dondoc.repository;

import com.dondoc.entity.FarmMember;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FarmMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public FarmMemberRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public List<FarmMember> findAll(){
        String sql = "SELECT * FROM farm_members";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new FarmMember(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("farm_id"),
                rs.getObject("joined_at", LocalDateTime.class)
        ));
    }

    public void save(FarmMember farmMember) {
        String sql = "INSERT INTO farm_members (user_id, farm_id, joined_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, farmMember.getUserId(), farmMember.getFarmId(), farmMember.getJoinedAt());
    }
}
