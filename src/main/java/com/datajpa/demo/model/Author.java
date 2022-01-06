package com.datajpa.demo.model;

import com.datajpa.demo.model.dto.AuthorDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zipCode_id")
    private ZipCode zipCode;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author(String name, ZipCode zipCode, List<Book> books) {
        this.name = name;
        this.zipCode = zipCode;
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public static Author from(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        if (Objects.nonNull(authorDto.getZipCode())){
            author.setZipCode(author.getZipCode());
        }
        if (Objects.nonNull(authorDto.getBooks())) {
            author.setBooks(authorDto.getBooks());
        }
        return author;
    }
}
