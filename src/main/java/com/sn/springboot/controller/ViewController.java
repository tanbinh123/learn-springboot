package com.sn.springboot.controller;

import com.sn.springboot.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {
    @GetMapping("/ftlh_book")
    public String ftlhBookView(Model model) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setName("Springboot---" + i);
            book.setPrice(100 + i);
            books.add(book);
        }
        model.addAttribute("books", books);
        return "book";
    }
}
