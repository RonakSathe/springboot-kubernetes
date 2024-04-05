package com.rons.bookmarkapi.api;

import com.rons.bookmarkapi.domain.Bookmark;
import com.rons.bookmarkapi.domain.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Random_Port is used so that it doesnt clases with the seervices running of specifc ports
//Using TestContainers

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
        }
)
class BookmarkControllerTest {

    //It provides the environment to run Spring tests.
    // It allows you to perform requests and validate responses without actually starting the full HTTP server.
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;
    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();

        bookmarks.add(new Bookmark(null, "Introduction to Algorithms", "https://en.wikipedia.org/wiki/Introduction_to_Algorithms", Instant.now()));
        bookmarks.add(new Bookmark(null, "The C Programming Language", "https://en.wikipedia.org/wiki/The_C_Programming_Language", Instant.now()));
        bookmarks.add(new Bookmark(null, "Clean Code: A Handbook of Agile Software Craftsmanship", "https://en.wikipedia.org/wiki/Clean_Code", Instant.now()));
        bookmarks.add(new Bookmark(null, "Artificial Intelligence: A Modern Approach", "https://en.wikipedia.org/wiki/Artificial_Intelligence:_A_Modern_Approach", Instant.now()));
        bookmarks.add(new Bookmark(null, "Computer Networks", "https://en.wikipedia.org/wiki/Computer_network", Instant.now()));
        bookmarks.add(new Bookmark(null, "Computer Science: An Overview", "https://en.wikipedia.org/wiki/Computer_science", Instant.now()));
        bookmarks.add(new Bookmark(null, "Operating System Concepts", "https://en.wikipedia.org/wiki/Operating_System_Concepts", Instant.now()));
        bookmarks.add(new Bookmark(null, "Database System Concepts", "https://en.wikipedia.org/wiki/Database_System_Concepts", Instant.now()));
        bookmarks.add(new Bookmark(null, "Introduction to the Theory of Computation", "https://en.wikipedia.org/wiki/Introduction_to_the_Theory_of_Computation", Instant.now()));
        bookmarks.add(new Bookmark(null, "Design Patterns: Elements of Reusable Object-Oriented Software", "https://en.wikipedia.org/wiki/Design_Patterns", Instant.now()));
        bookmarks.add(new Bookmark(null, "Computer Graphics: Principles and Practice", "https://en.wikipedia.org/wiki/Computer_Graphics:_Principles_and_Practice", Instant.now()));
        bookmarks.add(new Bookmark(null, "The Mythical Man-Month: Essays on Software Engineering", "https://en.wikipedia.org/wiki/The_Mythical_Man-Month", Instant.now()));
        bookmarks.add(new Bookmark(null, "Introduction to Artificial Intelligence", "https://en.wikipedia.org/wiki/Introduction_to_Artificial_Intelligence", Instant.now()));
        bookmarks.add(new Bookmark(null, "Computer Organization and Design", "https://en.wikipedia.org/wiki/Computer_Organization_and_Design", Instant.now()));
        bookmarks.add(new Bookmark(null, "The Art of Computer Programming", "https://en.wikipedia.org/wiki/The_Art_of_Computer_Programming", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);


    }

    @ParameterizedTest
    @CsvSource({
            "1,15,2,1,true,false,true,false",
            "2,15,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo,int totalElements,
                            int totalPages, int currentPage,
                            boolean isFirst, boolean isLast,
                            boolean hasNext, boolean hasPrevious) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements",  CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages",  CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage",  CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst",  CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast",  CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext",  CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious",  CoreMatchers.equalTo(hasPrevious)));

    }


}