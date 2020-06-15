package com.ait.library.controller;

import com.ait.library.Export.ExportBook;
import com.ait.library.Export.ExportChapter;
import com.ait.library.Service.ImplServiceJBDC.ChapterServiceImpl;
import com.ait.library.model.Book;
import com.ait.library.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.util.List;
@RestController
public class ChapterController {
    @Autowired
    private ChapterServiceImpl chapterService;
    @GetMapping("/chapter")
    public List<Chapter> categoriesAll() {

        return chapterService.findAll();
    }
    //    -------------------Retrieve All Customers--------------------------------------------------------
    @RequestMapping(value = "/chapter/", method = RequestMethod.GET)
    public ResponseEntity<List<Chapter>> listAllCategory() {
        List<Chapter> chapters = chapterService.findAll();
        if (chapters.isEmpty()) {
            return new ResponseEntity<List<Chapter>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Chapter>>(chapters, HttpStatus.OK);
    }
    //
    @RequestMapping(value = "/chapter/listBook/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Chapter>> listAllChapterForBook(@PathVariable("id") int id) {
        List<Chapter> chapters = chapterService.findChapterByBookID(id);
        if (chapters.isEmpty()) {
            return new ResponseEntity<List<Chapter>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Chapter>>(chapters, HttpStatus.OK);
    }
    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/chapter/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chapter> getCategory(@PathVariable("id") int idChapter) {
        Chapter chapter = chapterService.findById(idChapter);
        if (chapter == null) {
            return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Chapter>(chapter, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/chapter/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createChapter(@RequestBody Chapter chapter, UriComponentsBuilder ucBuilder) {

        chapterService.save(chapter);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/chapter/create/{id}").buildAndExpand(chapter.getIdChapter()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    // createchpateforBook
    @RequestMapping(value = "/chapter/createforBook/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> createChapter(@PathVariable("id") int id,@RequestBody Chapter chapter, UriComponentsBuilder ucBuilder) {

        chapterService.saveChapterForBook(id, chapter);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/chapter/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Chapter> updateChapter(@PathVariable("id") int id, @RequestBody Chapter chapter) {

        Chapter chapter1 = chapterService.findById(id);

        if (chapter1 == null) {
            return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
        }

        chapter1.setNameChapter(chapter1.getNameChapter());
        chapter1.setContentChapter(chapter.getContentChapter() );
        chapterService.update(chapter1);
        return new ResponseEntity<Chapter>(chapter1, HttpStatus.OK);
    }
    //------------------- Delete a Customer --------------------------------------------------------
    @RequestMapping(value = "/chapter/remove/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Chapter> removeChapter(@PathVariable("id") int id, @RequestBody Chapter chapter) {
        System.out.println("Updating Customer " + id);

        Chapter chapter1 = chapterService.findById(id);

        if (chapter1 == null) {
            return new ResponseEntity<Chapter>(HttpStatus.NOT_FOUND);
        }


        chapterService.remove(chapter1.getIdChapter());
        return new ResponseEntity<Chapter>(chapter1, HttpStatus.OK);
    }
    @GetMapping(value = "/chapter/export/{id}")
    public ResponseEntity<InputStreamResource> chapterToPDF(@PathVariable("id") int id) throws IOException {
        Chapter chapters = chapterService.findById(id);

         ByteArrayInputStream in = ExportChapter.chaptertoPdf(chapters);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+ chapters.getNameChapter() +".docx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
