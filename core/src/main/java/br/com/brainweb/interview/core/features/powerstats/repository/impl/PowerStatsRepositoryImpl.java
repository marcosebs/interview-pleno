package br.com.brainweb.interview.core.features.powerstats.repository.impl;

import br.com.brainweb.interview.core.features.powerstats.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private static final String SQL_CREATE_POWER = "INSERT INTO power_stats (strength, agility, dexterity, intelligence)" +
            "VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    private static final String SQL_UPDATE_POWER = "UPDATE power_stats " +
            "SET strength = :strength, agility = :agility, dexterity = :dexterity, intelligence = :intelligence, updated_at = :updated_at " +
            "WHERE id = :id RETURNING id;";

    private static final String SQL_DELETE_POWER = "DELETE from power_stats " +
            "WHERE id = :id;";

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public UUID create(PowerStats entity) {
        Map<String, Object> params = Map.of(
                "strength", entity.getStrength(),
                "agility", entity.getAgility(),
                "dexterity", entity.getDexterity(),
                "intelligence", entity.getIntelligence());
        return jdbc.queryForObject(SQL_CREATE_POWER, params, UUID.class);
    }

    @Override
    public UUID update(PowerStats entity) {
        Map<String, Object> params = Map.of(
                "strength", entity.getStrength(),
                "agility", entity.getAgility(),
                "dexterity", entity.getDexterity(),
                "intelligence", entity.getIntelligence(),
                "id", entity.getId(),
                "updated_at", LocalDateTime.now());
        return jdbc.queryForObject(SQL_UPDATE_POWER, params, UUID.class);
    }

    @Override
    public void delete(UUID id) {
        Map<String, Object> params = Map.of("id", id);
        jdbc.update(SQL_DELETE_POWER, params);
    }

}
