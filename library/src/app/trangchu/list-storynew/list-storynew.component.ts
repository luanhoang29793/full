import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { Observable } from 'rxjs';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-list-storynew',
  templateUrl: './list-storynew.component.html',
  styleUrls: ['./list-storynew.component.css']
})
export class ListStorynewComponent implements OnInit {
  book:Observable<Book[]>
  constructor(
    private router :Router,
    private bookService : BookService

  ) { }

  ngOnInit() {
    this.load();
  }
  load(){
    this.book = this.bookService.getBookDesc()
  }
  listChapter(id: number){
    console.log(id);
    console.log("detail")
    this.router.navigate(['user/listChapter',id]);
  }
}
