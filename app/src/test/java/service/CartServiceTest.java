package service;

import data.Account;
import data.Article;
import data.CartArticle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    @Test
    void total() {
        Article article1 = new Article(1,"article1","desc",12,12.2, new ArrayList<>(), new ArrayList<>());
        Article article2 = new Article(2,"article2","desc",21,15.6, new ArrayList<>(), new ArrayList<>());
        CartService cartService = new CartService(new ArrayList<CartArticle>() {{
            add(new CartArticle(article1, 1));
            add(new CartArticle(article2, 2));
        }});

        assertEquals(43.4,cartService.total());
    }

    @Test
    void addArticle() {
        final int QUANTITY = 2;
        Article article1 = new Article(1,"article1","desc",12,12.2, new ArrayList<>(), new ArrayList<>());
        Article article2 = new Article(2,"article2","desc",21,15.6, new ArrayList<>(), new ArrayList<>());
        CartService cartService = new CartService(new ArrayList<CartArticle>() {{
            add(new CartArticle(article1, 1));
        }});
        cartService.setAccount(new Account());

        cartService.addArticle(article2,QUANTITY);

        boolean isIn = false;

        for (CartArticle cartArticle : cartService.getArticles())
        {
            if(cartArticle.getArticle().getId() == article2.getId())
            {
                if(cartArticle.getQuantity() == QUANTITY)
                {
                    isIn = true;
                }
            }
        }

        assertTrue(isIn);

    }

    @Test
    void deleteArticle() {
        Article article1 = new Article(1,"article1","desc",12,12.2, new ArrayList<>(), new ArrayList<>());
        Article article2 = new Article(2,"article2","desc",21,15.6, new ArrayList<>(), new ArrayList<>());
        CartService cartService = new CartService(new ArrayList<CartArticle>() {{
            add(new CartArticle(article1, 1));
            add(new CartArticle(article2, 2));
        }});
        cartService.setAccount(new Account());

        cartService.deleteArticle(article1);

        boolean isIn = false;

        for (CartArticle cartArticle : cartService.getArticles())
        {
            if(cartArticle.getArticle().getId() == article1.getId())
            {
                    isIn = true;
            }
        }

        assertFalse(isIn);
    }

    @Test
    void updateQuantity() {
        Article article1 = new Article(1,"article1","desc",12,12.2, new ArrayList<>(), new ArrayList<>());
        CartService cartService = new CartService(new ArrayList<CartArticle>() {{
            add(new CartArticle(article1, 1));
        }});
        cartService.setAccount(new Account());

        cartService.updateQuantity(article1,4);

        assertEquals(4, cartService.getArticles().get(0).getQuantity());
    }
}