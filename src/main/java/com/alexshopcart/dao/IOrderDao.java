package com.alexshopcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexshopcart.model.Orden;

@Repository
public interface IOrderDao extends JpaRepository<Orden, Long> {

	@Query("SELECT DISTINCT o FROM Orden o ORDER BY o.idOrden")
	List<Orden> orderList();

	@Query("SELECT o FROM Orden o JOIN FETCH o.detallesOrden WHERE o.idOrden = :idOrden")
	Optional<Orden> findOrderById(@Param("idOrden") Long idOrden);

	Optional<Orden> findById(Long id);

}
