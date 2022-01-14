package com.datajpa.demo.model;

import com.datajpa.demo.model.dto.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Nullable
    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String name, List<Author> authors,Category category) {
        this.name = name;
        this.authors = authors;
        this.category = category;
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public static Book from(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        return book;
    }

}
