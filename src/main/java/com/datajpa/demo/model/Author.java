package com.datajpa.demo.model;

import com.datajpa.demo.model.dto.request.AuthorDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "zipCode_id")
    private ZipCode zipCode = null;
    @Nullable
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author(String name, ZipCode zipCode, List<Book> books) {
        this.name = name;
        this.zipCode = zipCode;
        this.books = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public static Author from(AuthorDto authorDto) {

        return new Author(authorDto.getName());
    }
}
