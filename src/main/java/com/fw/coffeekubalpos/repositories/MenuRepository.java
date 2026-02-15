package com.fw.coffeekubalpos.repositories;

import com.fw.coffeekubalpos.entities.Menu;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
