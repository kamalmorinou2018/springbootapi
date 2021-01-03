package com.ecommerce.microcommerce.web.controller;


import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api( description="API pour es opérations CRUD sur les produits.")
@RestController
public class ProductController<list> {
    @Autowired
    private ProductDao productDao;


    //Récupérer la liste des produits
    @GetMapping(value="/Produits")
    public List<Product> listeProduits() {
        return productDao.findAll();
    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value="/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        return productDao.findById(id);
    }
    @GetMapping("test/Produit/{prixLimit}")
    public List<Product> listaudessusduprix(@PathVariable int prixLimit){
        return productDao.findByPrixGreaterThan(prixLimit);
    }
    @GetMapping("test/Produits/{nom}")
    public List<Product> listnompareil(@PathVariable String nom){
        return productDao.findByNomLike(nom);
    }
   // Enrégistrer un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/Produits/{id}")
    public void supprimerProduit(@PathVariable int id){
        productDao.deleteById(id);
    }
    @PutMapping("/Produits")
    public Product mettreAjourProduit(@RequestBody Product product){
        return productDao.save(product);
    }


}