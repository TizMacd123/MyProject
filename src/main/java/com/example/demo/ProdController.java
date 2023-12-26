package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;







//@RestController
//@RequestMapping(path = "/produit") // http://localhost:8080/produit

public class ProdController {
    @Autowired
    private ProdRepo prodRepo;
///
//return all product
@GetMapping(path = "/allPrds")// Get : http://localhost:8080/produit/allPrds
   public @ResponseBody Iterable<Produit> getAllProds() {
       return prodRepo.findAll();
   }
//rzturn one product
@GetMapping(path="/prod/{id}")  // Get : http://localhost:8080/produit/prod/{id}
public Produit getProduit(@PathVariable("id") Integer id) {
    //if(prodRepo.findById(id).get()==null) return new Produit();
    return prodRepo.findById(id).get();
}
//Add new Product
@PostMapping(path="/add")
@ResponseBody
public Produit addProd(@RequestBody Produit prod) {
    //TODO: process POST request
    prodRepo.save(prod);
    return prod;
}
@PutMapping(path="prod/{id}")
public Produit putMethodName(@PathVariable Integer id, @RequestBody Produit prod) {
    //TODO: process PUT request
    prod.setId(id);
    prodRepo.save(prod);
    return prod;
}
//remove product 
@DeleteMapping(path="remove/{id}")
public String delete(@PathVariable("id") Integer id){
    prodRepo.deleteById(id);
    return "product removed";
}
//somme de des prix des produits
@GetMapping(path="/som")
public String  somme() {
    Iterable<Produit> it=getAllProds();
    int som=0;
        for (Produit produit : it) {
            som+=produit.getPrice();       }
    return "Le totale est :" +som + "DH";
}


}
