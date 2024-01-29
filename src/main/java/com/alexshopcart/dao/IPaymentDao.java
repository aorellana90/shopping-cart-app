package com.alexshopcart.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexshopcart.model.Pago;

@Repository
public interface IPaymentDao extends JpaRepository<Pago, Long> {

	@Query("SELECT p FROM Pago p WHERE p.idOrden = :idOrden")
	Optional<Pago> findByOrder(@Param("idOrden") Long idOrden);

	@Query("SELECT p FROM Pago p WHERE p.idPago = :idPago")
	Optional<Pago> findByPayment(@Param("idPago") Long idPago);

}
