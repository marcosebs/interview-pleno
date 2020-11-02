package br.com.brainweb.interview.core.features.hero.repository.impl;

import br.com.brainweb.interview.core.features.hero.repository.HeroRepository;
import br.com.brainweb.interview.model.enumerator.RaceEnum;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private static final String SQL_CREATE_HERO = "INSERT INTO hero (name, race, power_stats_id)" +
            "VALUES (:name, :race, :powerStatsId) " +
            "RETURNING id";

    private static final String SQL_FIND_HERO_BY_ID = "SELECT a.id, a.name, a.race, a.created_at, a.updated_at, a.power_stats_id, " +
            "b.agility, b.dexterity, b.intelligence, b.strength, b.created_at as created_at_power, b.updated_at as updated_at_power " +
            "FROM hero as a " +
            "JOIN power_stats as b on b.id = a.power_stats_id " +
            "WHERE a.id = :id;";

    private static final String SQL_LIST_HEROES_BY_NAME = "SELECT a.id, a.name, a.race, a.created_at, a.updated_at, a.power_stats_id, " +
            "b.agility, b.dexterity, b.intelligence, b.strength, b.created_at as created_at_power, b.updated_at as updated_at_power " +
            "FROM hero as a " +
            "JOIN power_stats as b on b.id = a.power_stats_id " +
            "WHERE a.name LIKE :name;";

    private static final String SQL_UPDATE_HERO = "UPDATE hero " +
            "SET name = :name, race = :race, updated_at = :updated_at " +
            "WHERE id = :id " +
            "RETURNING id;";

    private static final String SQL_DELETE_HERO = "DELETE FROM hero " +
            "WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public UUID create(Hero entity) {
        Map<String, Object> params = Map.of(
                "name", entity.getName(),
                "race", entity.getRace().getRaceDescription(),
                "powerStatsId", entity.getPowerStats().getId());
        return jdbc.queryForObject(SQL_CREATE_HERO, params, UUID.class);
    }

    @Override
    public Hero update(Hero entity) {
        Map<String, Object> params = Map.of(
                "id", entity.getId(),
                "name", entity.getName(),
                "race", entity.getRace().getRaceDescription(),
                "updated_at", LocalDateTime.now());
        UUID id = jdbc.queryForObject(SQL_UPDATE_HERO, params, UUID.class);
        return find(id);
    }

    @Override
    public Hero find(UUID id) {
        Map<String, Object> params = Map.of("id", id);
        List<Hero> heroes = jdbc.query(SQL_FIND_HERO_BY_ID, params, this::mapRow);
        if(heroes.isEmpty())
            return null;
        return heroes.get(0);
    }

    @Override
    public List<Hero> list(String name) {
        Map<String, Object> params = Map.of("name", "%"+name+"%");
        return jdbc.query(SQL_LIST_HEROES_BY_NAME, params, this::mapRow);
    }

    @Override
    public void delete(UUID id) {
        Map<String, Object> params = Map.of("id", id);
        jdbc.update(SQL_DELETE_HERO, params);
    }

    private Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
        PowerStats powerStats = PowerStats.builder()
                .id(UUID.fromString(rs.getString("power_stats_id")))
                .agility(rs.getInt("agility"))
                .dexterity(rs.getInt("dexterity"))
                .intelligence(rs.getInt("intelligence"))
                .strength(rs.getInt("strength"))
                .createdAt(rs.getTimestamp("created_at_power").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at_power").toLocalDateTime())
                .build();

        return Hero.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .race(RaceEnum.valueOf(rs.getString("race")))
                .powerStats(powerStats)
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .build();
    }
}
