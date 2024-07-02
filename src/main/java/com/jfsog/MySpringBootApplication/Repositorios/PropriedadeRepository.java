package com.jfsog.MySpringBootApplication.Repositorios;

import com.jfsog.MySpringBootApplication.Entidades.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, String>,
                                               JpaSpecificationExecutor<Propriedade> {
    //    List<Propriedade>
    //    findByNomeContainingIgnoreCaseOrderByCategoriaAscSubcategoriaAscNomeAsc(String nome);
//    @Query("Select c from Propriedade c where c.nome like %:filtro% order by categoria," +
//            "subcategoria,nome")
//    List<Propriedade> findByFiltro(@Param("filtro") String filtro);
    List<Propriedade> findByNomeContainingIgnoreCaseOrderByCategoriaAscSubcategoriaAscNomeAsc(String nome);
}

