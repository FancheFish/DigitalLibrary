package com.library.controller;

import com.library.bean.*;
import com.library.service.BookService;
import com.library.service.EBookService;
import com.library.service.LendService;
import com.library.service.ReaderTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private EBookService ebookService;
    @Autowired
    private LendService lendService;
    @Autowired
    private ReaderTraceService readerTraceService;

    private Date getDate(String pubstr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(pubstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(String searchWord) {
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("admin_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/reader_querybook_do.html")
    public ModelAndView readerQueryBookDo(String searchWord, HttpServletRequest request) {
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        String detail = "{\"searchWord\": \"" + searchWord + "\"}";
        boolean result = readerTraceService.addTrace(readerId, "普通检索", detail);
        if(!result) {
            System.out.println("跟踪读者行为失败！");
        }
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("reader_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("reader_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/reader_advanced_querybook_do.html")
    public ModelAndView readerAdvancedQueryBookDo(String searchName, String searchAuthor, String searchISBN, String searchCallname, String searchPublish, HttpServletRequest request) {
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        String detail = "{\"name\": \"" + searchName + "\", \"author\": \"" + searchAuthor + "\", \"ISBN\": \"" + searchISBN + "\", \"call_name\": \"" + searchCallname + "\", \"publish\": \"" + searchPublish + "\"}";
        boolean result = readerTraceService.addTrace(readerId, "高级检索", detail);
        if(!result) {
            System.out.println("跟踪读者行为失败！");
        }
        if (bookService.advancedMatchBook(searchName, searchAuthor, searchISBN, searchCallname, searchPublish)) {
            ArrayList<Book> books = bookService.advancedQueryBook(searchName, searchAuthor, searchISBN, searchCallname, searchPublish);
            ModelAndView modelAndView = new ModelAndView("reader_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("reader_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/admin_books.html")
    public ModelAndView adminBooks() {
        ArrayList<Book> books = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("admin_books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook() {
        return new ModelAndView("admin_book_add");
    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setPubdate(getDate(pubstr));
        if (bookService.addBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books.html";
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setPubdate(getDate(pubstr));
        if (bookService.editBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
        }
        return "redirect:/admin_books.html";
    }

    @RequestMapping("/admin_book_detail.html")
    public ModelAndView adminBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/reader_book_detail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        String ISBN = book.getIsbn();
//        EBook eBook = ebookService.getEBook(ISBN);
        EBook eBook = new EBook();
        if(ebookService.getEBook(ISBN)!=null) {
            eBook = ebookService.getEBook(ISBN);
        }
        else {
            eBook.setBookurl("暂无");
            eBook.setSource("暂无");
        }
        modelAndView.addObject("ebook_detail", eBook);
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        String bookName = book.getName();
        String detail = "{\"book_id\": " + bookId + ", \"name\": \"" + bookName + "\"}";
        boolean result = readerTraceService.addTrace(readerId, "查看详情", detail);
        if(!result) {
            System.out.println("跟踪读者行为失败！");
        }
        return modelAndView;
    }

    @RequestMapping("/admin_header.html")
    public ModelAndView admin_header() {
        return new ModelAndView("admin_header");
    }

    @RequestMapping("/reader_header.html")
    public ModelAndView reader_header() {
        return new ModelAndView("reader_header");
    }

    @RequestMapping("/reader_books.html")
    public ModelAndView readerBooks(HttpServletRequest request) {
        ArrayList<Book> books = bookService.getAllBooks();
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ArrayList<Lend> myAllLendList = lendService.myLendList(readerCard.getReaderId());
        ArrayList<Long> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend.getBookId());
            }
        }
        ModelAndView modelAndView = new ModelAndView("reader_books");
        modelAndView.addObject("books", books);
        modelAndView.addObject("myLendList", myLendList);
        return modelAndView;
    }
}
