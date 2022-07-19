package com.sm;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest()
class BootApplicationTests {

	@Autowired
    private DataSource dataSource;
 
    @Test
    public void hikariConnectionPoolIsConfigured() {

    }

}
