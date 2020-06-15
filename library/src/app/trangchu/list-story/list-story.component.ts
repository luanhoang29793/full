import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-list-story',
  templateUrl: './list-story.component.html',
  styleUrls: ['./list-story.component.css']
})
export class ListStoryComponent implements OnInit {
    book: Observable<Book[]>
  constructor(
    private router :Router,
    private bookService : BookService

  ) { }

  ngOnInit() {
    this.book= this.bookService.getBooktop6();
  }
  listChapter(id: number){
    console.log(id);
    console.log("detail")
    this.router.navigate(['user/listChapter',id]);
  }
}
