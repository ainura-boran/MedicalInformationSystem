package factory;

import factory.interfaces.ModelFactory;
import models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFactory implements ModelFactory<Admin> {

    @Override
    public Admin create(ResultSet rs) throws SQLException {
        return new Admin.Builder()
                .setId(rs.getInt("id"))
                .setUsername(rs.getString("username"))
                .setPasswordHash(rs.getString("password_hash"))
                .build();
    }
}