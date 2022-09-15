package com.visa.vttp.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.visa.vttp.server.models.Contact;
import static com.visa.vttp.server.repositories.Queries.*;

import java.util.ArrayList;
import java.util.List;

@Repository()
public class ContactRepository {

    @Autowired
    private JdbcTemplate jt;

    // @Autowired
    // ContactRepository(JdbcTemplate jt) {
    //     this.jt = jt;
    // }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        final SqlRowSet rs = jt.queryForRowSet(SQL_SELECT_CONTACT);
        while (rs.next()) {
            contacts.add(Contact.create(rs));
        }
        return contacts;
    }

    public boolean insertNameByMobile(String name, String mobile) {
        final int rows = jt.update(SQL_INSERT_NAME_BY_MOBILE,
                name, mobile);
        if (rows == 1)
            return true;
        throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(SQL_INSERT_NAME_BY_MOBILE, 1, rows);
    }

    public boolean removeNameByMobile(String mobile) {
        final int rows = jt.update(SQl_REMOVE_NAME_BY_MOBILE, mobile);
        if (rows == 1) return true;
        throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(SQl_REMOVE_NAME_BY_EMAIL, 1, rows);
    }

    public boolean insertNameByEmail(String name, String email) {
        final int rows = jt.update(SQL_INSERT_NAME_BY_EMAIL,
                name, email);
        if (rows == 1)
            return true;
        throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(SQL_INSERT_NAME_BY_EMAIL, 1, rows);
    }

    public boolean removeNameByEmail(String email) {
        final int rows = jt.update(SQl_REMOVE_NAME_BY_EMAIL, email);
        if (rows == 1) return true;
        throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(SQl_REMOVE_NAME_BY_EMAIL, 1, rows);
    }
}
