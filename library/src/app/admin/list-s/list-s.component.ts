import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-s',
  templateUrl: './list-s.component.html',
  styleUrls: ['./list-s.component.css']
})
export class ListSComponent implements OnInit {
    book: Observable<Book[]>
    p: number = 1;
    collection: any[]; 
    searchText;

    constructor(
    private bookService: BookService,
    private router :Router,

  ) { }

  ngOnInit() {
    this.reload();
  }
  reload(){
    this.book= this.bookService.getBookList();
  }
  createChapterforBook(id:number){
    console.log("createChapterforBook")
    this.router.navigate(['admin/createChapterforBook/',id]);
    console.log(id,"idbook")
  }
  editStory(id: number){
    console.log("editChapter")
    console.log(id)
    this.router.navigate(['admin/editStory', id]);
  }
  listChapter(id: number){
    console.log("listChapter")
    console.log(id)
    this.router.navigate(['admin/listChapter', id]);
  }
  upload(){
    this.router.navigate(['admin/upload']);
  }
  listStory(id: number){
    console.log("listChapter")
    console.log(id)
    this.router.navigate(['user/listChapter/', id]);
  }
  
}
