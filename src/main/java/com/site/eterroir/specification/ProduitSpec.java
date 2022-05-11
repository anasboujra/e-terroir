package com.site.eterroir.specification;

import com.site.eterroir.model.Produit;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Join(path = "produitMatieresAssos", alias = "a")
@And({
        @Spec(path = "categorie.nom", params = "categorie", spec = Equal.class),
        @Spec(path = "a.matierePremiere.nom", params = "matiere", spec = Equal.class),
        @Spec(path = "a.origine.nom", params = "origine", spec = Equal.class)
})
@Or({
        @Spec(path = "id", params = "search", spec = Equal.class),
        @Spec(path = "nom", params = "search", spec = Like.class),
        @Spec(path = "description", params = "search", spec = Like.class),
        @Spec(path = "prix", params = "search", spec = Equal.class),
        @Spec(path = "unite", params = "search", spec = Like.class),
        @Spec(path = "quantite", params = "search", spec = Equal.class),
        @Spec(path = "categorie.nom", params = "search", spec = Like.class),
        @Spec(path = "a.matierePremiere.nom", params = "search", spec = Like.class),
        @Spec(path = "a.origine.nom", params = "search", spec = Like.class),
        @Spec(path = "cooperative.nom", params = "search", spec = Like.class)
})
public interface ProduitSpec extends Specification<Produit> {
}
