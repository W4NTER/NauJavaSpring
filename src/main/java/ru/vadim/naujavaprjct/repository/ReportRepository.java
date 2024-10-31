package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
