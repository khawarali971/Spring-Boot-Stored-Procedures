package com.test.oracle.Repo;

import com.test.oracle.entity.CustomerName;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TestRepo extends CassandraRepository<CustomerName, String> {
}
