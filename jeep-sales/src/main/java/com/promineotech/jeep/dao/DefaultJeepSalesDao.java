import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;



@Override
public List<Jeep> fetchJeeps(JeepModel model, String trim) {
  LOG.info("Fetch jeeps with model={} and trim={}", model, trim);
  
  String sql = "SELECT * FROM jeeps WHERE model_id = :model_id AND trim_level = :trim_level";

  MapSqlParameterSource params = new MapSqlParameterSource();
  params.addValue("model_id", model.toString());
  params.addValue("trim_level", trim);

  RowMapper<Jeep> rowMapper = (rs, rowNum) -> {
    Jeep jeep = new Jeep();
    jeep.setId(rs.getLong("id"));
    jeep.setModelId(JeepModel.valueOf(rs.getString("model_id")));
    jeep.setTrimLevel(rs.getString("trim_level"));
    jeep.setColor(rs.getString("color"));
    jeep.setPrice(rs.getBigDecimal("price"));

    return jeep;
  };

  return jdbcTemplate.query(sql, params, rowMapper);
}
