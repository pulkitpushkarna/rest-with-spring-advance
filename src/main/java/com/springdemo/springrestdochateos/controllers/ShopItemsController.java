package com.springdemo.springrestdochateos.controllers;

import com.springdemo.springrestdochateos.annotation.RateLimit;
import com.springdemo.springrestdochateos.entities.Cart;
import com.springdemo.springrestdochateos.entities.Item;
import com.springdemo.springrestdochateos.enums.Gender;
import com.springdemo.springrestdochateos.services.Dummy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/item")
public class ShopItemsController {

    @Autowired
    Dummy dummy;

    Item item1 = new Item(1,"Item1",25);
    Item item2 = new Item(2,"Item2",30);
    List<Item> items=Arrays.asList(item1,item2);

    Cart cart= new Cart();

    @ApiOperation(value = "Get the item")
    @GetMapping("/{id}")
    public Item getItem1(@PathVariable("id") Integer id){
        Link selfLink= ControllerLinkBuilder
                .linkTo(ShopItemsController.class)
                .slash(id).withSelfRel();
        Link addToCart=ControllerLinkBuilder
                .linkTo(ShopItemsController.class)
                .slash("addToCart").slash(id).withRel("addToCart");
        Link removeFromCart=ControllerLinkBuilder
                .linkTo(ShopItemsController.class)
                .slash("removeFromCart").slash(id).withRel("removeFromCart");

        Item selectedItem=items.stream().filter(item->item.getItemId()==id).findFirst().get();
        selectedItem.getLinks().clear();
        selectedItem.add(selfLink);
        selectedItem.add(addToCart);
        selectedItem.add(removeFromCart);
        return selectedItem;
    }

    @GetMapping("/addToCart/{id}")
    public Cart addToCart(@PathVariable("id") Integer id){
        Item selectedItem=items.stream().filter(item->item.getItemId()==id).findFirst().get();
        cart.getList().add(selectedItem);
        return cart;
    }

    @GetMapping("/removeFromCart/{id}")
    public Cart removeCart(@PathVariable("id") Integer id){
        Item selectedItem=items.stream().filter(item->item.getItemId()==id).findFirst().get();
        cart.getList().remove(selectedItem);
        return cart;
    }

    @GetMapping("/cart")
    public Cart getCart(){
        return cart;
    }

    @GetMapping("/longRunningThread")
    public Callable<String> getResponse() throws Exception{
        System.out.println("Request Recieved");
        Callable<String> stringCallable= new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000L);
                System.out.println("Execute Long running something");
                return "Done";
            }
        };
        System.out.println("Request Completed");
        return stringCallable;
    }

    @GetMapping("/Deferred")
    public DeferredResult<String> getDeferredResult(){

        DeferredResult<String> deferredResult= new DeferredResult<>();
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Deferred Result";
        }).whenComplete((result,ex)->{ deferredResult.setResult(result);});
        return deferredResult;
    }

    @GetMapping("/asyncOperation")
    public String asyncOperation(){
        System.out.println("recieved request");
        dummy.longAsyncOperation();
        return "Done" ;
    }


    @Cacheable(value = "CacheResource")
    @GetMapping("/cachedData")
    public String cache() throws InterruptedException {
        Thread.sleep(4000L);
        return "Hello Cache";
    }

    @GetMapping("/evictCaching")
    @CacheEvict(value = "CacheResource")
    public String cacheEvict(){
        return "Cache Cleared";
    }

    @GetMapping("/genders")
    public List<Gender> getGenderList(){
        return  Arrays.asList(Gender.values());
    }

}
