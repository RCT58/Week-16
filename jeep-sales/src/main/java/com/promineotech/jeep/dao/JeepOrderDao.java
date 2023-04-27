package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Color;
import com.promineotech.jeep.entity.Engine;
import com.promineotech.jeep.entity.Tire;
import com.promineotech.jeep.entity.Option;
import com.promineotech.jeep.entity.Order;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultJeepOrderDao implements JeepOrderDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DefaultJeepOrderDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer fetchCustomer(String customerId) {
        // Implement the logic to fetch customer from the database based on the customerId
        // You can use jdbcTemplate.queryForObject() or jdbcTemplate.query() methods to execute SQL queries
        return null;
    }

    @Override
    public JeepModel fetchModel(String modelId, String trim, int doors) {
        // Implement the logic to fetch model from the database based on the modelId, trim, and doors
        // You can use jdbcTemplate.queryForObject() or jdbcTemplate.query() methods to execute SQL queries
        return null;
    }

    @Override
    public Color fetchColor(String colorId) {
        // Implement the logic to fetch color from the database based on the colorId
        // You can use jdbcTemplate.queryForObject() or jdbcTemplate.query() methods to execute SQL queries
        return null;
    }

    @Override
    public Engine fetchEngine(String engineId) {
        // Implement the logic to fetch engine from the database based on the engineId
        // You can use jdbcTemplate.queryForObject() or jdbcTemplate.query() methods to execute SQL queries
        return null;
    }

    @Override
    public Tire fetchTire(String tireId) {
        // Implement the logic to fetch tire from the database based on the tireId
        // You can use jdbcTemplate.queryForObject() or jdbcTemplate.query() methods to execute SQL queries
        return null;
    }

    @Override
    public List<Option> fetchOptions(List<String> optionIds) {
        // Implement the logic to fetch options from the database based on the option
        @Override
        public Order saveOrder(Customer customer, JeepModel model, Color color, Engine engine, Tire tire,
              List<Option> options, BigDecimal totalPrice) {
            String insertOrderSql = "INSERT INTO orders (customer_id, model_id, color_id, engine_id, tire_id, total_price) " +
                    "VALUES (:customerId, :modelId, :colorId, :engineId, :tireId, :totalPrice)";

            MapSqlParameterSource orderParams = new MapSqlParameterSource()
                    .addValue("customerId", customer.getCustomerId())
                    .addValue("modelId", model.getModelId())
                    .addValue("colorId", color.getColorId())
                    .addValue("engineId", engine.getEngineId())
                    .addValue("tireId", tire.getTireId())
                    .addValue("totalPrice", totalPrice);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(insertOrderSql, orderParams, keyHolder);

            Long orderPK = Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                    .orElseThrow(() -> new RuntimeException("Failed to retrieve order primary key."));

            saveOptions(options, orderPK);

            return Order.builder()
                    .orderId(orderPK)
                    .customer(customer)
                    .model(model)
                    .color(color)
                    .engine(engine)
                    .tire(tire)
                    .options(options)
                    .totalPrice(totalPrice)
                    .build();
        }

        private void saveOptions(List<Option> options, Long orderPK) {
            String insertOptionSql = "INSERT INTO order_options (order_id, option_id) VALUES (:orderId, :optionId)";

            for (Option option : options) {
                MapSqlParameterSource optionParams = new MapSqlParameterSource()
                        .addValue("orderId", orderPK)
                        .addValue("optionId", option.getOptionId());

                jdbcTemplate.update(insertOptionSql, optionParams);
            }
        }
    }


