package com.visa.vttp.server.repositories;

public class Queries {

    static final String SQL_SELECT_CONTACT = """
            SELECT
                name, mobile, email
            FROM
                contacts
            """;
    static final String SQL_INSERT_NAME_BY_MOBILE = """
            INSERT INTO contact_mobile (
                name,
                mobile
            ) VALUES
                (?,?);
                """;
    static final String SQL_INSERT_NAME_BY_EMAIL = """
            INSERT INTO contact_email (
                name,
                email
            ) VALUES
                (?,?);
                """;
    static final String SQl_REMOVE_NAME_BY_MOBILE = """
            DELETE FROM contact_mobile
            WHERE mobile = ?;
            """;
    static final String SQl_REMOVE_NAME_BY_EMAIL = """
            DELETE FROM contact_email
            WHERE email = ?;
            """;
}
